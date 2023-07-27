package Recall.Clickgui.New.extensions

import Recall.Clickgui.NewGui
import net.ccbluex.liquidbounce.utils.render.AnimationUtils
import net.ccbluex.liquidbounce.utils.render.RenderUtils

fun Float.animSmooth(target: Float, speed: Float) = if (NewGui.fastRenderValue.get()) target else AnimationUtils.animate(target, this, speed * RenderUtils.deltaTime * 0.025F)
fun Float.animLinear(speed: Float, min: Float, max: Float) = if (NewGui.fastRenderValue.get()) { if (speed < 0F) min else max } else (this + speed).coerceIn(min, max)