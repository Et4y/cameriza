package com.etch.camera.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etch.camera.R
import com.etch.camera.util.inflate
import com.etch.camera.util.setGlide
import javax.inject.Inject


class MainImagesAdapter @Inject constructor() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var dataList = ArrayList<String>()
    var onImageClick: ((String) -> (Unit))? = null
    var onCameraClick: ((String) -> (Unit))? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflatedView: View

        return when (viewType) {
            0 -> {
                inflatedView = parent.inflate(R.layout.item_camera, false)
                CameraViewHolder(inflatedView)
            }
            else -> {
                inflatedView = parent.inflate(R.layout.item_main_images, false)
                GalleryViewHolder(inflatedView)
            }
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position] == "")
            0
        else
            1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataList.get(position)

        when (holder) {
            is GalleryViewHolder -> {
                holder.iv.setGlide(data)

                holder.iv.setOnClickListener {
                    onImageClick?.invoke(data)
                }
            }

            is CameraViewHolder -> {
                holder.iv.setOnClickListener {
                    onCameraClick?.invoke("")
                }
            }
        }
    }


}