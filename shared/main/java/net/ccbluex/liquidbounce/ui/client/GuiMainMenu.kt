/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
package net.ccbluex.liquidbounce.ui.client

import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiButton
import net.ccbluex.liquidbounce.api.util.WrappedGuiScreen
import net.ccbluex.liquidbounce.injection.backend.WrapperImpl.classProvider
import net.ccbluex.liquidbounce.ui.client.altmanager.GuiAltManager
import net.ccbluex.liquidbounce.ui.font.Fonts
import net.ccbluex.liquidbounce.utils.render.RenderUtils
import java.awt.Color

class GuiMainMenu : WrappedGuiScreen() {

    override fun initGui() {
        val defaultHeight = representedScreen.height / 4 + 48

        representedScreen.buttonList.add(classProvider.createGuiButton(100, representedScreen.width / 2 - 75, defaultHeight + 96, 144, 20, "AltManager"))
        representedScreen.buttonList.add(classProvider.createGuiButton(102, representedScreen.width / 2 - 75, defaultHeight + 72 , 144, 20, "Background"))

        //我草
        //你妈死了是吧
        //我去你妈的这个他妈是要进入的页面

        representedScreen.buttonList.add(classProvider.createGuiButton(1, representedScreen.width / 2 - 75, defaultHeight+24, 144, 20, functions.formatI18n("menu.singleplayer")))
        representedScreen.buttonList.add(classProvider.createGuiButton(2, representedScreen.width / 2 - 75, defaultHeight+48, 144, 20, functions.formatI18n("menu.multiplayer")))
        representedScreen.buttonList.add(classProvider.createGuiButton(0, representedScreen.width / 2 - 75, defaultHeight + 120 , 144, 20, functions.formatI18n("menu.options")))
        representedScreen.buttonList.add(classProvider.createGuiButton(4, representedScreen.width / 2 - 75, defaultHeight + 144 , 144, 20, functions.formatI18n("menu.quit")))
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        representedScreen.drawBackground(0)

        //RenderUtils.drawRect(representedScreen.width / 2.0f - 115, representedScreen.height / 4.0f + 35, representedScreen.width / 2.0f + 115, representedScreen.height / 4.0f + 175, Integer.MIN_VALUE)

        Fonts.com120.drawCenteredString(LiquidBounce.CLIENT_NAME, representedScreen.width / 2F, representedScreen.height / 6F, Color.WHITE.rgb, true)
        val logo = classProvider.createResourceLocation("loserline" + "/clickgui/logo.png")
        //RenderUtils.drawImage(logo,representedScreen.width / 2 - 65,representedScreen.height / 6 - 10,133,55)
        //Fonts.com120.drawCenteredString("Loserline", representedScreen.width / 2F, representedScreen.height / 6F, Color.WHITE.rgb, true)

        representedScreen.superDrawScreen(mouseX, mouseY, partialTicks)
    }

    override fun actionPerformed(button: IGuiButton) {
        when (button.id) {
            0 -> mc.displayGuiScreen(classProvider.createGuiOptions(this.representedScreen, mc.gameSettings))
            1 -> mc.displayGuiScreen(classProvider.createGuiSelectWorld(this.representedScreen))
            2 -> mc.displayGuiScreen(classProvider.createGuiMultiplayer(this.representedScreen))
            4 -> mc.shutdown()
            100 -> mc.displayGuiScreen(classProvider.wrapGuiScreen(GuiAltManager(this.representedScreen)))
            101 -> mc.displayGuiScreen(classProvider.wrapGuiScreen(GuiServerStatus(this.representedScreen)))
            102 -> mc.displayGuiScreen(classProvider.wrapGuiScreen(GuiBackground(this.representedScreen)))
            103 -> mc.displayGuiScreen(classProvider.wrapGuiScreen(GuiModsMenu(this.representedScreen)))
            108 -> mc.displayGuiScreen(classProvider.wrapGuiScreen(GuiContributors(this.representedScreen)))
        }
    }
}