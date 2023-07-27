/*
 * Decompiled with CFR 0_132.
 */
package Recall.Clickgui.Jello.font.Unicode;

//import liying.Jello.font.CFontRenderer;
import Recall.Clickgui.Jello.font.CFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.InputStream;

public abstract class UnicodeFontLoaders {

 //   public static CFontRenderer kiona12 = new CFontRenderer(FontLoaders.getKiona(12), true, true);
       public static CFontRenderer Sans14 = new CFontRenderer(UnicodeFontLoaders.getFont("googlesans",12), true, true);

    public static Font getFont(String name,int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidBounce/fonts/"+name+".ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    private static Font getNL(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("fonts/neverlose_icon.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getGoogleSans(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("fonts/Product Sans.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getNovICON(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("fonts/NovICON.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getFluxIcon(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("fonts/NovICON.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getStyIcon(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("fonts/stylesicons.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getHanabiIcon(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("fonts/icon.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getComfortaa(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/fonts/comfortaa.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    private static Font getCSGOICON(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("client/fonts/csgo.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    public static Font getxyzn(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("client/fonts/Arial.ttf")).getInputStream();
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

