package net.ccbluex.liquidbounce.ui.client.hud.element.elements

import Recall.Utils.render.DrRenderUtils
import Recall.Utils.render.RoundedUtil
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.features.module.modules.client.CustomColor
import net.ccbluex.liquidbounce.features.module.modules.client.utils.render.ColorUtil
import net.ccbluex.liquidbounce.features.module.modules.render.GodLightSync
import net.ccbluex.liquidbounce.features.module.modules.render.HUD
import net.ccbluex.liquidbounce.ui.client.hud.element.Border
import net.ccbluex.liquidbounce.ui.client.hud.element.Element
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo
import net.ccbluex.liquidbounce.ui.client.hud.element.Side
import net.ccbluex.liquidbounce.ui.font.Fonts
import net.ccbluex.liquidbounce.utils.render.EaseUtils
import net.ccbluex.liquidbounce.utils.render.RenderUtils
import net.ccbluex.liquidbounce.value.FloatValue
import net.ccbluex.liquidbounce.value.ListValue
import net.minecraft.client.renderer.GlStateManager
import org.lwjgl.opengl.GL11
import java.awt.Color
import java.math.BigDecimal

/**
 * CustomHUD Notification element
 */
@ElementInfo(name = "Notifications", single = true)
class Notifications(x: Double = 143.0, y: Double = 30.0, scale: Float = 1.05F,
            side: Side = Side(Side.Horizontal.RIGHT, Side.Vertical.DOWN)) : Element(x, y, scale, side) {
    /**
     * Example notification for CustomHUD designer
     */
    val blurStrength = FloatValue("Strength", 0F, 0F, 30F)
    val mode = ListValue("Mode", arrayOf("Novoline", "New", "Tenacity2"), "Novoline")
    private val exampleNotification = Notification("Notification", "This is an example notification.", NotifyType.INFO)

    /**
     * Draw element
     */

    override fun drawElement(): Border? {
        val notifications = mutableListOf<Notification>()
        //FUCK YOU java.util.ConcurrentModificationException
        for ((index, notify) in LiquidBounce.hud.notifications.withIndex()) {
            GL11.glPushMatrix()

            if (notify.drawNotification(index,this)){
                notifications.add(notify)
            }
            GL11.glPopMatrix()
        }
        for (notify in notifications) {
            LiquidBounce.hud.notifications.remove(notify)
        }
        //val Notification = LiquidBounce.hud.notifications


        if (classProvider.isGuiHudDesigner(mc.currentScreen)) {
            if (!LiquidBounce.hud.notifications.contains(exampleNotification))
                LiquidBounce.hud.addNotification(exampleNotification)

            exampleNotification.fadeState = FadeState.STAY
            exampleNotification.displayTime = System.currentTimeMillis()
            //            exampleNotification.x = exampleNotification.textLength + 8F

            return Border(-exampleNotification.width.toFloat() + 80, -exampleNotification.height.toFloat()-24.5f, 80F, -24.5F)
        }

        return null
    }

}

