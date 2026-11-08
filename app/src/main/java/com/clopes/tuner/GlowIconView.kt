package com.clopes.tuner

import android.content.Context
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.widget.ImageView

/**
 * Icone com halo na propria arte.
 *
 * Desenha copias do vetor levemente ampliadas e tingidas na cor do glow, atras do
 * icone normal. Sem bitmap e sem BlurMaskFilter: nao distorce o desenho.
 */
class GlowIconView(context: Context) : ImageView(context) {

    var glowRadius: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    var glowColor: Int = 0xFFFFFFFF.toInt()
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        val d = drawable
        val glow = glowRadius
        if (d != null && glow > 0f) {
            val previousFilter = d.colorFilter
            val previousAlpha = d.alpha
            val centerX = width / 2f
            val centerY = height / 2f
            for (pass in 0 until 3) {
                val grow = 1f + (glow / width) * (0.5f + pass * 0.6f)
                val save = canvas.save()
                canvas.scale(grow, grow, centerX, centerY)
                d.colorFilter = PorterDuffColorFilter(glowColor, PorterDuff.Mode.SRC_IN)
                d.alpha = (110 - pass * 30).coerceAtLeast(30)
                d.draw(canvas)
                canvas.restoreToCount(save)
            }
            d.colorFilter = previousFilter
            d.alpha = previousAlpha
        }
        super.onDraw(canvas)
    }
}
