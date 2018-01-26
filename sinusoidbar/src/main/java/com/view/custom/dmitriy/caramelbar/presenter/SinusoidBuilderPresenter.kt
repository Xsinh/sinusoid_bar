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
class SinusoidBuilderPresenter : SinusoidBuilderView {
    override fun sinusoidBuilder(): Path = sinusoidEquationBuilder(emptyPath, emptyHalfX, emptyHalfY, emptyRunnableCoordinateY, emptyObjextSize)


    companion object {
        const val density = 6
        var sinusoidPath = Path()

        var emptyPath = Path()
        val emptyHalfX: Int = 0
        val emptyHalfY: Int = 0
        val emptyRunnableCoordinateY: Float = 0f
        val emptyObjextSize: Float = 0f
    }

    fun sinusoidEquationBuilder(path: Path, halfX: Int, halfY: Int, runnableCoordinateY: Float, objectSize: Float):  Path{
        for (dx in 0..halfX step density){
            sinusoidPath.addCircle(dx.toFloat(), halfY + ( dx * sin(runnableCoordinateY) ) * sin(dx.toFloat() + cos(runnableCoordinateY)*2), objectSize, Path.Direction.CW)
            sinusoidPath.addCircle(dx.toFloat() + halfX- density *2, halfY + ( (dx - halfX) * sin( -runnableCoordinateY ) ) * sin((dx - halfX) + cos(runnableCoordinateY)*2), objectSize, Path.Direction.CW)
        }
        return sinusoidPath
    }

}