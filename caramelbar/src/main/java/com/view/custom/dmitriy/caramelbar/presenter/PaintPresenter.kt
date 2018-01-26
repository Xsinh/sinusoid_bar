package com.view.custom.dmitriy.caramelbar.presenter

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import com.view.custom.dmitriy.caramelbar.view.PaintView

/**
 * Created by Dmitriy on 24.01.2018.
 */
class PaintPresenter : PaintView {
    companion object {
        lateinit var paintCaramelBar: Paint
        const val canvasObjectsSize = 8f

        var barShader = LinearGradient(0f, 0f, 100f, 20f, Color.CYAN, Color.GREEN, Shader.TileMode.MIRROR)
    }

    override fun paintInitialize(): Paint {

        paintCaramelBar = Paint(Paint.ANTI_ALIAS_FLAG)
        paintCaramelBar.color = Color.CYAN
        paintCaramelBar.strokeWidth = canvasObjectsSize
        paintCaramelBar.shader = barShader
        //paintCaramelBar.setShadowLayer(8f,0f,36f,Color.DKGRAY)

        return paintCaramelBar
    }
}