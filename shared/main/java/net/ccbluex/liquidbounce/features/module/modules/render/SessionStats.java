package net.ccbluex.liquidbounce.features.module.modules.render;

import Recall.Novoline.Font.Fonts;
import Recall.Utils.render.DrRenderUtils;
import Recall.Utils.render.RoundedUtil;
import Recall.Utils.render.StencilUtil;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.Render2DEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@ModuleInfo(name = "SessionStats", description = "", category = ModuleCategory.RENDER)
public class SessionStats extends Module {

    public static int gamesPlayed, killCount;
    public static long startTime = System.currentTimeMillis(), endTime = -1;
    public static final String[] KILL_TRIGGERS = {"by *", "para *", "fue destrozado a manos de *"};
    private final List<String> linesLeft = Arrays.asList("Play time", "Games played", "Kills");


    public IntegerValue dragx = new IntegerValue("X",0,-500,500);
    public IntegerValue dragy = new IntegerValue("Y",0,-500,500);

    private final BoolValue animatedPlaytime = new BoolValue("Animated counter", true);
    public final ListValue colorMode = new ListValue("Color",new String[]{ "Sync", "Analogous", "Tenacity", "Gradient", "Modern"}, "Sync");
    public final ListValue degree = new ListValue("Degree", new String[]{"30", "30"}, "-30");






    float playtimeWidth = 20.5f;

    private Color gradientColor1 = Color.WHITE, gradientColor2 = Color.WHITE, gradientColor3 = Color.WHITE, gradientColor4 = Color.WHITE;

