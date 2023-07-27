/*
 * Decompiled with CFR 0_132.
 */
package Recall.Clickgui.Jello.font;

import net.ccbluex.liquidbounce.LiquidBounce;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public abstract class FontLoaders {


    public static CFontRenderer Sans18 = new CFontRenderer(FontLoaders.getFont("googlesans",18), true, true);

    public static CFontRenderer JelloTitle20 = new CFontRenderer(FontLoaders.getFont("jellolight",20), true, true);
    public static CFontRenderer JelloM20 = new CFontRenderer(FontLoaders.getFont("jellomedium",20), true, true);
    public static CFontRenderer JelloM18 = new CFontRenderer(FontLoaders.getFont("jellomedium",18), true, true);

    public static CFontRenderer JelloM14 = new CFontRenderer(FontLoaders.getFont("jellomedium",14), true, true);
    public static CFontRenderer JelloTitle18 = new CFontRenderer(FontLoaders.getFont("jellolight",18), true, true);
    public static CFontRenderer jellolightnew20 = new CFontRenderer(FontLoaders.getFont("jellolight.ttf",20), true, true);
    public static CFontRenderer jellolightnew16 = new CFontRenderer(FontLoaders.getFont("jellolight.ttf",16), true, true);
    public static CFontRenderer jellolightnew18= new CFontRenderer(FontLoaders.getFont("jellolight.ttf",18), true, true);
    public static CFontRenderer jellolightnew22 = new CFontRenderer(FontLoaders.getFont("jellolight2.ttf",22), true, true);
    public static CFontRenderer jellolightnew26 = new CFontRenderer(FontLoaders.getFont("jellolight2.ttf",26), true, true);
    public static CFontRenderer icon30 = new CFontRenderer(FontLoaders.getFont("ico.ttf",30), true, true);
    public static CFontRenderer nicon30 = new CFontRenderer(FontLoaders.getFont("nicon.ttf",30), true, true);
    public static CFontRenderer check20 = new CFontRenderer(FontLoaders.getFont("check.ttf",20), true, true);
    public static CFontRenderer check35 = new CFontRenderer(FontLoaders.getFont("check.ttf",35), true, true);
    public static CFontRenderer tenacity18 = new CFontRenderer(FontLoaders.getFont("tenacity.ttf",18), true, true);
    public static CFontRenderer jellolightnew30 = new CFontRenderer(FontLoaders.getFont("jellolight.ttf",30), true, true);
    public static CFontRenderer jellolightnew48 = new CFontRenderer(FontLoaders.getFont("jellolight.ttf",48), true, true);
    public static CFontRenderer JelloList16 = new CFontRenderer(FontLoaders.getFont("jelloregular.ttf",16), true, true);
    public static ArrayList<CFontRenderer> fonts = new ArrayList();
    public static CFontRenderer getFontRender(int size) {
        return fonts.get(size - 10);
    }
    public static Font getFont(String name,int size) {
        try {
            final InputStream inputStream = new FileInputStream(new File(LiquidBounce.fileManager.fontsDir, name));
//            final InputStream inputStream = mc2.getResourceManager().getResource(new ResourceLocation("liquidbounce/fonts/" + fontName)).getInputStream();

            Font awtClientFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtClientFont = awtClientFont.deriveFont(Font.PLAIN, size);
            inputStream.close();
            return awtClientFont;
        } catch (final Exception e) {
            e.printStackTrace();
            return new Font("default", Font.PLAIN, size);
        }
    }
}

