package com.etch.camera.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.etch.camera.R
import com.etch.camera.util.inflate
import com.etch.camera.util.setGlide
import javax.inject.Inject


class ImagesAdapter @Inject constructor() :
    RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {


    var dataList: List<String>? = null
    var onItemClick: ((String, String) -> (Unit))? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.item_images, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        dataList?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList?.get(position)

        holder.iv.setGlide(data!!)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv = itemView.findViewById<ImageView>(R.id.iv)
    }


}