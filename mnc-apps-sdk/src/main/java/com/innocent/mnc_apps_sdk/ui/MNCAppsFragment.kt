package com.innocent.mnc_apps_sdk.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.innocent.mnc_apps_sdk.R
import com.innocent.mnc_apps_sdk.constant.Constant
import com.innocent.mnc_apps_sdk.presenter.MNCAppsPresenter
import kotlinx.android.synthetic.main.fragment_mnc_apps_body.*


class MNCAppsFragment : Fragment(), MNCAppsContract.View {

    private var presenter: MNCAppsContract.Presenter? = null
    private var listAppsAdapter: MNCAppsAdapter? = null
    override var userID: String? = null
    override var packageNameApps: String? = null
    override var platformType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            userID = arguments?.getString(Constant.userID)
            packageNameApps = arguments?.getString(Constant.packageName)
            platformType = arguments?.getString(Constant.platformType)
        }

        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_mnc_apps_body, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = initPresenter()
        presenter?.bind(this)
        presenter?.start()
    }

    override val screenSize: Int
        get() {
            if (isAdded && activity != null) {
                return resources.configuration.smallestScreenWidthDp
            }

            return 0
        }

    override fun showListApps() {
        if (isAdded && activity != null) {
            listAppsAdapter =
                MNCAppsAdapter(context!!, presenter!!.listApps, presenter!!.layoutApps)
            appsRecyclerView.layoutManager = LinearLayoutManager(
                context!!,
                LinearLayoutManager.VERTICAL,
                false
            )
            appsRecyclerView.adapter = listAppsAdapter
        }
    }

    override fun showGridListApps() {
        if (isAdded && activity != null) {
            listAppsAdapter =
                MNCAppsAdapter(context!!, presenter!!.listApps, presenter!!.layoutApps)
            appsRecyclerView.layoutManager = GridLayoutManager(context!!, 2)
            appsRecyclerView.adapter = listAppsAdapter
        }
    }

    override fun showProgressBar(isShown: Boolean) {
        if (isAdded && activity != null) {
            if (isShown) {
                listProgressBar.visibility = View.VISIBLE
            } else {
                listProgressBar.visibility = View.GONE
            }
        }
    }

    override fun initPresenter(): MNCAppsContract.Presenter = MNCAppsPresenter(context!!)

    fun showWebViewFragment(webUrl: String, activity: FragmentActivity) {
//        if (isAdded && activity != null) {
            val webViewFragment = InAppWebViewFragment()
            val bundle = Bundle()
            val fragmentManager = activity.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            bundle.putString(Constant.webUrl, webUrl)
            webViewFragment.arguments = bundle

            fragmentTransaction.replace(R.id.mncAppsBodyLayout, webViewFragment, "WebViewFragment")
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
//        }
    }
}