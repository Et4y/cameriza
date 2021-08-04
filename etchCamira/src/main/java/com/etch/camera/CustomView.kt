package com.etch.camera

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class CustomView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {

    // setup initial color
    private var paintColor = Color.BLACK

    // defines paint and canvas
    private var drawPaint: Paint? = null

    // stores next circle
    private val path = Path()


    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setupPaint()
    }


    fun setTextColor(color: Int) {
        drawPaint!!.color = color
    }


    private fun setupPaint() {
        // Setup paint with color and stroke styles
        drawPaint = Paint()
        drawPaint!!.color = paintColor
        drawPaint!!.isAntiAlias = true
        drawPaint!!.strokeWidth = 30f
        drawPaint!!.style = Paint.Style.STROKE
        drawPaint!!.strokeJoin = Paint.Join.ROUND
        drawPaint!!.strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, drawPaint!!)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {

            MotionEvent.ACTION_DOWN -> {
                path.moveTo(pointX, pointY)
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(pointX, pointY)
            }

            else -> return false
        }

        // Force a view to draw again
        postInvalidate()
        return true
    }

}