package Recall.Ui;


import Recall.Novoline.Font.Fonts;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.features.module.modules.render.HUD;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.utils.blur.BlurBuffer;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.ListValue;
import org.lwjgl.opengl.GL11;

import java.awt.*;

@ElementInfo(name = "TimerHud")
public class TimerHud extends Element {
    public final ListValue shadowValue = new ListValue("Shadow", new String[]{"None", "Basic", "Thick"}, "None");
    public BoolValue BlurValue = new BoolValue("blur",false);
    public float animWidth;
    public float animatedCircleEnd;
    float Width = 153.0f;
    float Height = 42.0f;
    int x = 0;
    int y = 0;
    Double renderX;
    Double renderY;
    @Override
    public Border drawElement() {
        float end;
        final HUD hud = (HUD) LiquidBounce.moduleManager.getModule(HUD.class);
        this.animWidth = (float) RenderUtils.interpolate1(100.0f - hud.getTicks() * 4.0f, this.animWidth, 0.05f);

        RenderUtils.drawSmoothRect(this.x, this.y, this.x + 40.0f, this.y + 43.0f, new Color(0,0,0,100).getRGB());
     //   VisualBase.renderBlurredShadow(new Color(0, 140, 255, 200).darker(), (double)(this.x - 5.0f), (double)(this.y - 2.0f), 40.0, 43.0, 43);
        //drawShadow
        if(BlurValue.get()) {
            GL11.glTranslated(-getRenderX(), -getRenderY(), 0.0);
            GL11.glPushMatrix();
            BlurBuffer.blurArea(getRenderX(), getRenderY(), this.x + 40.0f, this.y + 43.0f);
            GL11.glPopMatrix();
            GL11.glTranslated(getRenderX(), getRenderY(), 0.0);
        }
        switch (shadowValue.get()) {
            case "Basic":
                RenderUtils.drawShadow(this.x-0.5f, this.y-0.5f, this.x + 40.0f+1f, this.y + 43.0f);
                break;
            case "Thick":
                RenderUtils.drawShadow(this.x-0.5f, this.y-0.5f, this.x + 40.0f+1f, this.y + 43.0f);
                RenderUtils.drawShadow(this.x-0.5f, this.y-0.5f, this.x + 40.0f+1f, this.y + 43.0f);
                break;
        }
        Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.drawCenteredString("Timer", x + 20, y + 14 - 10, -1);
        RenderUtils.drawCircle(x + 15 + 5, (double)y + 23.5, 11.5, -5.0f, 360.0f, Color.DARK_GRAY.darker().getRGB(), 5.5f);
        float coef = this.animWidth / 100.0f;
        this.animatedCircleEnd = end = coef * 360.0f;
        RenderUtils.drawCircle(x + 15 + 5, (double)y + 23.5, 11.5, -5.0f, this.animatedCircleEnd, new Color(255,255,255).getRGB(), 5.5f);
        Fonts.SFBOLD.SFBOLD_15.SFBOLD_15.drawCenteredString(Math.round(this.animWidth) + "%", x + 20, y + 22, -1);
        return new Border(0,0,Width,Height);
    }

}
