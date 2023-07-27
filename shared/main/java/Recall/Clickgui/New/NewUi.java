package Recall.Clickgui.New;

import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import Recall.Clickgui.NewGui;
import Recall.Clickgui.New.element.CategoryElement;
import Recall.Clickgui.New.element.SearchElement;
import Recall.Clickgui.New.element.module.ModuleElement;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.MouseUtils;
import net.ccbluex.liquidbounce.utils.render.AnimationUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.utils.render.Stencil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewUi extends GuiScreen {

    private static NewUi instance;
    public static final NewUi getInstance() {
        return instance == null ? instance = new NewUi() : instance;
    }

    public static void resetInstance() {
        instance = new NewUi();
    }

    private NewUi() {
        for (ModuleCategory c : ModuleCategory.values())
            categoryElements.add(new CategoryElement(c));
        categoryElements.get(0).setFocused(true);
    }

    public final List<CategoryElement> categoryElements = new ArrayList<>();

    private float startYAnim = height / 2F;
    private float endYAnim = height / 2F;

    private SearchElement searchElement;

    private float fading = 0F;

    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        for (CategoryElement ce : categoryElements) {
            for (ModuleElement me : ce.getModuleElements()) {
                if (me.listeningKeybind())
                    me.resetState();
            }
        }
        searchElement = new SearchElement(40F, 115F, 180F, 20F);
        super.initGui();
    }

    public void onGuiClosed() {
        for (CategoryElement ce : categoryElements) {
            if (ce.getFocused())
                ce.handleMouseRelease(-1, -1, 0, 0, 0, 0, 0);
        }
        Keyboard.enableRepeatEvents(false);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        //防止傻逼偷看云参数

            drawFullSized(mouseX, mouseY, partialTicks, NewGui.getAccentColor());
        // will draw reduced ver once it gets under 1140x780.
    }

    private void drawFullSized(int mouseX, int mouseY, float partialTicks, Color accentColor) {
        RenderUtils.drawRoundedRect(30F, 30F, this.width - 30F, this.height - 30F, 8F, 0xFF101010);
        // something to make it look more like windoze
        if (MouseUtils.mouseWithinBounds(mouseX, mouseY, this.width - 54F, 30F, this.width - 30F, 50F))
            fading += 0.2F * RenderUtils.deltaTime * 0.045F;
        else
            fading -= 0.2F * RenderUtils.deltaTime * 0.045F;
        fading = MathHelper.clamp(fading, 0F, 1F);
        RenderUtils.customRounded(this.width - 54F, 30F, this.width - 30F, 50F, 0F, 8F, 0F, 8F, new Color(1F, 0F, 0F, fading).getRGB());
        GlStateManager.disableAlpha();
        RenderUtils.drawImage(IconManager.removeIcon, this.width - 47, 35, 10, 10);
        GlStateManager.enableAlpha();
        Stencil.write(true);
        RenderUtils.drawFilledCircle((int) 65F, (int) 80F, 25F, new Color(45, 45, 45));
        Stencil.erase(true);
        /*if (mc.player != null) {
            final ResourceLocation skin = MinecraftInstance.getNetHandler().getPlayerInfo(mc.player.getUniqueID()).getLocationSkin();
            glPushMatrix();
            glTranslatef(40F, 55F, 0F);
            glDisable(GL_DEPTH_TEST);
            glEnable(GL_BLEND);
            glDepthMask(false);
            OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);
            glColor4f(1f, 1f, 1f, 1f);
            mc.getTextureManager().bindTexture(skin);
            Gui.drawScaledCustomSizeModalRect(0, 0, 8F, 8F, 8, 8, 50, 50,
                    64F, 64F);
            glDepthMask(true);
            glDisable(GL_BLEND);
            glEnable(GL_DEPTH_TEST);
            glPopMatrix();
        }*/
        Stencil.dispose();

        if (Fonts.font35.getStringWidth(mc.player.getGameProfile().getName()) > 70)
            Fonts.font35.drawString(Fonts.font35.getStringWidth(mc.player.getGameProfile().getName()) + "...", 100, 78 - Fonts.font35.getFontHeight() + 15, -1);
        else
            Fonts.font35.drawString(mc.player.getGameProfile().getName(), 100, 78 - Fonts.font35.getFontHeight() + 15, -1);

        if (searchElement.drawBox(mouseX, mouseY, accentColor)) {
            searchElement.drawPanel(mouseX, mouseY, 230, 50, width - 260, height - 80, Mouse.getDWheel(), categoryElements, accentColor);
            return;
        }

        final float elementHeight = 24;
        float startY = 140F;
        for (CategoryElement ce : categoryElements) {
            ce.drawLabel(mouseX, mouseY, 30F, startY, 200F, elementHeight);
            if (ce.getFocused()) {
                startYAnim = NewGui.fastRenderValue.get() ? startY + 6F : AnimationUtils.animate(startY + 6F, startYAnim, (startYAnim - (startY + 5F) > 0 ? 0.65F : 0.55F) * RenderUtils.deltaTime * 0.025F);
                endYAnim = NewGui.fastRenderValue.get() ? startY + elementHeight - 6F : AnimationUtils.animate(startY + elementHeight - 6F, endYAnim, (endYAnim - (startY + elementHeight - 5F) < 0 ? 0.65F : 0.55F) * RenderUtils.deltaTime * 0.025F);

                ce.drawPanel(mouseX, mouseY, 230, 50, width - 260, height - 80, Mouse.getDWheel(), accentColor);
            }
            startY += elementHeight;
        }
        RenderUtils.drawRoundedRect(32F, startYAnim, 34F, endYAnim, 1F, accentColor.getRGB());
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (MouseUtils.mouseWithinBounds(mouseX, mouseY, this.width - 54F, 30F, this.width - 30F, 50F)) {
            mc.displayGuiScreen(null);
            return;
        }
        final float elementHeight = 24;
        float startY = 140F;
        searchElement.handleMouseClick(mouseX, mouseY, mouseButton, 230, 50, width - 260, height - 80, categoryElements);
        if (!searchElement.isTyping()) for (CategoryElement ce : categoryElements) {
            if (ce.getFocused())
                ce.handleMouseClick(mouseX, mouseY, mouseButton, 230, 50, width - 260, height - 80);
            if (MouseUtils.mouseWithinBounds(mouseX, mouseY, 30F, startY, 230F, startY + elementHeight) && !searchElement.isTyping()) {
                categoryElements.forEach(e -> e.setFocused(false));
                ce.setFocused(true);
                return;
            }
            startY += elementHeight;
        }
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        for (CategoryElement ce : categoryElements) {
            if (ce.getFocused()) {
                if (ce.handleKeyTyped(typedChar, keyCode))
                    return;
            }
        }
        if (searchElement.handleTyping(typedChar, keyCode, 230, 50, width - 260, height - 80, categoryElements))
            return;
        super.keyTyped(typedChar, keyCode);
    }

    protected void mouseReleased(int mouseX, int mouseY, int state) {
        searchElement.handleMouseRelease(mouseX, mouseY, state, 230, 50, width - 260, height - 80, categoryElements);
        if (!searchElement.isTyping())
            for (CategoryElement ce : categoryElements) {
                if (ce.getFocused())
                    ce.handleMouseRelease(mouseX, mouseY, state, 230, 50, width - 260, height - 80);
            }
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}
