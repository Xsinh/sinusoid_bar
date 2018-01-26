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

    var sinusoidBuilderPresenter = SinusoidBuilderPresenter(context, attrs)

    init {
        post(this)
    }

    companion object {
        var lineCoordinateY = 0f
        var path = Path()
        var paintPresenter = PaintPresenter()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas!!.drawPath(pathCreator(), paintInitialize())
    }



    /*Override methods*/

    override fun run() {
        lineCoordinateY += 0.08f

        invalidate()
        path.reset()
        postDelayed( this, 20)
    }

    override fun paintInitialize(): Paint = paintPresenter.paintInitialize()

    override fun sinusoidBuilder(path: Path, runnableCoordinateY: Float, objectSize: Float): Path {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

                                    /*Private members*/

    private fun pathCreator(): Path {
        for (dx in 0..width/2 step density){
            path.addCircle(dx.toFloat(), height/2 + ( dx * sin(lineCoordinateY) ) * sin(dx.toFloat() + cos(lineCoordinateY)*2), 6f, Path.Direction.CW)
            path.addCircle(dx.toFloat()+width/2 - density *2, height/2 + ( (dx - width/2) * sin(-lineCoordinateY) ) * sin((dx - width/2) + cos(lineCoordinateY)*2), 6f, Path.Direction.CW)
        }
        return path
    }
}