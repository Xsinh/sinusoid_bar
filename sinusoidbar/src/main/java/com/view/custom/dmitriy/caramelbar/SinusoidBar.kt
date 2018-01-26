package com.view.custom.dmitriy.caramelbar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.view.custom.dmitriy.caramelbar.presenter.PaintPresenter
import com.view.custom.dmitriy.caramelbar.presenter.SinusoidBuilderPresenter
import com.view.custom.dmitriy.caramelbar.presenter.SinusoidBuilderPresenter.Companion.density
import com.view.custom.dmitriy.caramelbar.view.PaintView
import com.view.custom.dmitriy.caramelbar.view.SinusoidBuilderView
import kotlin.math.cos
import kotlin.math.sin

/**
 * Created by Dmitriy on 22.01.2018.
 */
class SinusoidBar(context: Context, attrs: AttributeSet?) : View(context, attrs), Runnable, PaintView, SinusoidBuilderView{

    init {
        post(this)
    }

    companion object {
        var sinusoidCoordinateY = 0f
        var sinusoidPath = Path()

        val paintPresenter = PaintPresenter()
        val sinusoidBuilderPresenter = SinusoidBuilderPresenter()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas!!.drawPath(sinusoidBuilder(), paintInitialize())
    }



    /*Override methods*/

    override fun run() {
        sinusoidCoordinateY += 0.08f

        invalidate()
        sinusoidBuilder().reset()
        postDelayed( this, 20)
    }

    override fun paintInitialize(): Paint = paintPresenter.paintInitialize()

    override fun sinusoidBuilder(): Path = sinusoidBuilderPresenter.sinusoidEquationBuilder(sinusoidPath, width/2, height/2, sinusoidCoordinateY, 6f)
    
}