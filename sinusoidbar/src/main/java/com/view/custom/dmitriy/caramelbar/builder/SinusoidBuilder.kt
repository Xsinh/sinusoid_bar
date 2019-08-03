package com.view.custom.dmitriy.caramelbar.builder

import android.graphics.Path
import kotlin.math.cos
import kotlin.math.sin

/**
 * Created by Dmitriy on 24.01.2018.
 */
class SinusoidBuilder {

    companion object {
        const val density = 6
        var sinusoidPath = Path()

        const val amplitudeCoefficient = 3
    }

    /**
     * Метод для построения синусоиды по формулам затухающих колебаний,
     * работает анологично интегралу, по dx бертся f(y) = A(dx*sin(y))sin(w*t+fi)
     * @param halfY координата центра [SinusoidBar] по оси Y
     * @param halfX координата центра [SinusoidBar] по оси X
     * @param runnableCoordinateY дифференциал колебания, пересчет деффиринциала необходимо выполнять в [Runnable]
     * @param objectSize размер отрисованных объектов
     */
    fun sinusoidStartBuild(halfX: Int, halfY: Int, runnableCoordinateY: Float, objectSize: Float):  Path = equationBuilder(halfX, halfY, runnableCoordinateY, objectSize)

                                                /*Private members*/

    /**
     * Метод для построения синусоиды по формулам затухающих колебаний,
     * работает анологично интегралу, по dx бертся f(y) = A(dx*sin(y))sin(w*t+fi)
     * @param halfY координата центра [SinusoidBar] по оси Y
     * @param halfX координата центра [SinusoidBar] по оси X
     * @param runnableCoordinateY дифференциал колебания, пересчет дифференциала необходимо выполнять в [Runnable]
     * @param objectSize размер отрисованных объектов
     */
    private fun equationBuilder(halfX: Int, halfY: Int, runnableCoordinateY: Float, objectSize: Float): Path {
        for (dx in 0..halfX step density) {
            sinusoidPath.addCircle(dx.toFloat(), equationForcedOscillation(halfY, dx, runnableCoordinateY), objectSize, Path.Direction.CW)
            sinusoidPath.addCircle(phaseShift(dx, halfX), equationDampedOscillation(halfY, dx, halfX, runnableCoordinateY), objectSize, Path.Direction.CW)
        }
        return sinusoidPath
    }

                                            /*Вынужденное колебание*/
    /**
     * Уравнение вынужденного колебания
     * @param halfY координата центра [SinusoidBar] по оси Y
     * @param halfX координата центра [SinusoidBar] по оси X
     * @param dx дифференциал колебания
     * @param runnableCoordinateY дифференциал колебания, пересчет дифференциал необходимо выполнять в [Runnable]
     */
    private fun equationDampedOscillation(halfY: Int, dx: Int, halfX: Int, runnableCoordinateY: Float) =
            halfY + amplitudeDumpedOscillation(dx, halfX, runnableCoordinateY) / amplitudeCoefficient * sin((dx - halfX) + cos(runnableCoordinateY) * 2)

            /**
             * Амплитуда вынужденного колебания
             * @param dx дифференциал колебания
             * @param halfX координата центра [SinusoidBar] по оси X
             * @param runnableCoordinateY дифференциал колебания, пересчет дифференциала необходимо выполнять в [Runnable]
             */
            private fun amplitudeDumpedOscillation(dx: Int, halfX: Int, runnableCoordinateY: Float) =
                    ((dx - halfX) * sin(-runnableCoordinateY))

                                            /*Затухающее колебание*/
    /**
     * Уравнение затухающего колебания
     * @param halfY координата центра [SinusoidBar] по оси Y
     * @param dx дифференциал колебания
     * @param runnableCoordinateY дифференциал колебания, пересчет дифференциал необходимо выполнять в [Runnable]
     */
    private fun equationForcedOscillation(halfY: Int, dx: Int, runnableCoordinateY: Float) =
            halfY + amplitudeForcedOscillation(dx, runnableCoordinateY) / amplitudeCoefficient * sin(dx.toFloat() + cos(runnableCoordinateY) * 2)

            /**
             * Амплитуда затухающего колебания
             * @param dx дифференциал колебания
             * @param runnableCoordinateY дифференциал колебания, пересчет дифференциала необходимо выполнять в [Runnable]
             */
            private fun amplitudeForcedOscillation(dx: Int, runnableCoordinateY: Float) =
                    (dx * sin(runnableCoordinateY))

    /**
     * Фазовый сдвиг для затухающего колебания
     * @param dx дифференциал колебания
     * @param halfX координата центра [SinusoidBar] по оси X
     */
    private fun phaseShift(dx: Int, halfX: Int) = dx.toFloat() + halfX - density * 2
}