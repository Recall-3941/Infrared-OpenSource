package Recall.Ui

import Recall.Ui.targets.TargetStyle
import Recall.Ui.targets.impl.*
import Recall.Ui.targets.impl.Chill
import Recall.Ui.targets.impl.Moon
import Recall.Ui.targets.impl.Rice
import Recall.Ui.targets.impl.Slowly


import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityLivingBase
import net.ccbluex.liquidbounce.features.module.modules.render.ColorMixer
//import net.ccbluex.liquidbounce.api.minecraft.client.entity.player.IEntityPlayer
import net.ccbluex.liquidbounce.features.module.modules.combat.KillAura
//import net.ccbluex.liquidbounce.features.module.modules.combat.TeleportAura
//import net.ccbluex.liquidbounce.features.module.modules.color.ColorMixer
import net.ccbluex.liquidbounce.ui.client.hud.element.Border
import net.ccbluex.liquidbounce.ui.client.hud.element.Element
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo

import net.ccbluex.liquidbounce.utils.render.*
import net.ccbluex.liquidbounce.value.*
import net.minecraft.client.gui.GuiChat
import net.minecraft.client.renderer.GlStateManager
//import net.minecraft.util.MathHelper
import org.lwjgl.opengl.GL11
import java.awt.Color

/**
 * A target hud
 */
@ElementInfo(name = "LBPLUSTarget", disableScale = true)
class LBplusTarget : Element() {

    val styleList = mutableListOf<TargetStyle>()

    val styleValue: ListValue

    // Global variables

    val blurValue = BoolValue("Blur", false)
    val blurStrength = FloatValue("Blur-Strength", 1F, 0.01F, 40F)
    val shadowValue = BoolValue("Shadow", false)
    val shadowStrength = FloatValue("Shadow-Strength", 1F, 0.01F, 40F)
    val shadowColorMode = ListValue("Shadow-Color", arrayOf("Background", "Custom", "Bar"), "Background")

    val shadowColorRedValue = IntegerValue("Shadow-Red", 0, 0, 255)
    val shadowColorGreenValue = IntegerValue("Shadow-Green", 111, 0, 255)
    val shadowColorBlueValue = IntegerValue("Shadow-Blue", 255, 0, 255)

    val fadeValue = BoolValue("FadeAnim", false)
    val fadeSpeed = FloatValue("Fade-Speed", 1F, 0F, 5F)

    val noAnimValue = BoolValue("No-Animation", false)
    val globalAnimSpeed = FloatValue("Global-AnimSpeed", 3F, 1F, 9F)

    val showWithChatOpen = BoolValue("Show-ChatOpen", true)
    val resetBar = BoolValue("ResetBarWhenHiding", false)

    val colorModeValue = ListValue("Color", arrayOf("Custom", "Rainbow", "Sky", "Slowly", "Fade", "Mixer", "Health"), "Custom")
    val redValue = IntegerValue("Red", 252, 0, 255)
    val greenValue = IntegerValue("Green", 96, 0, 255)
    val blueValue = IntegerValue("Blue", 66, 0, 255)
    val saturationValue = FloatValue("Saturation", 1F, 0F, 1F)
    val brightnessValue = FloatValue("Brightness", 1F, 0F, 1F)
    val waveSecondValue = IntegerValue("Seconds", 2, 1, 10)
    val bgRedValue = IntegerValue("Background-Red", 0, 0, 255)
    val bgGreenValue = IntegerValue("Background-Green", 0, 0, 255)
    val bgBlueValue = IntegerValue("Background-Blue", 0, 0, 255)
    val bgAlphaValue = IntegerValue("Background-Alpha", 160, 0, 255)

    override val values: List<Value<*>>
        get() {
            val valueList = mutableListOf<Value<*>>()
            styleList.forEach { valueList.addAll(it.values) }
            return super.values.toMutableList() + valueList
        }
    //
    init {
        styleValue = ListValue("Style", addStyles(
                Recall.Ui.targets.impl.LiquidBounce(this),
                Chill(this),
                Rice(this),
                Tena(this),

//            Remix(this),
                Slowly(this),
                Moon(this)
        ).toTypedArray(), "Tena")
    }

    var mainTarget: IEntityLivingBase? = null
    var animProgress = 0F

    var barColor = Color(-1)
    var bgColor = Color(-1)

