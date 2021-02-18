package com.innocent.mnc_apps_sdk.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.innocent.mnc_apps_sdk.R
import com.innocent.mnc_apps_sdk.constant.Constant
import com.innocent.mnc_apps_sdk.presenter.MNCAppsPresenter
import kotlinx.android.synthetic.main.activity_mnc_apps_body.*
import kotlinx.android.synthetic.main.view_toolbar.*

class MNCAppsActivity : AppCompatActivity(), MNCAppsContract.View {
    private val presenter: MNCAppsContract.Presenter = initPresenter()
    private var listAppsAdapter: MNCAppsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mnc_apps_screen)

        setupToolbar()

        setLogo()

        presenter.bind(this)
        presenter.start()
    }

    private fun setLogo() {
        Glide.with(this)
            .asBitmap()
            .load(Constant.mncLogo)
            .into(logoImageView)
    }

    private fun setupToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun mainStartActivity(context: Context) {
        val intent = Intent(context, MNCAppsActivity::class.java)
        context.startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showListApps() {
        listAppsAdapter = MNCAppsAdapter(this, presenter.listApps)
        appsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        appsRecyclerView.adapter = listAppsAdapter
    }

    override fun initPresenter(): MNCAppsContract.Presenter = MNCAppsPresenter(this)
}