/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
package Recall.Modules

import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketPlayerBlockPlacement
import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketPlayerLook
import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketPlayerPosLook
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.injection.backend.unwrap
import net.ccbluex.liquidbounce.utils.ClientUtils
import Recall.Utils.PacketUtils2
import net.ccbluex.liquidbounce.utils.timer.MSTimer
import net.ccbluex.liquidbounce.value.BoolValue
import net.ccbluex.liquidbounce.value.IntegerValue
import net.ccbluex.liquidbounce.value.ListValue
import net.minecraft.network.Packet
import net.minecraft.network.play.INetHandlerPlayServer
import net.minecraft.network.play.client.*
import java.util.*

@ModuleInfo(name = "HYTDisabler", description = "Spoofs your ping to a given value.", category = ModuleCategory.HYT)
class HYTDisabler : Module() {

    private val modeValue = ListValue("PacketMode", arrayOf( "FakeLag","C03","HytRange","HytSpartan"), "FakeLag")
    private val lagDelayValue = IntegerValue("LagDelay", 0, 0, 2000)
    private val lagDurationValue = IntegerValue("LagDuration", 200, 100, 1000)
    private val debugValue = BoolValue("Debug", false)
    private val fakeLagBlockValue = BoolValue("FakeLagBlock", true)
    private val fakeLagPosValue = BoolValue("FakeLagPosition", true)
    private val fakeLagAttackValue = BoolValue("FakeLagAttack", true)
    private val fakeLagSpoofValue = BoolValue("FakeLagC03Spoof", false)
    private val keepAlives = arrayListOf<CPacketKeepAlive>()

    private val transactions = arrayListOf<CPacketConfirmTransaction>()
    var posX = 0.0;
    var posY = 0.0;
    var posZ = 0.0;
    private  val msTimer = MSTimer()


    private var isSent = false
    private val fakeLagDelay = MSTimer()
    private val fakeLagDuration = MSTimer()
    private val packetBuffer = LinkedList<Packet<INetHandlerPlayServer>>()
    override fun onEnable() {
        posX = mc.thePlayer!!.posX
        posY = mc.thePlayer!!.posY
        posZ = mc.thePlayer!!.posZ
    }
    override fun onDisable() {
        when (modeValue.get().toLowerCase()) {
            "fakelag" -> {
                for (packet in packetBuffer) {
                    PacketUtils2.sendPacketNoEvent(packet)
                }
                packetBuffer.clear()
            }
        }
    }

    @EventTarget
    fun onUpdate(event: UpdateEvent) {
        when (modeValue.get().toLowerCase()) {
            "hytspartan" -> {
                if (msTimer.hasTimePassed(3000L) && keepAlives.size > 0 && transactions.size > 0) {
                    PacketUtils2.sendPacketNoEvent(keepAlives[keepAlives.size - 1])
                    PacketUtils2.sendPacketNoEvent(transactions[transactions.size - 1])

                    ClientUtils.displayChatMessage("c00 no.${keepAlives.size - 1} sent.")
                    ClientUtils.displayChatMessage("c0f no.${transactions.size - 1} sent.")
                    keepAlives.clear()
                    transactions.clear()
                    msTimer.reset()
                }
            }
            "fakelag" -> {
                if (!fakeLagDelay.hasTimePassed(lagDelayValue.get().toLong())) fakeLagDuration.reset()
                // Send
                if (fakeLagDuration.hasTimePassed(lagDurationValue.get().toLong())) {
                    fakeLagDelay.reset()
                    fakeLagDuration.reset()
                    for (packet in packetBuffer) {
                        PacketUtils2.sendPacketNoEvent(packet)
                    }
                    debugMessage("Release buf(size=${packetBuffer.size})")
                    isSent = true
                    packetBuffer.clear()
                }
            }
            "C03" -> {
                mc.thePlayer!!.setPosition(posX, posY, posZ)

            }

        }

    }
    @EventTarget
    fun onPacket(event: PacketEvent) {
        val packet = event.packet.unwrap()
        when (modeValue.get().toLowerCase()) {
            "hytspartan" -> {
                if (packet is CPacketKeepAlive && (keepAlives.size <= 0 || packet != keepAlives[keepAlives.size - 1])) {
                    ClientUtils.displayChatMessage("c00 added")
                    keepAlives.add(packet)
                    event.cancelEvent()
                }
                if (packet is CPacketConfirmTransaction && (transactions.size <= 0 || packet != transactions[transactions.size - 1])) {
                    ClientUtils.displayChatMessage("c0f added")
                    transactions.add(packet)
                    event.cancelEvent()
                }
            }
            "fakelag" -> {
                if (fakeLagDelay.hasTimePassed(lagDelayValue.get().toLong())) {
                    if (isSent && fakeLagSpoofValue.get()) {
                        PacketUtils2.sendPacketNoEvent(CPacketPlayer(true))
                        if (lagDurationValue.get() >= 300) PacketUtils2.sendPacketNoEvent(CPacketPlayer(true))
                        isSent = false
                    }
                    if (packet is CPacketKeepAlive || packet is CPacketConfirmTransaction) {
                        event.cancelEvent()
                        packetBuffer.add(packet as Packet<INetHandlerPlayServer>)
                    }
                    if (fakeLagAttackValue.get() && (packet is CPacketUseEntity || packet is CPacketAnimation)) {
                        event.cancelEvent()
                        packetBuffer.add(packet as Packet<INetHandlerPlayServer>)
                        if (packet is CPacketAnimation) return
                    }
                    if (fakeLagBlockValue.get() && (packet is CPacketPlayerDigging || packet is ICPacketPlayerBlockPlacement || packet is CPacketAnimation)) {
                        event.cancelEvent()
                        packetBuffer.add(packet as Packet<INetHandlerPlayServer>)
                    }
                    if (fakeLagPosValue.get() && (packet is CPacketPlayer || packet is CPacketPlayer.Position || packet is ICPacketPlayerLook || packet is ICPacketPlayerPosLook || packet is CPacketEntityAction)) {
                        event.cancelEvent()
                        packetBuffer.add(packet as Packet<INetHandlerPlayServer>)
                    }
                }
            }
            "C03" -> {
                if (packet is CPacketPlayer) {
                    event.cancelEvent()
                    debugMessage("Clean C03")
                }
            }
            "HytRange" -> {
                if(mc.thePlayer!!.ticksExisted % 4 == 0 && classProvider.isCPacketPlayer(packet)){
                    event.cancelEvent()
                }
            }
        }
    }
    private fun debugMessage(str: String) {
        if (debugValue.get()) {
            ClientUtils.displayChatMessage(" [Disabler] $str")
        }
    }
    override val tag: String
        get() = this.modeValue.get()
}