package net.ccbluex.liquidbounce.features.module.modules.misc

import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.value.ListValue
import net.ccbluex.liquidbounce.value.TextValue

@ModuleInfo(name = "CustomTitle", description = "Title", category = ModuleCategory.MISC)
class CustomTitle : Module(){
    private val TitleValue = TextValue("SetTitle", "Infrared Client || 交流群178422309 || 时间已流逝：")
    private val ModeTitle = ListValue("ModeTitle", arrayOf("Custom","Mode1","Mode2","Mode3","Mode4","Mode5","Mode6","Mode7","Mode8"),"Custom")
    var S = 0
    var HM = 0
    var M = 0
    var H = 0

    @EventTarget
    fun onUpdate(event: UpdateEvent){
        HM += 1
        if (HM ==20){
            S = S + 1
            HM = 0
        }
        if (S ==60){
            M = M +1
            S = 0
        }
        if (M==60){
            H = H+1
            M = 0
        }
        when (ModeTitle.get().toLowerCase()) {
            "custom" -> org.lwjgl.opengl.Display.setTitle(TitleValue.get() + H + "  时  " + M + "  分  " + S + "  秒  ")
            "mode1" -> org.lwjgl.opengl.Display.setTitle("Infrared Client || 人哪有好的，只是坏的程度不一样而已。——《哈尔的移动城堡》 || 您已使用：" + H + "  时  " + M + "  分  " + S + "  秒  ")
            "mode2" -> org.lwjgl.opengl.Display.setTitle("Infrared Client || 总是依赖别人的话，就永远长不大。——《哆啦A梦》 || 您已使用：" + H + "  时  " + M + "  分  " + S + "  秒  ")
            "mode3" -> org.lwjgl.opengl.Display.setTitle("Infrared Client || 我们会死很久，所以活着的时候一定要开心。——《名侦探柯南》 || 您已使用：" + H + "  时  " + M + "  分  " + S + "  秒  ")
            "mode4" -> org.lwjgl.opengl.Display.setTitle("Infrared Client || 我想成为一个温柔的人，因为曾被温柔的人那样对待，深深了解那种被温柔相待的感觉。——《夏目友人帐》 || 您已使用：" + H + "  时  " + M + "  分  " + S + "  秒  ")
            "mode5" -> org.lwjgl.opengl.Display.setTitle("Infrared Client || 梦不会逃走，逃走的一直都是自己。——《蜡笔小新》 || 您已使用：" + H + "  时  " + M + "  分  " + S + "  秒  ")
            "mode6" -> org.lwjgl.opengl.Display.setTitle("Infrared Client || 世界上也许有人喜欢孤独，但却没有人能承受孤独。——《妖精的尾巴》 || 您已使用：" + H + "  时  " + M + "  分  " + S + "  秒  ")
            "mode7" -> org.lwjgl.opengl.Display.setTitle("Infrared Client || 不相信自己的人，连努力的价值都没有。——《火影忍者》 || 您已使用：" + H + "  时  " + M + "  分  " + S + "  秒  ")
            "mode8" -> org.lwjgl.opengl.Display.setTitle("Infrared Client || 我不知道离别的滋味是这样凄凉，我不知道说声再见要这么坚强。——《千与千寻》 || 您已使用：" + H + "  时  " + M + "  分  " + S + "  秒  ")
        }
    }
}