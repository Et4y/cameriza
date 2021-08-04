package com.etch.camera.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.etch.camera.data.CamerizaImageModel
import com.etch.camera.R
import com.etch.camera.util.inflate
import com.etch.camera.util.setGlide
import com.mikhaellopez.circleview.CircleView
import javax.inject.Inject


class ColorsAdapter @Inject constructor() :
    RecyclerView.Adapter<ColorsAdapter.ViewHolder>() {


    var dataList = ArrayList<Int>()

    var selectedPosition = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.item_color, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList.get(position)

        if (selectedPosition == position)
            holder.rlSelected.background =
                holder.itemView.context.getDrawable(R.drawable.circle_white)
        else
            holder.rlSelected.background = null


        holder.view.circleColor = data

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rlSelected = itemView.findViewById<RelativeLayout>(R.id.rlSelected)
        val view = itemView.findViewById<CircleView>(R.id.view)

        init {
            itemView.setOnClickListener {
                selectedPosition = absoluteAdapterPosition
                notifyDataSetChanged()
            }
        }

    }

}