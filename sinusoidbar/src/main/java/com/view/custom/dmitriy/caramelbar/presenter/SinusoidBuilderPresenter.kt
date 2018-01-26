package com.view.custom.dmitriy.caramelbar.presenter

import android.graphics.Path
import com.view.custom.dmitriy.caramelbar.view.SinusoidBuilderView
import kotlin.math.cos
import kotlin.math.sin

/**
 * Created by Dmitriy on 24.01.2018.
 */
class SinusoidBuilderPresenter : SinusoidBuilderView {
    override fun sinusoidBuilder(): Path = sinusoidEquationBuilder( emptyHalfX, emptyHalfY, emptyRunnableCoordinateY, emptyObjectSize)


    companion object {
        const val density = 6
        var sinusoidPath = Path()

        const val emptyHalfX: Int = 0
        const val emptyHalfY: Int = 0
        const val emptyRunnableCoordinateY: Float = 0f
        const val emptyObjectSize: Float = 0f
    }

    fun sinusoidEquationBuilder( halfX: Int, halfY: Int, runnableCoordinateY: Float, objectSize: Float):  Path = equation(halfX, halfY, runnableCoordinateY, objectSize)

                                                /*Private members*/

    private fun equation(halfX: Int, halfY: Int, runnableCoordinateY: Float, objectSize: Float): Path {
        for (dx in 0..halfX step density) {
            sinusoidPath.addCircle(dx.toFloat(), halfY + (dx * sin(runnableCoordinateY)) * sin(dx.toFloat() + cos(runnableCoordinateY) * 2), objectSize, Path.Direction.CW)
            sinusoidPath.addCircle(dx.toFloat() + halfX - density * 2, halfY + ((dx - halfX) * sin(-runnableCoordinateY)) * sin((dx - halfX) + cos(runnableCoordinateY) * 2), objectSize, Path.Direction.CW)
        }
        return sinusoidPath
    }
}