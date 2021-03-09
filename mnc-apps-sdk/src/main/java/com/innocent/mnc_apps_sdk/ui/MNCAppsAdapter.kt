package com.innocent.mnc_apps_sdk.ui

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.innocent.mnc_apps_sdk.R
import com.innocent.mnc_apps_sdk.model.AppsModel
import com.innocent.mnc_apps_sdk.model.LayoutModel
import com.innocent.mnc_apps_sdk.utils.ButtonUtils
import com.innocent.mnc_apps_sdk.utils.LayoutUtils
import kotlinx.android.synthetic.main.item_recyclerview_mnc_apps.view.*


class MNCAppsAdapter(
    val context: Context?,
    private val listApps: MutableList<AppsModel>,
    private val layoutModel: LayoutModel
) :
    RecyclerView.Adapter<MNCAppsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recyclerview_mnc_apps,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(listApps[position], layoutModel)
    }

    override fun getItemCount(): Int = listApps.size

    @Suppress("DEPRECATION")
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(apps: AppsModel, layout: LayoutModel) {

            var installed: Boolean = ButtonUtils.getInstalledStatus(data = apps, packageManager = context?.packageManager)
            var buttonText: String = ButtonUtils.getButtonLabel(data = apps, installed = installed)

            itemView.title.text = apps.appName
            itemView.desc.text = apps.description?.id
            Glide.with(context!!)
                .load(apps.image)
                .into(itemView.logoAppsImageView)

            LayoutUtils.getDrawable(radius = LayoutUtils.getRoundSize(layout.buttonRoundedSize), color = layout.buttonColor, view = itemView.openButton)
            itemView.openButton.setTextColor(Color.parseColor("#${layout.buttonTextColor}"))
            itemView.cardListLayout.radius = LayoutUtils.getRoundSize(layout.cardRoundedSize).toFloat()
            itemView.openButton.text = buttonText
            itemView.openButton.setOnClickListener {
                ButtonUtils.openFunction(context = context, data = apps, installed = installed)
            }
        }
    }
}
