package com.view.custom.dmitriy.caramelbar.builder

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import com.view.custom.dmitriy.caramelbar.R

/**
 * Created by Dmitriy on 24.01.2018.
 */
class PaintBuilder(context: Context, attributeSet: AttributeSet?) {
    private val appContext = context
    private val attrs = attributeSet

    companion object {
        lateinit var paintCaramelBar: Paint
        lateinit var typedArray: TypedArray

        const val canvasObjectsSize = 8f
        const val shadowLayerRadius = 12f
        const val shadowLayerDx = 0f
        const val shadowLayerDy = 20f
        const val shadowLayerColor = Color.DKGRAY

        var barShader = LinearGradient(0f, 0f, 100f, 20f, Color.CYAN, Color.GREEN, Shader.TileMode.MIRROR)
    }

     fun paintInitialize(): Paint {
         paintCaramelBar = Paint(Paint.ANTI_ALIAS_FLAG)
         paintCaramelBar.strokeWidth = canvasObjectsSize
         paintCaramelBar.shader = barShader

         attributeAcid()
         return paintCaramelBar
    }

     private fun attributeAcid() {
        typedArray = appContext.theme.obtainStyledAttributes(
                this.attrs,
                R.styleable.SinusoidBar,
                0, 0
        )
        val style = typedArray.getBoolean(R.styleable.SinusoidBar_acid, true)
            if (style) {
                paintCaramelBar.setShadowLayer( shadowLayerRadius, shadowLayerDx, shadowLayerDy, shadowLayerColor )

            }
        typedArray.recycle()
    }
}