package Recall.Modules;

import com.sun.istack.internal.NotNull
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.utils.ClientUtils
import net.ccbluex.liquidbounce.value.BoolValue

@ModuleInfo(name = "BlockFlyHelper", description = "Sk1d by 2997570499", category = ModuleCategory.WORLD)
class BlockFlyHelper : Module() {
    val debug = BoolValue("RotDebug", false)
    val debug2 = BoolValue("XDebug", false)
    val debug3 = BoolValue("ZDebug", false)
    val test = BoolValue("Test", false)
    @EventTarget
    fun onUpdate(@NotNull event: UpdateEvent?) {
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(event, "event")
        val X: Double = mc.thePlayer!!.motionX
        val Z: Double = mc.thePlayer!!.motionZ
        if (Z > 0.1) mc.thePlayer!!.rotationYaw= 0.0f
        if (Z < -0.1) mc.thePlayer!!.rotationYaw= 180.0f
        if (X > 0.1) mc.thePlayer!!.rotationYaw= -90.0f
        if (X < -0.1) mc.thePlayer!!.rotationYaw= 90.0f
        if (debug.get()) ClientUtils.displayChatMessage(
                kotlin.jvm.internal.Intrinsics.stringPlus(
                        "rot yaw ", java.lang.Float.valueOf(
                        mc.thePlayer!!.rotationYaw
                )
                )
        )
        if (debug2.get()) ClientUtils.displayChatMessage(
                kotlin.jvm.internal.Intrinsics.stringPlus(
                        "X ", java.lang.Double.valueOf(
                        mc.thePlayer!!.motionX
                )
                )
        )
        if (debug3.get()) ClientUtils.displayChatMessage(
                kotlin.jvm.internal.Intrinsics.stringPlus(
                        "Z ", java.lang.Double.valueOf(
                        mc.thePlayer!!.motionZ
                )
                )
        )
    }
}