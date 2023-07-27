package net.ccbluex.liquidbounce.features.module.modules.client

import Recall.Utils.render.RoundedUtil
import net.ccbluex.liquidbounce.features.module.modules.client.utils.render.ColorUtil
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.Render2DEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.features.module.modules.render.HUD
import net.ccbluex.liquidbounce.ui.font.Fonts
import net.ccbluex.liquidbounce.value.TextValue
import java.awt.Color


@ModuleInfo(name = "Title",description="Skid", category = ModuleCategory.CLIENT)
class Title : Module() {

    private val clientname = TextValue("ClientName", "im legit")

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
                4.5F,
                12.0.toDouble().toFloat(),
                (Fonts.com120.getStringWidth(LiquidBounce.CLIENT_NAME) + 7 - 2).toDouble().toFloat(), 27.0F,
                9f,
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
        Recall.Novoline.Font.Fonts.SFBOLD.SFBOLD_22.SFBOLD_22.drawString(LiquidBounce.CLIENT_NAME,
                13.0, 15.5,Color.WHITE.rgb,true)
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

