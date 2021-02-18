package com.innocent.mnc_apps_sdk.service

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceBuilder() {

    companion object {
        val BASE_URL = "https://firebasestorage.googleapis.com/"
    }

    fun provideApiService(): Service {
        return provideRetrofit(BASE_URL).create(Service::class.java)
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

    private fun provideInterceptorWithHttpLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}