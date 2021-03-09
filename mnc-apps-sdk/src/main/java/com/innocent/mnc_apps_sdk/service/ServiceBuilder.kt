package com.innocent.mnc_apps_sdk.service

import android.content.Context
import com.google.gson.GsonBuilder
import com.innocent.mnc_apps_sdk.utils.NetworkUtils.hasNetwork
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class ServiceBuilder() {

    companion object {
        private const val BASE_URL = "https://firebasestorage.googleapis.com/"
        private const val HTTP_CACHE = "http-cache"
        private const val CACHE_SIZE = 10 * 1024 * 2014
        private const val CACHE_CONTROL = "Cache-Control"
        private const val PRAGMA = "Pragma"
    }

    fun provideApiService(): Service {
        return provideRetrofit(BASE_URL).create(Service::class.java)
    }

    fun provideApiServiceWithCache(context: Context): Service {
        return provideRetrofitWithCache(context, BASE_URL).create(Service::class.java)
    }

    private fun provideRetrofit(baseUrl: String): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //.client(getUnsafeOkHttpClient().build())
            .client(provideOkHttpClient())
            .build()
    }

    private fun provideRetrofitWithCache(context: Context, baseUrl: String): Retrofit {
        val builder = OkHttpClient.Builder()
            .addInterceptor(provideInterceptor())
            .addInterceptor(provideInterceptorWithHttpLogging())
            .addInterceptor(provideInterceptorWithOfflineCache(context))
            .addNetworkInterceptor(provideInterceptorWithCache())
            .cache(provideCache(context))
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        return httpClient
            .addInterceptor(provideInterceptor())
            .addInterceptor(provideInterceptorWithHttpLogging())
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS).build()
    }

    private fun provideInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val originalRequest = chain.request()
            var url = originalRequest.url().toString()
            url = url.replace("%3D", "=")
            val request = originalRequest.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }

    private fun provideInterceptorWithCache(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(15, TimeUnit.SECONDS)
                .build()
            response.newBuilder()
                .removeHeader(PRAGMA)
                .header(CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }

    private fun provideInterceptorWithOfflineCache(context: Context): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (!hasNetwork(context)) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(15, TimeUnit.SECONDS)
                    .build()
                request = request.newBuilder()
                    .removeHeader(PRAGMA)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }

    private fun provideInterceptorWithHttpLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private fun provideCache(context: Context): Cache? {
        var cache: Cache? = null
        try {
            cache = Cache(File(context.applicationContext.cacheDir, HTTP_CACHE),
                CACHE_SIZE.toLong())
        } catch (e: Exception) {
        }
        return cache
    }
}