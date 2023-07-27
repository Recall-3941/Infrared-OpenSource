/*
 Copyright Alan Wood 2021
 None of this code to be reused without my written permission
 Intellectual Rights owned by Alan Wood

package net.ccbluex.liquidbounce.features.module.modules.render;

import dev.rise.font.CustomFont;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.Render3DEvent;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.ui.font.TTFFontRenderer;
import net.ccbluex.liquidbounce.features.module.*;
import dev.rise.module.enums.Category;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import dev.rise.util.render.theme.ThemeType;
import dev.rise.util.render.theme.ThemeUtil;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FontValue;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

import java.awt.*;

@ModuleInfo(name = "Nametags", description = "Lets you see peoples names through walls", category = Category.RENDER)
public final class RiseNametags extends Module {

    public static boolean enabled;

    private final FontValue comforaa = new FontValue("Font", Fonts.font40);
    private final BoolValue invisible = new BoolValue("Invisibles",  false);

    // Uses Render.java line 106
    @EventTarget
    public void onRender3DEvent(final Render3DEvent event) {
        int amount = 0;

        for (final EntityPlayer entity : mc2.world.playerEntities) {
            if (entity != null) {
                final String name = entity.getName();

                if ((!entity.isInvisible() || this.invisible.get()) && !entity.isDead && entity != mc2.player && RenderUtils.isInViewFrustrum(entity)) {
                    //Changing size
                    final float scale = Math.max(0.02F, mc2.player.getDistance(entity) / 300);

                    final double x = (entity).lastTickPosX + ((entity).posX - (entity).lastTickPosX) * mc2.timer.renderPartialTicks - (mc2.getRenderManager()).renderPosX;
                    final double y = ((entity).lastTickPosY + ((entity).posY - (entity).lastTickPosY) * mc2.timer.renderPartialTicks - (mc2.getRenderManager()).renderPosY) + scale * 6;
                    final double z = (entity).lastTickPosZ + ((entity).posZ - (entity).lastTickPosZ) * mc2.timer.renderPartialTicks - (mc2.getRenderManager()).renderPosZ;

                    GL11.glPushMatrix();
                    GL11.glTranslated(x, y + 2.3, z);
                    GlStateManager.disableDepth();

                    GL11.glScalef(-scale, -scale, -scale);

                    GL11.glRotated(-mc2.getRenderManager().playerViewY, 0.0D, 1.0D, 0.0D);
                    GL11.glRotated(mc2.getRenderManager().playerViewX, mc2.gameSettings.thirdPersonView == 2 ? -1.0D : 1.0D, 0.0D, 0.0D);

                    final float width = Fonts.font40.getStringWidth(name);
                    final float progress = Math.min((entity).getHealth(), (entity).getMaxHealth()) / (entity).getMaxHealth();

                    final Color color = ThemeUtil.getThemeColor(amount, ThemeType.GENERAL, 0.5f);

                    Gui.drawRect((-width / 2.0F - 5.0F), -1, (width / 2.0F + 5.0F), 8, 0x40000000);
                    Gui.drawRect((-width / 2.0F - 5.0F), 7, (-width / 2.0F - 5.0F + (width / 2.0F + 5.0F - -width / 2.0F + 5.0F) * progress), 8, color.getRGB());

                    GL11.glScalef(0.1f, 0.1f, 0.1f);

                    comfortaa.drawCenteredString(name, -width / 16.0F, 0.5f, -1);

                    GL11.glScalef(1.9f, 1.9f, 1.9f);

                    GlStateManager.enableDepth();
                    GL11.glPopMatrix();
                    amount++;
                }
            }
        }
    }

    @Override
    public void onEnable() {
        enabled = true;
    }

    @Override
    public void onDisable() {
        enabled = false;
    }
}

 */
