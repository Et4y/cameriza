package etch.cameraiza.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import etch.cameraiza.R

class CameraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val iv = itemView.findViewById<ImageView>(R.id.iv)

}