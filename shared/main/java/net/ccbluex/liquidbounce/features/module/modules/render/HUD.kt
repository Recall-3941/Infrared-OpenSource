/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
package net.ccbluex.liquidbounce.features.module.modules.render

import Recall.Clickgui.Jello.font.FontLoaders
import Recall.Utils.render.RoundedUtil
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.api.minecraft.potion.PotionType
import net.ccbluex.liquidbounce.event.*
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.features.module.modules.client.utils.render.ColorUtil
import net.ccbluex.liquidbounce.utils.render.RenderUtils
import net.ccbluex.liquidbounce.value.BoolValue
import net.ccbluex.liquidbounce.value.FloatValue

import net.ccbluex.liquidbounce.value.IntegerValue
import net.ccbluex.liquidbounce.value.ListValue
import net.minecraft.client.gui.ScaledResolution
import java.awt.Color
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.math.pow


@ModuleInfo(name = "HUD", description = "Toggles visibility of the HUD.", category = ModuleCategory.RENDER, array = false)
class HUD : Module() {
    val inventoryParticle = BoolValue("InventoryParticle", false)
    private val blurValue = BoolValue("Blur", false)
    val hueInterpolation = BoolValue("HueInterpolate", false)
    val fontChatValue = BoolValue("FontChat", false)
    val chatRect = BoolValue("ChatRect", false)
    val chatAnimValue = BoolValue("ChatAnimation", true)
    val r = IntegerValue("NovolineRed", 0, 0, 255)
    val g = IntegerValue("NovolineGreen", 255, 0, 255)
    val b = IntegerValue("NovolineBlue", 255, 0, 255)
    val r2 = IntegerValue("NovolineRed2", 255, 0, 255)
    val g2 = IntegerValue("NovolineGreen2", 40, 0, 255)
    val b2 = IntegerValue("NovolineBlue2", 255, 0, 255)
    val Radius = IntegerValue("BlurRadius", 10 , 1 , 50 )
    public val BlurStrength = FloatValue("BlurStrength", 15F, 0f, 30F)
    val rainbowStart = FloatValue("RainbowStart", 0.41f, 0f, 1f)
    val rainbowBrightness = FloatValue("RainbowBrightness", 1f, 0f, 1f)
    val rainbowSpeed = IntegerValue("RainbowSpeed", 1500, 500, 7000)
    val rainbowSaturation = FloatValue("RainbowSaturation", 0.7f, 0f, 1f)
    val rainbowStop = FloatValue("RainbowStop", 0.58f, 0f, 1f)
    val hotbar = BoolValue("Hotbar", true)
    val clolormode = ListValue("ColorMode", arrayOf(
            "Rainbow",
            "Light Rainbow",
            "Static",
            "Double Color",
            "Default"
    ), "Light Rainbow")
    private val colorRedValue = IntegerValue("R", 255, 0, 255)
    private val colorGreenValue = IntegerValue("G", 255, 0, 255)
    private val colorBlueValue = IntegerValue("B", 255, 0, 255)
    val movingcolors = BoolValue("MovingColors", false)
    fun getClientColors(): Array<Color>? {
        val firstColor: Color
        val secondColor: Color
        when (clolormode.get().toLowerCase()) {
            "light rainbow" -> {
                firstColor = ColorUtil.rainbow(15, 1, .6f, 1f, 1f)
                secondColor = ColorUtil.rainbow(15, 40, .6f, 1f, 1f)
            }

            "rainbow" -> {
                firstColor = ColorUtil.rainbow(15, 1, 1f, 1f, 1f)
                secondColor = ColorUtil.rainbow(15, 40, 1f, 1f, 1f)
            }

            "double color" -> {
                firstColor = ColorUtil.interpolateColorsBackAndForth(15, 0, Color.PINK, Color.BLUE, hueInterpolation.get())
                secondColor = ColorUtil.interpolateColorsBackAndForth(15, 90, Color.PINK, Color.BLUE, hueInterpolation.get())
            }

            "static" -> {
                firstColor = Color(colorRedValue.get(), colorGreenValue.get(), colorBlueValue.get())
                secondColor = firstColor
            }

            else -> {
                firstColor = Color(-1)
                secondColor = Color(-1)
            }
        }
        return arrayOf(firstColor, secondColor)
    }
    var ticks = 0.0f
    private var easingHealth: Float = 0F
    private var easingarmor: Float = 0F
    private var easingxp: Float = 0F
    private var easingfood: Float = 0F
    private val decimalFormat = DecimalFormat("##", DecimalFormatSymbols(Locale.ENGLISH))
    @EventTarget
    fun onRender2D(event: Render2DEvent?) {
        val sr = ScaledResolution(mc2)
        val left = sr.scaledWidth / 2 + 91
        val top= sr.scaledHeight - 50
        val x = left - 1 * 8 - 180
        if (!hotbar.get() && mc.thePlayer != null && mc.theWorld != null) {
            RoundedUtil.drawRound(x.toFloat(), top.toFloat(), 100F, 9f, 0f, Color(20, 23, 22))
            var color =  Color(252, 76, 34)
            if (easingHealth <= 0f ){
                easingHealth  = 0F
            }
            if (easingHealth >= mc.thePlayer!!.maxHealth ){
                easingHealth  = mc.thePlayer!!.maxHealth
            }
            if (easingarmor <= 0){
                easingarmor = 0F
            }
            if (easingarmor >= 20f){
                easingarmor = 20F
            }
            if (easingfood <= 0){
                easingfood = 0F
            }
            if (easingfood >= 20f){
                easingfood = 20F
            }
            if (mc.thePlayer!!.isPotionActive(classProvider.getPotionEnum(PotionType.REGENERATION))){
                color = Color(244 , 143 , 177)
            }
            RoundedUtil.drawRound(
                    x.toFloat(),
                    top.toFloat(),
                    (easingHealth / mc.thePlayer!!.maxHealth) * 100F,
                    10f,
                    0f,
                    color
            )
            RoundedUtil.drawRound(x.toFloat(), top.toFloat() - 3f - 15f, 100f, 10f, 0f, Color(20, 23, 22))
            RoundedUtil.drawRound(
                    x.toFloat(),
                    top.toFloat() - 3f - 15f,
                    (easingarmor / 20f) * 100F,
                    10f,
                    0f,
                    Color(66,148,252)
            )
            RoundedUtil.drawRound(x.toFloat() + 110F, top.toFloat() - 3f - 15f, 100f, 10f, 0f, Color(20, 23, 22))
            RoundedUtil.drawRound(
                    x.toFloat() + 110F,
                    top.toFloat() - 3f - 15f,
                    easingxp,
                    10f,
                    0f,
                    Color(170 , 226 , 177)
            )
            RoundedUtil.drawRound(x.toFloat() + 110F, top.toFloat(), 100f, 10f, 0f, Color(20, 23, 22))
            RoundedUtil.drawRound(
                    x.toFloat() + 110F,
                    top.toFloat(),
                    (easingfood / 20F) * 100F,
                    10f,
                    0f,
                    Color(251,162,44)
            )
            FontLoaders.JelloM14.drawString(
                    decimalFormat.format((easingarmor / 20f) * 100) + "%",
                    x.toFloat() +2,
                    ((top + 5 - FontLoaders.JelloM14.height / 2).toFloat()) - 3f - 15f,
                    -1
            )
            FontLoaders.JelloM14.drawString(
                    decimalFormat.format((easingHealth / mc.thePlayer!!.maxHealth) * 100) + "%",
                    x.toFloat() +2,
                    ((top + 5 - FontLoaders.JelloM14.height / 2).toFloat()),
                    -1
            )
            FontLoaders.JelloM14.drawString(
                    "Lv" + mc2.player.experienceLevel.toString()
                    ,
                    x.toFloat()+110F +2,
                    ((top + 5 - FontLoaders.JelloM14.height / 2).toFloat()) - 3f - 15f,
                    -1
            )
            FontLoaders.JelloM14.drawString(
                    decimalFormat.format((easingfood / 20F) * 100F) + "%",
                    x.toFloat()+110F +2,
                    ((top + 5 - FontLoaders.JelloM14.height / 2).toFloat()),
                    -1
            )
            easingfood += (mc2.player.foodStats.foodLevel - easingfood) / 2.0F.pow(10.0F - 5F) * RenderUtils.deltaTime
            easingxp += ((mc2.player.experience * 100F) - easingxp) / 2.0F.pow(10.0F - 5F) * RenderUtils.deltaTime
            easingHealth += ((mc.thePlayer!!.health - easingHealth) / 2.0F.pow(10.0F - 5F)) * RenderUtils.deltaTime
            easingarmor += ((mc2.player.totalArmorValue - easingarmor) / 2.0F.pow(10.0F - 5F)) * RenderUtils.deltaTime
        }
        if (classProvider.isGuiHudDesigner(mc.currentScreen))
            return

        LiquidBounce.hud.render(false)
    }

    @EventTarget
    fun onUpdate(event: UpdateEvent?) {
        LiquidBounce.hud.update()
    }

    @EventTarget
    fun onKey(event: KeyEvent) {
        LiquidBounce.hud.handleKey('a', event.key)
    }

    @EventTarget(ignoreCondition = true)
    fun onScreen(event: ScreenEvent) {
        if (mc.theWorld == null || mc.thePlayer == null) return
        if (state && blurValue.get() && !mc.entityRenderer.isShaderActive() && event.guiScreen != null &&
                !(classProvider.isGuiChat(event.guiScreen) || classProvider.isGuiHudDesigner(event.guiScreen))) mc.entityRenderer.loadShader(classProvider.createResourceLocation("liquidbounce" + "/blur.json")) else if (mc.entityRenderer.shaderGroup != null &&
                mc.entityRenderer.shaderGroup!!.shaderGroupName.contains("liquidbounce/blur.json")) mc.entityRenderer.stopUseShader()
    }

    init {
        state = true
    }
}