package net.ccbluex.liquidbounce.features.module.modules.render

import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.injection.backend.unwrap
import net.ccbluex.liquidbounce.value.FloatValue
import net.ccbluex.liquidbounce.value.IntegerValue
import net.ccbluex.liquidbounce.value.ListValue
import net.minecraft.network.play.server.SPacketChangeGameState
import net.minecraft.network.play.server.SPacketTimeUpdate

@ModuleInfo(name = "TimeChanger", description = "Ambience By Tq" ,category = ModuleCategory.RENDER)
class Ambience : Module() {
    private val timeModeValue = ListValue("TimeMode", arrayOf("None", "Normal", "Custom"), "Normal")
    private val weatherModeValue = ListValue("WeatherMode", arrayOf("None", "Sun", "Rain", "Thunder"), "None")
    private val customWorldTimeValue = IntegerValue("CustomTime", 1000, 0, 24000)
    private val changeWorldTimeSpeedValue = IntegerValue("ChangeWorldTimeSpeed", 150, 10, 500)
    private val weatherStrengthValue = FloatValue("WeatherStrength", 1f, 0f, 1f)

    var i = 0L

    override fun onDisable() {
        i = 0
    }

    @EventTarget
    fun onUpdate(event: UpdateEvent) {
        when (timeModeValue.get().toLowerCase()) {
            "normal" -> {
                if (i < 24000) {
                    i += changeWorldTimeSpeedValue.get()
                } else {
                    i = 0
                }
                mc.unwrap().world.worldTime = i
            }
            "custom" -> {
                mc.unwrap().world.worldTime = customWorldTimeValue.get().toLong()
            }
        }

        when (weatherModeValue.get().toLowerCase()) {
            "sun" -> {
                mc.unwrap().world.setRainStrength(0f)
                mc.unwrap().world.setThunderStrength(0f)
            }
            "rain" -> {
                mc.unwrap().world.setRainStrength(weatherStrengthValue.get())
                mc.unwrap().world.setThunderStrength(0f)
            }
            "thunder" -> {
                mc.unwrap().world.setRainStrength(weatherStrengthValue.get())
                mc.unwrap().world.setThunderStrength(weatherStrengthValue.get())
            }
        }
    }

    @EventTarget
    fun onPacket(event: PacketEvent) {
        val packet = event.packet

        if (!timeModeValue.equals("none") && packet.unwrap() is SPacketTimeUpdate) {
            event.cancelEvent()
        }

        if (!weatherModeValue.equals("none") && packet.unwrap() is SPacketChangeGameState) {
            if ((packet.unwrap() as SPacketChangeGameState).gameState in 7..8) { // change weather packet
                event.cancelEvent()
            }
        }
    }
}
