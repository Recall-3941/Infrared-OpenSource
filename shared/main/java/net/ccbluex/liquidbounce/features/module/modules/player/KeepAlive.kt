/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
package net.ccbluex.liquidbounce.features.module.modules.player

import net.ccbluex.liquidbounce.api.enums.ItemType
import net.ccbluex.liquidbounce.api.enums.WEnumHand
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.MotionEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo

import net.ccbluex.liquidbounce.utils.InventoryUtils
import net.ccbluex.liquidbounce.utils.createUseItemPacket
import net.ccbluex.liquidbounce.value.ListValue

@ModuleInfo(name = "KeepAlive", description = "Tries to prevent you from dying.", category = ModuleCategory.PLAYER)
class KeepAlive : Module() {

}