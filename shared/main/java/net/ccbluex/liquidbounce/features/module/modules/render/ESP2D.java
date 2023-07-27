package net.ccbluex.liquidbounce.features.module.modules.render;


import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.Render2DEvent;
import net.ccbluex.liquidbounce.event.Render3DEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.ESPUtil;
import net.ccbluex.liquidbounce.utils.MathUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import Recall.Utils.render.RoundedUtil;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.ListValue;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.StringUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector4f;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.ccbluex.liquidbounce.utils.MinecraftInstance.mc;
import static net.ccbluex.liquidbounce.utils.MinecraftInstance.mc2;
import static org.lwjgl.opengl.GL11.*;

@ModuleInfo(name = "ESP2D",description = "",category = ModuleCategory.RENDER)
public class ESP2D extends Module {


    private final BoolValue itemHeld = new  BoolValue("Item Held", true);
    private final  BoolValue boxEsp = new  BoolValue("Box", true);

    private final  BoolValue healthBar = new  BoolValue("Health Bar", true);
    private final ListValue healthBarMode = new ListValue("Health Bar Mode", new String[]{ "Color", "Health"},"Color");
    private final BoolValue healthBarText = new BoolValue("Health Bar Text", true);


    private final  BoolValue nametags = new  BoolValue("Tags", true);
    private final  BoolValue redTags = new  BoolValue("Red Tags", true);
    private final FloatValue scale = new FloatValue("Tag Scale", .75F, .35F, 1F);

    private final ListValue backgroundMode = new  ListValue("Background Mode",new String[]{ "Rect", "Round"},"Rect");



    private final Map<Entity, Vector4f> entityPosition = new HashMap<>();
    @EventTarget
    public void Render3D(Render3DEvent e) {
        entityPosition.clear();
        for (final Entity entity : mc2.world.loadedEntityList) {
            if (ESPUtil.isInView(entity)) {
                entityPosition.put(entity, ESPUtil.getEntityPositionsOn2D(entity));
            }
        }

    };

    private final NumberFormat df = new DecimalFormat("0.#");
    private final Color backgroundColor = new Color(10, 10, 10, 130);

    private Color firstColor = Color.BLACK, secondColor = Color.BLACK, thirdColor = Color.BLACK, fourthColor = Color.BLACK;

    @EventTarget
    public void Render2D(Render2DEvent event) {

        glEnable(GL11.GL_BLEND);
        glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        for (Entity entity : entityPosition.keySet()) {
            Vector4f pos = entityPosition.get(entity);
            float x = pos.getX(),
                    y = pos.getY(),
                    right = pos.getZ(),
                    bottom = pos.getW();

            if (nametags.get() && entity instanceof EntityLivingBase) {
                EntityLivingBase renderingEntity = (EntityLivingBase) entity;
                float healthValue = renderingEntity.getHealth() / renderingEntity.getMaxHealth();
                Color healthColor = healthValue > .75 ? new Color(66, 246, 123) : healthValue > .5 ? new Color(228, 255, 105) : healthValue > .35 ? new Color(236, 100, 64) : new Color(255, 65, 68);
                StringBuilder text = new StringBuilder((redTags.get() ? "§c" : "§f") + StringUtils.stripControlCodes(renderingEntity.getDisplayName().getUnformattedText()));
                text.append(String.format(" §7[§r%s HP§7]", df.format(renderingEntity.getHealth())));
                double fontScale = scale.getValue();
                float middle = x + ((right - x) / 2);
                float textWidth = 0;
                double fontHeight;
                textWidth = (float) Fonts.font40.getStringWidth(text.toString());
                middle -= (textWidth * fontScale) / 2f;
                fontHeight = Fonts.font40.getFontHeight() * fontScale;

                glPushMatrix();
                glTranslated(middle, y - (fontHeight + 2), 0);
                glScaled(fontScale, fontScale, 1);
                glTranslated(-middle, -(y - (fontHeight + 2)), 0);

                if (backgroundMode.get() == ("Rect")) {
                    RenderUtils.drawRect2tenacity(middle - 3, (float) (y - (fontHeight + 7)), textWidth + 6,
                            (fontHeight / fontScale) + 4, backgroundColor.getRGB());
                } else {
                    RoundedUtil.drawRound(middle - 3, (float) (y - (fontHeight + 7)), (float) (textWidth + 6),
                            (float) ((fontHeight / fontScale) + 4), 4, backgroundColor);
                }

                GlStateManager.bindTexture(0);
                RenderUtils.resetColor();

                Fonts.font40.drawString(text.toString(), middle, (float) (y - (fontHeight + 5)), healthColor.getRGB());


                glPopMatrix();

            }
            if (itemHeld.get() && entity instanceof EntityLivingBase) {
                EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
                if (entityLivingBase.getHeldItem(EnumHand.MAIN_HAND) != null) {

                    double fontScale = .5f;
                    float middle = x + ((right - x) / 2);
                    float textWidth = 0;
                    double fontHeight;
                    String text = entityLivingBase.getHeldItem(EnumHand.MAIN_HAND).getDisplayName();

                    textWidth = (float) Fonts.font40.getStringWidth(text);
                    middle -= (textWidth * fontScale) / 2f;
                    fontHeight = Fonts.font40.getFontHeight() * fontScale;


                    glPushMatrix();
                    glTranslated(middle, (bottom + 4), 0);
                    glScaled(fontScale, fontScale, 1);
                    glTranslated(-middle, -(bottom + 4), 0);
                    GlStateManager.bindTexture(0);
                    RenderUtils.resetColor();
                    Fonts.font40.drawStringWithShadow(text.toString(), (int) middle, (int) (bottom + 4), -1);

                    glPopMatrix();
                }
            }


            if (healthBar.get() && entity instanceof EntityLivingBase) {
                EntityLivingBase renderingEntity = (EntityLivingBase) entity;
                float healthValue = renderingEntity.getHealth() / renderingEntity.getMaxHealth();
                Color healthColor = healthValue > .75 ? new Color(66, 246, 123) : healthValue > .5 ? new Color(228, 255, 105) : healthValue > .35 ? new Color(236, 100, 64) : new Color(255, 65, 68);

                float height = (bottom - y) + 1;
                RenderUtils.drawRect2tenacity(right + 2.5f, y - .5f, 2, height + 1, new Color(0, 0, 0, 180).getRGB());

                RenderUtils.drawRect2tenacity(right + 3, y + (height - (height * healthValue)), 1, height * healthValue, healthColor.getRGB());


                if (healthBarText.get())
                    healthValue *= 100;
                String health = String.valueOf(MathUtils.round(healthValue, 1)).substring(0, healthValue == 100 ? 3 : 2);
                String text = health + "%";
                double fontScale = .5;
                float textX = right + 8;
                float fontHeight = (float) (Fonts.font40.getFontHeight() * fontScale);
                float newHeight = height - fontHeight;
                float textY = y + (newHeight - (newHeight * (healthValue / 100)));

                glPushMatrix();
                glTranslated(textX - 5, textY, 1);
                glScaled(fontScale, fontScale, 1);
                glTranslated(-(textX - 5), -textY, 1);

                Fonts.font40.drawStringWithShadow(text, (int) textX, (int) textY, -1);

                glPopMatrix();
            }


        }


    }
};
