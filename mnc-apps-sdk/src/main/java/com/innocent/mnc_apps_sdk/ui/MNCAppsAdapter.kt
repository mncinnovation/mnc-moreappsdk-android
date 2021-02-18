package com.innocent.mnc_apps_sdk.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.innocent.mnc_apps_sdk.R
import com.innocent.mnc_apps_sdk.model.AppsModel
import kotlinx.android.synthetic.main.item_recyclerview_mnc_apps.view.*

class MNCAppsAdapter(val context: Context, private val listApps: MutableList<AppsModel>) :
    RecyclerView.Adapter<MNCAppsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview_mnc_apps, parent, false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(listApps[position])
    }

    override fun getItemCount(): Int = listApps.size

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(apps: AppsModel) {
            itemView.title.text = apps.appName
            itemView.desc.text = apps.description?.id
            Glide.with(context)
                .load(apps.image)
                .into(itemView.logoAppsImageView)
        }
    }
}
