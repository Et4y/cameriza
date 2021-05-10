package com.etch.camera.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.etch.camera.R

class CameraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val iv = itemView.findViewById<ImageView>(R.id.iv)

}