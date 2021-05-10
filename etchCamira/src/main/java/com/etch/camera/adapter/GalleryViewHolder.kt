package com.etch.camera.adapter

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.etch.camera.R

class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
    val iv = itemView.findViewById<ImageView>(R.id.iv)
    val checkContainer = itemView.findViewById<RelativeLayout>(R.id.checkContainer)

}