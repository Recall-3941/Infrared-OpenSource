package net.ccbluex.liquidbounce.features.module.modules.client

import Recall.Novoline.Font.Fonts
import Recall.Utils.render.RoundedUtil
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.Render2DEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.features.module.modules.client.utils.render.ColorUtil
import net.ccbluex.liquidbounce.features.module.modules.render.HUD
import net.ccbluex.liquidbounce.utils.EntityUtils
import net.ccbluex.liquidbounce.value.FloatValue
import java.awt.Color


@ModuleInfo(name = "PingInfo",description="sb", category = ModuleCategory.CLIENT)
class PingInfo : Module() {


    val xValue = FloatValue("X", 5.0f, 0.0f, 2560.0f)
    val yValue = FloatValue("Y", 5.0f, 0.0f, 1440.0f)

    private var gradientColor1 =
            Color.WHITE
    private  var gradientColor2:java.awt.Color? = java.awt.Color.WHITE
    private  var gradientColor3:java.awt.Color? = java.awt.Color.WHITE
    private  var gradientColor4:java.awt.Color? = java.awt.Color.WHITE
    @EventTarget
    fun onRender(event: Render2DEvent){

        val hudMod = LiquidBounce.moduleManager.getModule(HUD::class.java) as HUD

        gradientColor1 = ColorUtil.interpolateColorsBackAndForth(
                15,
                0,
                getClientColor(),
                getAlternateClientColor(),
                hudMod.hueInterpolation.get()
        )
        gradientColor2 = ColorUtil.interpolateColorsBackAndForth(
                15,
                90,
                getClientColor(),
                getAlternateClientColor(),
                hudMod.hueInterpolation.get()
        )
        gradientColor3 = ColorUtil.interpolateColorsBackAndForth(
                15,
                180,
                getClientColor(),
                getAlternateClientColor(),
                hudMod.hueInterpolation.get()
        )
        gradientColor4 = ColorUtil.interpolateColorsBackAndForth(
                15,
                270,
                getClientColor(),
                getAlternateClientColor(),
                hudMod.hueInterpolation.get()
        )
        RoundedUtil.drawGradientRound(
                xValue.get() - 8.5f,
                yValue.get() - 2.0f,
                (Fonts.SFBOLD.SFBOLD_22.SFBOLD_22.stringWidth(EntityUtils.getPing(mc.thePlayer!!).toString()+" ms") + 17 ).toDouble().toFloat(), Fonts.SFBOLD.SFBOLD_22.SFBOLD_22.height+5F,
                4f,
                ColorUtil.applyOpacity(gradientColor4, .85f),
                gradientColor1,
                gradientColor3,
                gradientColor2
        )
        /*RenderUtil.drawGradientRect2(
            3.0, 10.0.toDouble(), (Fonts.com96.getStringWidth(LiquidBounce.CLIENT_NAME) + 17+17 - 2).toDouble(), 23.0, ColorUtil.applyOpacity(
                Color.BLACK, .2f
            ).rgb, ColorUtil.applyOpacity(Color.BLACK, 0f).rgb
        )*/
        Recall.Novoline.Font.Fonts.SFBOLD.SFBOLD_22.SFBOLD_22.drawString(
                EntityUtils.getPing(mc.thePlayer!!).toString()+" ms",
                xValue.get().toDouble(), yValue.get().toDouble(),Color.WHITE.rgb,true)
        //Fonts.com35.drawStringWithShadow(LiquidBounce.CLIENT_VERSION.toString(),Fonts.com96.getStringWidth(LiquidBounce.CLIENT_NAME) + 13, 14,Color.WHITE.rgb)
        //Fonts.com35.drawStringWithShadow(clientname.get(),Fonts.com96.getStringWidth(LiquidBounce.CLIENT_NAME) + 13, 24,Color.WHITE.rgb)


    }

    fun getClientColor(): Color {
        val hudMod = LiquidBounce.moduleManager.getModule(HUD::class.java) as HUD
        return Color(hudMod.r.get(), hudMod.g.get(), hudMod.b.get(), 255)
    }

    fun getAlternateClientColor(): Color {
        val hudMod = LiquidBounce.moduleManager.getModule(HUD::class.java) as HUD
        return Color(hudMod.r2.get(), hudMod.g2.get(), hudMod.b2.get(), 255)
    }
}

