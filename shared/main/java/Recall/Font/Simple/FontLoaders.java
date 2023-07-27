/*
 * Decompiled with CFR 0_132.
 */
package Recall.Font.Simple;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;

public abstract class FontLoaders {

    public static CFontRenderer F14 = new CFontRenderer(FontLoaders.getFont(14), true, true);
    public static CFontRenderer F16 = new CFontRenderer(FontLoaders.getFont(16), true, true);
    public static CFontRenderer F18 = new CFontRenderer(FontLoaders.getFont(18), true, true);
    public static CFontRenderer F20 = new CFontRenderer(FontLoaders.getFont(20), true, true);
    public static CFontRenderer F22 = new CFontRenderer(FontLoaders.getFont(22), true, true);
    public static CFontRenderer F23 = new CFontRenderer(FontLoaders.getFont(23), true, true);
    public static CFontRenderer F24 = new CFontRenderer(FontLoaders.getFont(24), true, true);
    public static CFontRenderer F30 = new CFontRenderer(FontLoaders.getFont(30), true, true);
    public static CFontRenderer F40 = new CFontRenderer(FontLoaders.getFont(40), true, true);
    public static CFontRenderer F90 = new CFontRenderer(FontLoaders.getFont(90), true, true);
    public static CFontRenderer xyz16 = new CFontRenderer(getxyz(16), true, true);
    public static CFontRenderer xyz18 = new CFontRenderer(getxyz(18), true, true);
    public static CFontRenderer xyz20 = new CFontRenderer(getxyz(20), true, true);
    public static CFontRenderer xyz22 = new CFontRenderer(getxyz(22), true, true);
    public static CFontRenderer xyz28 = new CFontRenderer(getxyz(28), true, true);
    public static CFontRenderer xyz32 = new CFontRenderer(getxyz(32), true, true);
    public static CFontRenderer xyz26 = new CFontRenderer(getxyz(26), true, true);
    public static CFontRenderer xyz70 = new CFontRenderer(getxyz(70), true, true);
    public static CFontRenderer C16 = new CFontRenderer(getComfortaa(16), true, true);
    public static CFontRenderer C18 = new CFontRenderer(getComfortaa(18), true, true);
    public static CFontRenderer C20 = new CFontRenderer(getComfortaa(20), true, true);
    public static CFontRenderer C22 = new CFontRenderer(getComfortaa(22), true, true);
    public static CFontRenderer C30 = new CFontRenderer(getComfortaa(30), true, true);
    public static CFontRenderer C50 = new CFontRenderer(getComfortaa(50), true, true);
    public static CFontRenderer C12 = new CFontRenderer(FontLoaders.getComfortaa(12), true, true);
    public static CFontRenderer C14 = new CFontRenderer(FontLoaders.getComfortaa(14), true, true);
    public static CFontRenderer C55 = new CFontRenderer(FontLoaders.getComfortaa(55), true, true);

    public static CFontRenderer Logo = new CFontRenderer(FontLoaders.getNovo(40), true, true);

    public static ArrayList<CFontRenderer> fonts = new ArrayList();


    public static CFontRenderer getFontRender(int size) {
        return fonts.get(size - 10);
    }
    private static Font getxyz(int size) {
        Font font;

        try {
            InputStream ex = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/huahuo.ttf")).getInputStream();

            font = Font.createFont(0, ex);
            font = font.deriveFont(0, (float) size);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }

        return font;
    }
    public static Font getFont(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/huahuo.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    public static Font getComfortaa(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager()
                    .getResource(new ResourceLocation("liquidbounce/font/huahuo.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    public static Font getNovo(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager()
                    .getResource(new ResourceLocation("liquidbounce/font/huahuo.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

}

