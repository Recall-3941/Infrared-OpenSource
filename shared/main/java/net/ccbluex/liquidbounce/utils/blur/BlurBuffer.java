package net.ccbluex.liquidbounce.utils.blur;


import Recall.Utils.render.RoundedUtil;
import Recall.Utils.render.StencilUtil;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.features.module.modules.render.HUD;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;

import java.awt.*;

public class BlurBuffer {

    public static void blurArea(float x, float y, float width, float height) {
        final HUD hud =(HUD) LiquidBounce.moduleManager.getModule(HUD.class);
        StencilUtil.initStencilToWrite();
        RenderUtils.drawRect(x, y, x + width, y + height, new Color(-2).getRGB());
        StencilUtil.readStencilBuffer(1);
        GaussianBlur.renderBlur(hud.getBlurStrength().getValue().floatValue());
        StencilUtil.uninitStencilBuffer();
    }
    public static void blurArea(Double x, Double y, float width, float height) {
        final HUD hud =(HUD) LiquidBounce.moduleManager.getModule(HUD.class);
        StencilUtil.initStencilToWrite();
        RenderUtils.drawRect(x, y, x + width, y + height, new Color(-2).getRGB());
        StencilUtil.readStencilBuffer(1);
        GaussianBlur.renderBlur(hud.getRadius().getValue().floatValue());

        StencilUtil.uninitStencilBuffer();
    }
    public static void CustomBlurArea(float x, float y, float width, float height,float BlurStrength ) {
        StencilUtil.initStencilToWrite();
        RenderUtils.drawRect(x, y, x + width, y + height, new Color(-2).getRGB());
        StencilUtil.readStencilBuffer(1);
        GaussianBlur.renderBlur(BlurStrength);
        StencilUtil.uninitStencilBuffer();
    }

    public static void CustomBlurRoundArea(float x, float y, float width, float height, float radius,float BlurStrength) {
        final HUD hud =(HUD) LiquidBounce.moduleManager.getModule(HUD.class);
        StencilUtil.initStencilToWrite();
        RoundedUtil.drawRound(x, y, width, height, radius, new Color(-2));
        StencilUtil.readStencilBuffer(1);
        GaussianBlur.renderBlur(BlurStrength);
        StencilUtil.uninitStencilBuffer();
    }
    public static void blurRoundArea(float x, float y, float width, float height, float radius) {
        final HUD hud =(HUD) LiquidBounce.moduleManager.getModule(HUD.class);
        StencilUtil.initStencilToWrite();
        RenderUtils.drawRoundedRect2(x, y, x + width, y + height, radius, 6, new Color(-2).getRGB());
        StencilUtil.readStencilBuffer(1);
        GaussianBlur.renderBlur((hud.getBlurStrength().getValue().floatValue()));
        StencilUtil.uninitStencilBuffer();
    }
}
