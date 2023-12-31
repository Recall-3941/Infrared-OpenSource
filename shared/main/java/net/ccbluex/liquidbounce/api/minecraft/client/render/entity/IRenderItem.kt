/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */

package net.ccbluex.liquidbounce.api.minecraft.client.render.entity

import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer
import net.ccbluex.liquidbounce.api.minecraft.item.IItemStack
import net.minecraft.item.ItemStack

interface IRenderItem {
    var zLevel: Float

    fun renderItemAndEffectIntoGUI(stack: IItemStack, x: Int, y: Int)
    fun renderItemIntoGUI(stack: IItemStack, x: Int, y: Int)
    fun renderItemOverlays(fontRenderer: IFontRenderer, stack: IItemStack, x: Int, y: Int)
    fun renderItemAndEffectIntoGUI2(stack: ItemStack, x: Int, y: Int)
    fun renderItemOverlays2(fontRenderer: IFontRenderer, stack: ItemStack, x: Int, y: Int)
}