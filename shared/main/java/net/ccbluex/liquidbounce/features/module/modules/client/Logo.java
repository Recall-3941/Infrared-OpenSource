package net.ccbluex.liquidbounce.features.module.modules.client;

import Recall.ClientInfo;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.Render2DEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

/**
 * Skid by Recall.
 *
 * @Date 2023/04/01
 */
@ModuleInfo(name="Logo",description = "Jello",category = ModuleCategory.CLIENT,array = false)
public class Logo extends Module {
    @EventTarget
    public void onRender2D(final Render2DEvent event){

        int sigmaY = 4;
        int sigmaX = 8;
        RenderUtils.drawShadowImage(sigmaX - 12 - Fonts.jello40.getStringWidth(LiquidBounce.CLIENT_NAME) / 2 - 8, sigmaY + 1, 125, 50, new ResourceLocation("liquidbounce/shadow/arraylistshadow.png"));

        Fonts.jello96.drawString(LiquidBounce.CLIENT_NAME, sigmaX, sigmaY + 1, new Color(255, 255, 255, 130).getRGB());
        Fonts.jello40.drawCenteredString(ClientInfo.INSTANCE.getClientdev(), sigmaX + 10, sigmaY + 28, new Color(255, 255, 255, 170).getRGB());
    }
}