    @EventTarget
    public void Render2d(Render2DEvent e){
        float x = this.dragx.get(), y = this.dragy.get();
        float height = linesLeft.size() * (Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.getHeight() + 6) + 24;
        float width = 140;

        HUD hudMod = (HUD) LiquidBounce.moduleManager.getModule(HUD.class);
        switch (colorMode.get()) {
            case "Sync":
                Color[] colors =   new Color[]{ClickGUI.generateColor(), ClickGUI.generateColor()};;
                gradientColor1 = RenderUtils.interpolateColorsBackAndForth(15, 0, colors[0], colors[1], hudMod.getHueInterpolation().get());
                gradientColor2 = RenderUtils.interpolateColorsBackAndForth(15, 90, colors[0], colors[1], hudMod.getHueInterpolation().get());
                gradientColor3 = RenderUtils.interpolateColorsBackAndForth(15, 180, colors[0], colors[1], hudMod.getHueInterpolation().get());
                gradientColor4 = RenderUtils.interpolateColorsBackAndForth(15, 270, colors[0], colors[1], hudMod.getHueInterpolation().get());
                break;
            case "Tenacity":
                gradientColor1 = RenderUtils.interpolateColorsBackAndForth(15, 0,getClientColor(),getAlternateClientColor(), hudMod.getHueInterpolation().get());
                gradientColor2 = RenderUtils.interpolateColorsBackAndForth(15, 90,getClientColor(),getAlternateClientColor(), hudMod.getHueInterpolation().get());
                gradientColor3 = RenderUtils.interpolateColorsBackAndForth(15, 180,getClientColor(),getAlternateClientColor(), hudMod.getHueInterpolation().get());
                gradientColor4 = RenderUtils.interpolateColorsBackAndForth(15, 270,getClientColor(),getAlternateClientColor(), hudMod.getHueInterpolation().get());
                break;
            case "Gradient":
                gradientColor1 = RenderUtils.interpolateColorsBackAndForth(15, 0,ClickGUI.generateColor(), ClickGUI.generateColor(), hudMod.getHueInterpolation().get());
                gradientColor2 = RenderUtils.interpolateColorsBackAndForth(15, 90,ClickGUI.generateColor(), ClickGUI.generateColor(), hudMod.getHueInterpolation().get());
                gradientColor3 = RenderUtils.interpolateColorsBackAndForth(15, 180,ClickGUI.generateColor(), ClickGUI.generateColor(), hudMod.getHueInterpolation().get());
                gradientColor4 = RenderUtils.interpolateColorsBackAndForth(15, 270,ClickGUI.generateColor(), ClickGUI.generateColor(), hudMod.getHueInterpolation().get());
                break;
            case "Analogous":
                int val = degree.get() == "30" ? 0 : 1;
                Color analogous = RenderUtils.getAnalogousColor(ClickGUI.generateColor())[val];
                gradientColor1 = RenderUtils.interpolateColorsBackAndForth(15, 0,ClickGUI.generateColor(), analogous, hudMod.getHueInterpolation().get());
                gradientColor2 = RenderUtils.interpolateColorsBackAndForth(15, 90,ClickGUI.generateColor(), analogous, hudMod.getHueInterpolation().get());
                gradientColor3 = RenderUtils.interpolateColorsBackAndForth(15, 180,ClickGUI.generateColor(), analogous, hudMod.getHueInterpolation().get());
                gradientColor4 = RenderUtils.interpolateColorsBackAndForth(15, 270,ClickGUI.generateColor(), analogous, hudMod.getHueInterpolation().get());
                break;
            case "Modern":
                RoundedUtil.drawRoundOutline(x, y, width, height, 6, .5f, new Color(10, 10, 10, 80), new Color(-2));
                break;
        }
        boolean outlinedRadar = !(colorMode.get() == "Modern");
        DrRenderUtils.setAlphaLimit(0);
        if (outlinedRadar) {
            RoundedUtil.drawGradientRound(x, y, width, height, 6, DrRenderUtils.applyOpacity(gradientColor4, .85f), gradientColor1, gradientColor3, gradientColor2);
            DrRenderUtils.drawGradientRect2(x - 1, y + 15, width + 2, 5, DrRenderUtils.applyOpacity(Color.BLACK, .2f).getRGB(), DrRenderUtils.applyOpacity(Color.BLACK, 0).getRGB());
        }else {
            DrRenderUtils.drawGradientRect2(x +1, y + 15, width - 2, 5, DrRenderUtils.applyOpacity(Color.BLACK, .2f).getRGB(), DrRenderUtils.applyOpacity(Color.BLACK, 0).getRGB());
        }


        Fonts.SFBOLD.SFBOLD_22.SFBOLD_22.drawCenteredString("Statistics", x + width / 2, y + (colorMode.get() == "Modern" ? 3 : 2), -1);




        for (int i = 0; i < linesLeft.size(); i++) {
            int offset = i * (Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.getHeight() + 6);
            Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.drawString(linesLeft.get(i), x + 5, (float) (y + offset + (i == 0 ? 23.5 : 25)), -1);
        }
        int[] playTime = getPlayTime();

        playtimeWidth = (float) DrRenderUtils.animate(20.5 + (playTime[1] > 0 ? 20 : 0) + (playTime[0] > 0 ? 14 : 0), playtimeWidth, 0.008);

        float playtimeX = x + width - (playtimeWidth + 5);
        if (animatedPlaytime.get()) {
            drawAnimatedPlaytime(playtimeX, y, width);
        } else {
            String playtimeString = playTime[0] + "h " + playTime[1] + "m " + playTime[2] + "s";
            Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.drawString(playtimeString, playtimeX + playtimeWidth - Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.stringWidth(playtimeString), y + 24, -1);
        }

        List<String> linesRight = Arrays.asList(String.valueOf(gamesPlayed), String.valueOf(killCount));

        for (int i = 0; i < linesRight.size(); i++) {
            int offset = (i + 1) * (Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.getHeight() + 6);
            Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.drawString(linesRight.get(i), x + width - (Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.stringWidth(linesRight.get(i)) + 5), y + offset + 25, -1);
        }

    }


    public final Color getClientColor() {
        return new Color(236, 133, 209);
    }

    public final Color getAlternateClientColor() {
        return new Color(28, 167, 222);
    }

    //Animation values for going up and down with the time
    float hourYAnimation;
    float minuteYAnimation1;
    float minuteYAnimation2;
    float secondYAnimation2;
    float secondYAnimation1;

    //Animation values for going left or right based on the width of the other charchter
    float secondsSeperateWidthAnim1;
    float secondsSeperateWidthAnim2;
    float minuteSeperateWidthAnim1;
    float minuteSeperateWidthAnim2;

