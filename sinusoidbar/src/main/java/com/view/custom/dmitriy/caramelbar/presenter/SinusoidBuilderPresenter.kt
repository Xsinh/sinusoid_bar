package com.view.custom.dmitriy.caramelbar.presenter

import android.content.Context
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.view.custom.dmitriy.caramelbar.view.SinusoidBuilderView
import kotlin.math.cos
import kotlin.math.sin

/**
 * Created by Dmitriy on 24.01.2018.
 */
class SinusoidBuilderPresenter(context: Context?, attrs: AttributeSet?) : View(context, attrs), SinusoidBuilderView {

    companion object {
        const val density = 6
    }

    override fun sinusoidBuilder(path: Path, runnableCoordinateY: Float, objectSize: Float): Path {
        for (dx in 0..width/2 step density){
            path.addCircle(dx.toFloat(), height/2 + ( dx * sin(runnableCoordinateY) ) * sin(dx.toFloat() + cos(runnableCoordinateY)*2), 6f, Path.Direction.CW)
            path.addCircle(dx.toFloat() + width/2 - density *2, height/2 + ( (dx - width/2) * sin( -runnableCoordinateY ) ) * sin((dx - width/2) + cos(runnableCoordinateY)*2), objectSize, Path.Direction.CW)
        }
        return path
    }
}