class Notification(val title: String, val content: String, val type: NotifyType, val time: Int = 2000, val animeTime: Int = 500) {
    var fadeState = FadeState.IN
    val height = 30
    var nowY = -height
    var string = ""
    var displayTime = System.currentTimeMillis()
    var animeXTime = System.currentTimeMillis()
    var animeYTime = System.currentTimeMillis()
    val width = Fonts.Posterama30.getStringWidth(content) + 53
    fun drawCircle(x: Float, y: Float, radius: Float, start: Int, end: Int) {
        GlStateManager.enableBlend()
        GlStateManager.disableTexture2D()
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO)
        GL11.glEnable(GL11.GL_LINE_SMOOTH)
        GL11.glLineWidth(2f)
        GL11.glBegin(GL11.GL_LINE_STRIP)
        var i = end.toFloat()
        val GodLightSync = LiquidBounce.moduleManager.getModule(GodLightSync::class.java) as GodLightSync
        while (i >= start) {
            var c = RenderUtils.getGradientOffset(Color(GodLightSync.r.get(),GodLightSync.g.get(),GodLightSync.b.get()), Color(GodLightSync.r.get(),GodLightSync.g.get(),GodLightSync.b.get(), 1), (Math.abs(System.currentTimeMillis() / 360.0 + (i* 34 / 360) * 56 / 100) / 10)).rgb
            val f2 = (c shr 24 and 255).toFloat() / 255.0f
            val f22 = (c shr 16 and 255).toFloat() / 255.0f
            val f3 = (c shr 8 and 255).toFloat() / 255.0f
            val f4 = (c and 255).toFloat() / 255.0f
            GlStateManager.color(f22, f3, f4, f2)
            GL11.glVertex2f(
                    (x + Math.cos(i * Math.PI / 180) * (radius * 1.001f)).toFloat(),
                    (y + Math.sin(i * Math.PI / 180) * (radius * 1.001f)).toFloat()
            )
            i -= 360f / 90.0f
        }
        GL11.glEnd()
        GL11.glDisable(GL11.GL_LINE_SMOOTH)
        GlStateManager.enableTexture2D()
        GlStateManager.disableBlend()
    }
    /**
     * Draw notification
     */
    fun drawNotification(index: Int,noti: Notifications): Boolean {
        val realY = -(index + 1) * (height + 10)
        val nowTime = System.currentTimeMillis()
        //Y-Axis Animation
        if (nowY != realY) {
            var pct = (nowTime - animeYTime) / animeTime.toDouble()
            if (pct > 1) {
                nowY = realY
                pct = 1.0
            } else {
                pct = EaseUtils.easeOutBack(pct)
            }
            GL11.glTranslated(0.0, (realY - nowY) * pct, 0.0)
        } else {
            animeYTime = nowTime
        }
        GL11.glTranslated(0.0, nowY.toDouble(), 0.0)

        //X-Axis Animation
        var pct = (nowTime - animeXTime) / animeTime.toDouble()
        when (fadeState) {
            FadeState.IN -> {
                if (pct > 1) {
                    fadeState = FadeState.STAY
                    animeXTime = nowTime
                    pct = 1.0
                }
                pct = EaseUtils.easeOutBack(pct)
            }

            FadeState.STAY -> {
                pct = 1.0
                if ((nowTime - animeXTime) > time) {
                    fadeState = FadeState.OUT
                    animeXTime = nowTime
                }
            }

            FadeState.OUT -> {
                if (pct > 1) {
                    fadeState = FadeState.END
                    animeXTime = nowTime
                    pct = 1.0
                }
                pct = 1 - EaseUtils.easeInBack(pct)
            }

            FadeState.END -> {
                return true
            }
        }
        var string1 = ""

        if (type.toString() == "SUCCESS") {
            string = "a"
            string1 = "o"
        }
        if (type.toString() == "ERROR") {
            string = "B"
            string1 = "p"
        }
        if (type.toString() == "WARNING") {
            string = "D"
            string1 = "r"
        }
        if (type.toString() == "INFO") {
            string = "C"
            string1 = "m"
        }
        val originalX = noti.renderX.toFloat()
        val originalY = noti.renderY.toFloat()

        when (noti.mode.get().toLowerCase()) {
            "novoline" -> {
                GlStateManager.resetColor()
                val GodLightSync = LiquidBounce.moduleManager.getModule(GodLightSync::class.java) as GodLightSync
                GL11.glScaled(pct, pct, pct)
                GL11.glTranslatef(-width.toFloat() / 2, -height.toFloat() / 2, 0F)
                RenderUtils.drawRect(0F, 0F, width.toFloat(), height.toFloat(), Color(63, 63, 63, 40))
                RenderUtils.drawShadowWithCustomAlpha(0f, 0f, width.toFloat(), height.toFloat(), 255f)
                RenderUtils.drawGradientSideways(
                        0.0,
                        height - 1.7,
                        (width * ((nowTime - displayTime) / (animeTime * 2F + time))).toDouble(),
                        height.toDouble(),
                        Color(GodLightSync.r.get(), GodLightSync.g.get(), GodLightSync.b.get()).rgb,
                        Color(GodLightSync.r2.get(), GodLightSync.g2.get(), GodLightSync.b2.get()).rgb
                )
                Fonts.Posterama35.drawString(title, 24.5F.toInt(), 7, Color.WHITE.rgb)
                Fonts.Posterama35.drawString(
                        "$content (" + BigDecimal(((time - time * ((nowTime - displayTime) / (animeTime * 2F + time))) / 1000).toDouble()).setScale(
                                1,
                                BigDecimal.ROUND_HALF_UP
                        ).toString() + "s)",
                        24.5F.toInt(), 17.3F.toInt(), Color.WHITE.rgb
                )
                RenderUtils.drawFilledCircle(13, 15, 8.5F, Color.BLACK)
                Fonts.nico80.drawString(string, 4f, 8f, Color.WHITE.rgb)
                drawCircle(12.9f, 15.0f, 8.8f, 0, 360)
                GlStateManager.resetColor()
            }
            "new" -> {
                var gradientColor1 = Color(CustomColor.r.get(), CustomColor.g.get(), CustomColor.b.get(), CustomColor.a.get())
                var gradientColor2 = Color(CustomColor.r.get(), CustomColor.g.get(), CustomColor.b.get(), CustomColor.a.get())
                var gradientColor3 = Color(CustomColor.r2.get(), CustomColor.g2.get(), CustomColor.b2.get(), CustomColor.a2.get())
                var gradientColor4 = Color(CustomColor.r2.get(), CustomColor.g2.get(), CustomColor.b2.get(), CustomColor.a2.get())
                RoundedUtil.drawGradientRound(
                        0f,
                        0f,
                        width.toFloat(),
                        height.toFloat(),
                        CustomColor.ra.get(), ColorUtil.applyOpacity(gradientColor4, .85f), gradientColor1, gradientColor3, gradientColor2)
                Fonts.bold35.drawStringWithShadow(title, 24.5F.toInt(), 7, Color.WHITE.rgb)
                Fonts.bold35.drawStringWithShadow(
                        content,
                        24.5F.toInt(), 17.3F.toInt(), Color.WHITE.rgb
                )
                Fonts.nico80.drawString(string, 4f, 8f, Color.WHITE.rgb)
                drawCircle(12.9f, 15.0f, 8.8f, 0, 360)
                GlStateManager.resetColor()
            }
            "tenacity2" -> {

                val hudMod = LiquidBounce.moduleManager.getModule(
                        HUD::class.java
                ) as HUD

                fun getClientColor2(): Color {
                    return Color(236, 133, 209)
                }

                fun getAlternateClientColor2(): Color {
                    return Color(28, 167, 222)
                }
                val gradientColor1 = RenderUtils.interpolateColorsBackAndForth(
                        15,
                        0,
                        getClientColor2(),
                        getAlternateClientColor2(),
                        hudMod.hueInterpolation.get()
                )
                val gradientColor2 = RenderUtils.interpolateColorsBackAndForth(
                        15,
                        90,
                        getClientColor2(),
                        getAlternateClientColor2(),
                        hudMod.hueInterpolation.get()
                )
                val gradientColor3 = RenderUtils.interpolateColorsBackAndForth(
                        15,
                        180,
                        getClientColor2(),
                        getAlternateClientColor2(),
                        hudMod.hueInterpolation.get()
                )
                val gradientColor4 = RenderUtils.interpolateColorsBackAndForth(
                        15,
                        270,
                        getClientColor2(),
                        getAlternateClientColor2(),
                        hudMod.hueInterpolation.get()
                )
                GL11.glScaled(pct, pct, pct)
                GL11.glTranslatef(-width.toFloat() / 2 + 15, -height.toFloat() / 2, 0F)
                RoundedUtil.drawGradientRound(
                        0f,
                        0f,
                        width.toFloat(),
                        height.toFloat(),
                        6f,
                        DrRenderUtils.applyOpacity(gradientColor4, .85f),
                        gradientColor1,
                        gradientColor3,
                        gradientColor2
                )
                Recall.Novoline.Font.Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.drawString(title, 24.5F.toInt(), 7, Color.WHITE.rgb)
                Recall.Novoline.Font.Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.drawString(
                        content,
                        24.5F.toInt(), 17.3F.toInt(), Color.WHITE.rgb
                )
                Fonts.nico80.drawString(string, 4f, 8f, Color.WHITE.rgb)
                drawCircle(12.9f, 15.0f, 8.8f, 0, 360)
                GlStateManager.resetColor()
            }
        }

        return false
    }
}


enum class NotifyType(var icon: String) {
    SUCCESS("check-circle"),
    ERROR("close-circle"),
    WARNING("warning"),
    INFO("information");
}


enum class FadeState { IN, STAY, OUT, END }


