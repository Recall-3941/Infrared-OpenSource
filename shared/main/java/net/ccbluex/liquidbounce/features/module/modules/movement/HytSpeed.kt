package net.ccbluex.liquidbounce.features.module.modules.movement

import net.ccbluex.liquidbounce.api.minecraft.network.IPacket
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.utils.MovementUtils.isMoving
import net.ccbluex.liquidbounce.utils.MovementUtils.strafe
import net.ccbluex.liquidbounce.value.FloatValue
import java.util.concurrent.LinkedBlockingQueue

@ModuleInfo(name = "HytSpeed", description = "AsOne & 司马人", category = ModuleCategory.HYT)
class HytSpeed : Module() {

}