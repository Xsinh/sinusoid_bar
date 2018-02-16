package com.view.custom.dmitriy.caramelbar.presenter

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import com.view.custom.dmitriy.caramelbar.R
import com.view.custom.dmitriy.caramelbar.view.PaintView

/**
 * Created by Dmitriy on 24.01.2018.
 */
class PaintPresenter(context: Context, attributeSet: AttributeSet?) : PaintView {
    private val appContext = context
    private val attrs = attributeSet

    companion object {
        lateinit var paintCaramelBar: Paint
        lateinit var typedArray: TypedArray

        const val canvasObjectsSize = 8f

        var barShader = LinearGradient(0f, 0f, 100f, 20f, Color.CYAN, Color.GREEN, Shader.TileMode.MIRROR)
    }

    override fun paintInitialize(): Paint {
        paintCaramelBar = Paint(Paint.ANTI_ALIAS_FLAG)
        paintCaramelBar.strokeWidth = canvasObjectsSize
        paintCaramelBar.shader = barShader

        typedArray = appContext.theme.obtainStyledAttributes(
                attrs,
                R.styleable.SinusoidBar,
                0,0
        )
        try {
            if( typedArray.getBoolean(R.styleable.SinusoidBar_acid, false) ){
                paintCaramelBar.setShadowLayer(8f,0f,36f,Color.DKGRAY)
            }
        }finally {
            typedArray.recycle()
        }

        return paintCaramelBar
    }
}