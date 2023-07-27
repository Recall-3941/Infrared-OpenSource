package net.ccbluex.liquidbounce.features.module.modules.client
import net.ccbluex.liquidbounce.features.module.modules.client.utils.render.CompassUtil
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.Render2DEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.ScaledResolution


@ModuleInfo(
        name = "JelloCompass",
        description = "Show the compass for u.",
        category = ModuleCategory.RENDER
)
class Compass : Module() {
    @EventTarget
    private fun onRender2D(e: Render2DEvent) {
        val cpass = CompassUtil(325F, 325F, 1F, 2, true)
        val sc = ScaledResolution(Minecraft.getMinecraft())
        cpass.draw(sc)
    }
}