    private void drawAnimatedPlaytime(float playtimeX, float y, float width) {
        int[] playTime = getPlayTime();
        RoundedUtil.drawRoundOutline(playtimeX, y + 21, playtimeWidth, 13, 6, .5f, DrRenderUtils.applyOpacity(Color.WHITE, 0), Color.WHITE);
        //RoundedUtil.drawRound(playtimeX, y + 22, playtimeWidth, 11, 6, new Color(30, 30, 30));
        StencilUtil.initStencilToWrite();
        RoundedUtil.drawRound(playtimeX, y + 22, playtimeWidth, 11, 6, new Color(30, 30, 30));
        StencilUtil.readStencilBuffer(1);


        float secondX = playtimeX + playtimeWidth - 7;
        Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.drawString("s", secondX, y + 24, -1);

        int secondsFirstPlace = (playTime[2] % 10);

        secondYAnimation2 = (float) DrRenderUtils.animate(20 * secondsFirstPlace, secondYAnimation2, .02);

        secondsSeperateWidthAnim1 = (float) DrRenderUtils.animate(Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.stringWidth(String.valueOf(secondsFirstPlace)), secondsSeperateWidthAnim1, .05);

        secondX -= secondsSeperateWidthAnim1 + .5;

        for (int i = 0; i < 10; i++) {
            Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.drawString(String.valueOf(i), secondX, y + 24 + (i * 20) - secondYAnimation2, -1);
        }

        int secondsSecondPlace = Math.floorDiv(playTime[2], 10);

        secondYAnimation1 = (float) DrRenderUtils.animate(20 * (secondsSecondPlace), secondYAnimation1, .02);

        secondsSeperateWidthAnim2 = (float) DrRenderUtils.animate(Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.stringWidth(String.valueOf(secondsSecondPlace)), secondsSeperateWidthAnim2, .05);


        secondX -= secondsSeperateWidthAnim2 + .5;

        for (int i = 0; i < 10; i++) {
            Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.drawString(String.valueOf(i), secondX, y + 24 + (i * 20) - secondYAnimation1, -1);
        }

        if (playTime[1] > 0) {

            float minuteX = playtimeX + playtimeWidth - 27;

            Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.drawString("m", minuteX, y + 24, -1);

            int minuteFirstPlace = (playTime[1] % 10);

            minuteYAnimation1 = (float) DrRenderUtils.animate(20 * minuteFirstPlace, minuteYAnimation1, .02);

            minuteSeperateWidthAnim1 = (float) DrRenderUtils.animate(Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.stringWidth(String.valueOf(minuteFirstPlace)), minuteSeperateWidthAnim1, .05);

            minuteX -= minuteSeperateWidthAnim1 + .5;

            for (int i = 0; i < 10; i++) {
                Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.drawString(String.valueOf(i), minuteX, y + 24 + (i * 20) - minuteYAnimation1, -1);
            }

            int minuteSecondPlace = Math.floorDiv(playTime[1], 10);

            minuteYAnimation2 = (float) DrRenderUtils.animate(20 * (minuteSecondPlace), minuteYAnimation2, .02);

            minuteSeperateWidthAnim2 = (float) DrRenderUtils.animate(Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.stringWidth(String.valueOf(minuteSecondPlace)), minuteSeperateWidthAnim2, .05);

            minuteX -= minuteSeperateWidthAnim2 + .5;

            for (int i = 0; i < 10; i++) {
                Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.drawString(String.valueOf(i), minuteX, y + 24 + (i * 20) - minuteYAnimation2, -1);
            }

            if (playTime[0] > 0) {
                hourYAnimation = (float) DrRenderUtils.animate(20 * (playTime[0] % 10), hourYAnimation, .02);

                Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.drawString("h", playtimeX + playtimeWidth - 44, y + 24, -1);
                for (int i = 0; i < 10; i++) {
                    Fonts.SFBOLD.SFBOLD_18.SFBOLD_18.drawString(String.valueOf(i), playtimeX + playtimeWidth - 49, y + 24 + (i * 20) - hourYAnimation, -1);
                }

            }

        }


        StencilUtil.uninitStencilBuffer();
    }

    public static int[] getPlayTime() {
        long diff = getTimeDiff();
        long diffSeconds = 0, diffMinutes = 0, diffHours = 0;
        if (diff > 0) {
            diffSeconds = diff / 1000 % 60;
            diffMinutes = diff / (60 * 1000) % 60;
            diffHours = diff / (60 * 60 * 1000) % 24;
        }
       /* String str = (int) diffSeconds + "s";
        if (diffMinutes > 0) str = (int) diffMinutes + "m " + str;
        if (diffHours > 0) str = (int) diffHours + "h " + str;*/
        return new int[]{(int) diffHours, (int) diffMinutes, (int) diffSeconds};
    }

    public static long getTimeDiff() {
        return (endTime == -1 ? System.currentTimeMillis() : endTime) - startTime;
    }

    public static void reset() {
        startTime = System.currentTimeMillis();
        endTime = -1;
        gamesPlayed = 0;
        killCount = 0;
    }



}
