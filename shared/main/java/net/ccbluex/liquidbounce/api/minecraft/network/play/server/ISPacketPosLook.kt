/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */

package net.ccbluex.liquidbounce.api.minecraft.network.play.server

import net.ccbluex.liquidbounce.api.minecraft.network.IPacket

interface ISPacketPosLook : IPacket {
    var yaw: Float
    var pitch: Float
    val x : Double
    val y : Double
    val z : Double
}