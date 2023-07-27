package net.ccbluex.liquidbounce.features.module.modules.misc

import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.value.ListValue

@ModuleInfo(name = "StateSound", description = "Sound" ,category = ModuleCategory.MISC, canEnable = false)
class StateSound : Module() {
    private val toggleSoundValue = ListValue("ToggleSound", arrayOf("None", "Click", "Pop"), "Click")

    fun playSound(enable: Boolean) {
        when (toggleSoundValue.get().toLowerCase()) {
            "click" -> {
                mc.soundHandler.playSound("ui.button.click", 1F)
            }

            "Pop" -> {
                if (enable) {
                    mc.soundHandler.playSound("block.lava.pop", 1.0F)
                } else {
                    mc.soundHandler.playSound("block.lava.pop", 1.0F)
                }
            }
        }
    }
}