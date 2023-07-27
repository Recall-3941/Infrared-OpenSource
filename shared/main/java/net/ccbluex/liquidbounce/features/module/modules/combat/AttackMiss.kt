package net.ccbluex.liquidbounce.features.module.modules.combat

import net.ccbluex.liquidbounce.event.AttackEvent
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.value.FloatValue
import net.minecraft.entity.EntityLivingBase
import net.minecraft.network.play.client.CPacketUseEntity
import kotlin.math.sqrt

@ModuleInfo("AttackMiss", "Limit the Attack Packet.", ModuleCategory.COMBAT)
class AttackMiss : Module() {
    private val rangeValue = FloatValue("Range", 3.5F, 3.1F, 4.5F)
    private var limitPacket = false
    @EventTarget
    fun onAttack(event: AttackEvent) {
        val target = event.targetEntity as EntityLivingBase
        if (getRange(target) > rangeValue.get()) {
            limitPacket = true
        }
    }

    @EventTarget
    private fun onPacket(event: PacketEvent) {
        if(event.packet is CPacketUseEntity) {
            if(limitPacket)
                event.cancelEvent()
            limitPacket = false
        }
    }

    private fun getRange(entity: EntityLivingBase) : Double {
        val xDifference = mc.thePlayer!!.posX - entity.posX
        val zDifference = mc.thePlayer!!.posZ - entity.posZ
        val yDifference = mc.thePlayer!!.posY - entity.posY
        return sqrt(xDifference * xDifference + zDifference * zDifference + yDifference * yDifference)
    }
}