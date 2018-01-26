package com.view.custom.dmitriy.caramelbar.view

import android.graphics.Paint
import android.util.AttributeSet

/**
 * Created by Dmitriy on 24.01.2018.
 */
interface PaintView {

    /**
     * Метод стилизации объектов при отрисовке
     * @param attrs парсинг стилей из xml
     */
    fun paintInitialize(): Paint
}