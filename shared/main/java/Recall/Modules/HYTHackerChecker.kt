package Recall.Modules

import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.injection.backend.unwrap
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.Notification
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.NotifyType
import net.minecraft.network.play.server.SPacketChat

@ModuleInfo(name = "HYTHackerChecker",  category = ModuleCategory.PLAYER,description = "idk")
class HYTHackerChecker : Module() {
    @EventTarget
    fun onPacket(event: PacketEvent) {
        val packet = event.packet.unwrap()
        if (packet is SPacketChat) {
            val text = packet.chatComponent.unformattedText
            if (text.contains("游戏开始", false)) {
                LiquidBounce.hud.addNotification(Notification("Hacker Detector","Hacker Checked", NotifyType.SUCCESS))
                mc.thePlayer!!.sendChatMessage("@[起床战争] Game 结束！感谢您的参与！")
            }
        }
    }
}
