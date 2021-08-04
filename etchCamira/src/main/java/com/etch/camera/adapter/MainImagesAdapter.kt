package com.etch.camera.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etch.camera.data.CamerizaImageModel
import com.etch.camera.R
import com.etch.camera.util.inflate
import com.etch.camera.util.setGlide
import javax.inject.Inject


class MainImagesAdapter @Inject constructor() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var dataList = ArrayList<CamerizaImageModel>()
    var selectedImages = ArrayList<CamerizaImageModel>()

    var onImageClick: ((ArrayList<CamerizaImageModel>) -> (Unit))? = null
    var onCameraClick: ((String) -> (Unit))? = null

    var lastSelectedPosition = 0
    var isSingleSelection = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflatedView: View

        return when (viewType) {
            0 -> {
                inflatedView = parent.inflate(R.layout.item_cameriza_camera, false)
                CameraViewHolder(inflatedView)
            }
            else -> {
                inflatedView = parent.inflate(R.layout.item_cameriza_main_images, false)
                GalleryViewHolder(inflatedView)
            }
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position].type == "")
            0
        else
            1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataList.get(position)

        when (holder) {

            is GalleryViewHolder -> {

                holder.iv.setGlide(data.image)

                holder.iv.setOnClickListener {

                    if (isSingleSelection) {
                        dataList[lastSelectedPosition].isSelected = false

                        selectedImages.clear()
                        selectedImages.add(data)
                        data.isSelected = !data.isSelected

                        onImageClick?.invoke(selectedImages)
                        notifyDataSetChanged()

                        lastSelectedPosition = position

                    } else {
                        if (data.isSelected) {
                            selectedImages.remove(data)
                            data.isSelected = !data.isSelected
                        } else {
                            data.isSelected = !data.isSelected
                            selectedImages.add(data)
                        }

                        onImageClick?.invoke(selectedImages)
                        notifyDataSetChanged()

                    }


                }

                if (data.isSelected)
                    holder.checkContainer.visibility = View.VISIBLE
                else
                    holder.checkContainer.visibility = View.GONE

            }


            is CameraViewHolder -> {
                holder.iv.setOnClickListener {
                    onCameraClick?.invoke("")
                }
            }


        }

    }


}