    override fun drawElement(): Border? {
        val mainStyle = getCurrentStyle(styleValue.get()) ?: return null

        val kaTarget = (LiquidBounce.moduleManager[KillAura::class.java] as KillAura).target
//        val taTarget = (LiquidBounce.moduleManager[TeleportAura::class.java] as TeleportAura).lastTarget

        val actualTarget = if (kaTarget != null && classProvider.isEntityPlayer(kaTarget)) kaTarget

        else if ((mc.currentScreen is GuiChat && showWithChatOpen.get()) || classProvider.isGuiHudDesigner(mc.currentScreen)) mc.thePlayer
        else null

        val preBarColor = when (colorModeValue.get()) {
            "Rainbow" -> Color(RenderUtils.getRainbowOpaque(waveSecondValue.get(), saturationValue.get(), brightnessValue.get(), 0))
            "Custom" -> Color(redValue.get(), greenValue.get(), blueValue.get())
            "Sky" -> ColorUtils.skyRainbow(0, saturationValue.get(), brightnessValue.get(),3.0)
            "Fade" -> ColorUtils.fade(Color(redValue.get(), greenValue.get(), blueValue.get()), 0, 100)
            "Health" -> if (actualTarget != null) BlendUtils.getHealthColor(actualTarget.health, actualTarget.maxHealth) else Color.green
            "Mixer" -> ColorMixer.getMixedColor(0, waveSecondValue.get())
            else -> ColorUtils.LiquidSlowly(System.nanoTime(), 0, saturationValue.get(), brightnessValue.get())!!
        }

        val preBgColor = Color(bgRedValue.get(), bgGreenValue.get(), bgBlueValue.get(), bgAlphaValue.get())

        if (fadeValue.get())
            animProgress += (0.0075F * fadeSpeed.get() * RenderUtils.deltaTime * if (actualTarget != null) -1F else 1F)
        else animProgress = 0F

        animProgress = animProgress.coerceIn(0F, 1F)

        barColor = ColorUtils.reAlpha(preBarColor!!, preBarColor.alpha / 255F * (1F - animProgress))
        bgColor = ColorUtils.reAlpha(preBgColor, preBgColor.alpha / 255F * (1F - animProgress))

        if (actualTarget != null || !fadeValue.get())
            mainTarget = actualTarget
        else if (animProgress >= 1F)
            mainTarget = null

        val returnBorder = mainStyle.getBorder(mainTarget) ?: return null
        val borderWidth = returnBorder.x2 - returnBorder.x
        val borderHeight = returnBorder.y2 - returnBorder.y

        if (mainTarget == null) {
            if (resetBar.get())
                mainStyle.easingHealth = 0F
            if (mainStyle is Rice)
                mainStyle.particleList.clear()
            return returnBorder
        }
        val convertTarget = mainTarget!!.asEntityPlayer()

        val calcScaleX = animProgress * (4F / (borderWidth / 2F))
        val calcScaleY = animProgress * (4F / (borderHeight / 2F))
        val calcTranslateX = borderWidth / 2F * calcScaleX
        val calcTranslateY = borderHeight / 2F * calcScaleY

        if (shadowValue.get() && mainStyle.shaderSupport) {
            val floatX = renderX.toFloat()
            val floatY = renderY.toFloat()

            GL11.glTranslated(-renderX, -renderY, 0.0)
            GL11.glPushMatrix()

            ShadowUtils.shadow(shadowStrength.get(), {
                GL11.glPushMatrix()
                GL11.glTranslated(renderX, renderY, 0.0)
                if (fadeValue.get()) {
                    GL11.glTranslatef(calcTranslateX, calcTranslateY, 0F)
                    GL11.glScalef(1F - calcScaleX, 1F - calcScaleY, 1F - calcScaleX)
                }
                mainStyle.handleShadow(convertTarget)
                GL11.glPopMatrix()
            }, {
                GL11.glPushMatrix()
                GL11.glTranslated(renderX, renderY, 0.0)
                if (fadeValue.get()) {
                    GL11.glTranslatef(calcTranslateX, calcTranslateY, 0F)
                    GL11.glScalef(1F - calcScaleX, 1F - calcScaleY, 1F - calcScaleX)
                }
                mainStyle.handleShadowCut(convertTarget)
                GL11.glPopMatrix()
            })

            GL11.glPopMatrix()
            GL11.glTranslated(renderX, renderY, 0.0)
        }

        if (blurValue.get() && mainStyle.shaderSupport) {
            val floatX = renderX.toFloat()
            val floatY = renderY.toFloat()

            GL11.glTranslated(-renderX, -renderY, 0.0)
            GL11.glPushMatrix()
            BlurUtils.blur(floatX + returnBorder.x, floatY + returnBorder.y, floatX + returnBorder.x2, floatY + returnBorder.y2, blurStrength.get() * (1F - animProgress), false) {
                GL11.glPushMatrix()
                GL11.glTranslated(renderX, renderY, 0.0)
                if (fadeValue.get()) {
                    GL11.glTranslatef(calcTranslateX, calcTranslateY, 0F)
                    GL11.glScalef(1F - calcScaleX, 1F - calcScaleY, 1F - calcScaleX)
                }
                mainStyle.handleBlur(convertTarget)
                GL11.glPopMatrix()
            }
            GL11.glPopMatrix()
            GL11.glTranslated(renderX, renderY, 0.0)
        }

        if (fadeValue.get()) {
            GL11.glPushMatrix()
            GL11.glTranslatef(calcTranslateX, calcTranslateY, 0F)
            GL11.glScalef(1F - calcScaleX, 1F - calcScaleY, 1F - calcScaleX)
        }

        if (mainStyle is Chill)
            mainStyle.updateData(renderX.toFloat() + calcTranslateX, renderY.toFloat() + calcTranslateY, calcScaleX, calcScaleY)
        mainStyle.drawTarget(convertTarget)

        if (fadeValue.get())
            GL11.glPopMatrix()

        GlStateManager.resetColor()
        return returnBorder
    }

//    fun handleDamage(ent: EntityPlayer) {
//        if (mainTarget != null && ent == mainTarget)
//            getCurrentStyle(styleValue.get())?.handleDamage(ent)
//    }

    fun getFadeProgress() = animProgress

    @SafeVarargs
    fun addStyles(vararg styles: TargetStyle): List<String> {
        val nameList = mutableListOf<String>()
        styles.forEach {
            styleList.add(it)
            nameList.add(it.name)
        }
        return nameList
    }

    fun getCurrentStyle(styleName: String): TargetStyle? = styleList.find { it.name.equals(styleName, true) }

}