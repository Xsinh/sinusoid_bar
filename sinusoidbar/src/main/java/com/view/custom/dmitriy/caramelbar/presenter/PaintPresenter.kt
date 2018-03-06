package com.view.custom.dmitriy.caramelbar.presenter

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.util.Log
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
        const val shadowLayerRadius = 12f
        const val shadowLayerDx = 0f
        const val shadowLayerDy = 20f
        const val shadowLayerColor = Color.DKGRAY

        var barShader = LinearGradient(0f, 0f, 100f, 20f, Color.CYAN, Color.GREEN, Shader.TileMode.MIRROR)
    }

    override fun paintInitialize(): Paint {
        paintCaramelBar = Paint(Paint.ANTI_ALIAS_FLAG)
        paintCaramelBar.strokeWidth = canvasObjectsSize
        paintCaramelBar.shader = barShader

        attributeAcid()

        return paintCaramelBar
    }

    /**
     * Если в xml добавить 'app:acid="true"',
     * то получится интересный эффект :)
     */
    private fun attributeAcid() {
        typedArray = appContext.theme.obtainStyledAttributes(
                attrs,
                R.styleable.SinusoidBar,
                0, 0
        )
        try {
            if (typedArray.getBoolean(R.styleable.SinusoidBar_acid, false)) {
                paintCaramelBar.setShadowLayer( shadowLayerRadius, shadowLayerDx, shadowLayerDy, shadowLayerColor )
            }
        }
        finally {
            typedArray.recycle()
        }
    }
}