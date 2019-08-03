package com.view.custom.dmitriy.caramelbar.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.view.custom.dmitriy.caramelbar.builder.PaintBuilder
import com.view.custom.dmitriy.caramelbar.builder.SinusoidBuilder


/**
 * Created by Dmitriy on 22.01.2018.
 */
class SinusoidBar(context: Context, attrs: AttributeSet) : View(context, attrs), Runnable {

    companion object {
        var sinusoidCoordinateY = 0f

        const val objectSize = 6f
        const val speed = .08f
        const val delay = 20L

        @SuppressLint("StaticFieldLeak")
        lateinit var paintBuilder: PaintBuilder

        val sinusoidBuilderPresenter = SinusoidBuilder()
    }

    init {
        post(this)
        paintBuilder = PaintBuilder(context, attrs)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawPath(
                sinusoidBuilderPresenter.sinusoidStartBuild(
                width/2, height/2,
                sinusoidCoordinateY,
                objectSize
        ), paintBuilder.paintInitialize())
    }

                                        /*Override methods*/

    override fun run() {
        sinusoidCoordinateY += speed
        invalidate()
        sinusoidBuilderPresenter.sinusoidStartBuild( width/2, height/2, sinusoidCoordinateY, objectSize).reset()
        postDelayed( this, delay)
    }
}