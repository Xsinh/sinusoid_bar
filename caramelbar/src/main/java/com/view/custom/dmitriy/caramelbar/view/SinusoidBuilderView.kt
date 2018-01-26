package com.view.custom.dmitriy.caramelbar.view

import android.graphics.Path

/**
 * Created by Dmitriy on 24.01.2018.
 */
interface SinusoidBuilderView {

    fun sinusoidBuilder(path: Path, runnableCoordinateY: Float, objectSize: Float): Path
}