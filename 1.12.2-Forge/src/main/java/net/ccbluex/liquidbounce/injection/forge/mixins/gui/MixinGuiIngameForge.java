/*
 * LiquidBounce+ Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/WYSI-Foundation/LiquidBouncePlus/
 */
package net.ccbluex.liquidbounce.injection.forge.mixins.gui;


import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.features.module.modules.render.Animations;
import net.ccbluex.liquidbounce.features.module.modules.render.HUD;
import net.ccbluex.liquidbounce.utils.render.AnimationUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.ccbluex.liquidbounce.utils.MinecraftInstance.mc2;
import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.PLAYER_LIST;

@Mixin(GuiIngameForge.class)
public abstract class MixinGuiIngameForge extends MixinGuiInGame {

    @Shadow(remap = false)
    abstract boolean pre(ElementType type);

    @Shadow(remap = false)
    abstract void post(ElementType type);

    public float xScale = 0F;

    @Inject(
            method = "renderChat",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/eventhandler/EventBus;post(Lnet/minecraftforge/fml/common/eventhandler/Event;)Z", ordinal = 0, remap = false)),
            at = @At(value = "RETURN", ordinal = 0),
            remap = false
    )
    private void fixProfilerSectionNotEnding(int width, int height, CallbackInfo ci) {
        final Minecraft mc = Minecraft.getMinecraft();
        if (mc.mcProfiler.getNameOfLastSection().endsWith("chat"))
            mc.mcProfiler.endSection();
    }

    @Inject(method = "renderToolHighlight", at = @At("HEAD"),cancellable = true, remap = false)
    protected void renderToolHighlight(ScaledResolution res, CallbackInfo ci) {
        final HUD hud = (HUD) LiquidBounce.moduleManager.getModule(HUD.class);
        if (!hud.getHotbar().get())
            ci.cancel();
    }
    @Inject(method = "renderFood", at = @At("HEAD"),cancellable = true, remap = false)
    private void renderPlayerStats(int width, int height, CallbackInfo ci) {
        final HUD hud = (HUD) LiquidBounce.moduleManager.getModule(HUD.class);
        if (!hud.getHotbar().get())
            ci.cancel();
    }
    @Inject(method = "renderExperience", at = @At("HEAD"),cancellable = true, remap = false)
    private void renderExperience(int width, int height, CallbackInfo ci) {
        final HUD hud = (HUD) LiquidBounce.moduleManager.getModule(HUD.class);
        if (!hud.getHotbar().get())
            ci.cancel();
    }
    @Inject(method = "renderHealth", at = @At("HEAD"),cancellable = true, remap = false)
    private void renderHealth(int width, int height, CallbackInfo ci) {
        final HUD hud = (HUD) LiquidBounce.moduleManager.getModule(HUD.class);
        if (!hud.getHotbar().get())
            ci.cancel();
    }
    @Inject(method = "renderArmor", at = @At("HEAD"),cancellable = true, remap = false)
    private void renderArmor(int width, int height, CallbackInfo ci) {
        final HUD hud = (HUD) LiquidBounce.moduleManager.getModule(HUD.class);
        if (!hud.getHotbar().get())
            ci.cancel();
    }
    /**
     * @author
     * @reason
     */

}
