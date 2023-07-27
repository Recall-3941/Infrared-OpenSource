package net.ccbluex.liquidbounce.features.module.modules.misc

import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.features.module.modules.world.Fucker
import net.ccbluex.liquidbounce.utils.ClientUtils
import net.ccbluex.liquidbounce.value.BoolValue
import net.ccbluex.liquidbounce.value.TextValue

@ModuleInfo(name = "NoFucker", description = "NoFucker You Bed，Fuck Has Bed Ban.", category = ModuleCategory.MISC)
class NoFucker : Module() {
    private val debugValue = BoolValue("Debug", false)
    private val autoValue = BoolValue("auto", false)
    private val Clientname = TextValue("ClientName", "Infrared")
    var x = 0.0
    var y = 0.0
    var z = 0.0
    var a = 0

    @EventTarget
    fun onUpdate(event: PacketEvent) {
        if (autoValue.get()) { //判断自动是否为true
            val packet = event.packet
            if (classProvider.isSPacketChat(packet)) {
                val gameplay = packet.asSPacketChat().chatComponent.unformattedText.contains("游戏开始 ...")
                if (gameplay) {
                    x = mc.thePlayer?.posX ?: x
                    y = mc.thePlayer?.posY ?: y
                    z = mc.thePlayer?.posZ ?: z
                    ClientUtils.displayChatMessage(Clientname.get() + "§c已经标记您的床 您的Fucker不会挖你家的床")
                }
            }
        }

        if (debugValue.get()) {
            ClientUtils.displayChatMessage("" + mc.thePlayer?.getDistance(x, y, z))
        }

        Fucker.state = mc.thePlayer?.getDistance(x, y, z)!! >= 20

    }

    override fun onEnable() {
        if (autoValue.get() == false) { //判断自动是否为false
            x = mc.thePlayer?.posX ?: x
            y = mc.thePlayer?.posY ?: y
            z = mc.thePlayer?.posZ ?: z
            ClientUtils.displayChatMessage(Clientname.get() + "§c已经标记您的床 您的Fucker不会挖你家的床")
        }
    }

    override fun onDisable() {
        x = 0.0
        y = 0.0
        z = 0.0
    }
}