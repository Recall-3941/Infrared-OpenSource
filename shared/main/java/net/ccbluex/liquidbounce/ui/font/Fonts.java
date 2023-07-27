/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
package net.ccbluex.liquidbounce.ui.font;

import Recall.CFonts.CFont.CFontRenderer;
import Recall.CFonts.FontDrawer;
import Recall.CFonts.FontLoaders;
import com.google.gson.*;

import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.misc.HttpUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.List;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Fonts extends MinecraftInstance {

    @FontDetails(fontName = "Minecraft Font")
    public static final IFontRenderer minecraftFont = mc.getFontRendererObj();
    private static final HashMap<FontInfo, IFontRenderer> CUSTOM_FONT_RENDERERS = new HashMap<>();

    @FontDetails(fontName = "Posterama", fontSize = 30)
    public static IFontRenderer Posterama30;

    @FontDetails(fontName = "comfortaa", fontSize = 120)
    public static IFontRenderer com120;
    @FontDetails(fontName = "comfortaa", fontSize = 40)
    public static IFontRenderer com40;
//
//    @FontDetails(fontName = "Roboto-Italic ", fontSize = 35)
//    public static IFontRenderer Italic35;
//    @FontDetails(fontName = "Roboto-Italic ", fontSize = 45)
//    public static IFontRenderer Italic45;
//    @FontDetails(fontName = "Roboto-Italic ", fontSize = 60)
//    public static IFontRenderer Italic60;
//    @FontDetails(fontName = "Roboto-Italic ", fontSize = 120)
//    public static IFontRenderer Italic120;
//
//    @FontDetails(fontName = "Roboto-Light.ttf ", fontSize = 35)
//    public static IFontRenderer Light35;
//    @FontDetails(fontName = "Roboto-Light.ttf ", fontSize = 45)
//    public static IFontRenderer Light45;
//    @FontDetails(fontName = "Roboto-Light.ttf ", fontSize = 60)
//    public static IFontRenderer Light60;

    @FontDetails(fontName = "Bold", fontSize = 30)
    public static IFontRenderer bold30;
    @FontDetails(fontName = "Bold", fontSize = 40)
    public static IFontRenderer bold40;
    @FontDetails(fontName = "Bold", fontSize = 35)
    public static IFontRenderer bold35;
//    @FontDetails(fontName = "Bold", fontSize = 45)
//    public static IFontRenderer bold45;
//    @FontDetails(fontName = "Bold", fontSize = 72)
//    public static IFontRenderer bold72;
//    @FontDetails(fontName = "Bold", fontSize = 180)
//    public static IFontRenderer bold180;

    @FontDetails(fontName = "regular", fontSize = 30)
    public static IFontRenderer regular30;
    @FontDetails(fontName = "regular", fontSize = 40)
    public static IFontRenderer regular40;
    @FontDetails(fontName = "regular", fontSize = 35)
    public static IFontRenderer regular35;
//    @FontDetails(fontName = "regular", fontSize = 45)
//    public static IFontRenderer regular45;
//    @FontDetails(fontName = "regular", fontSize = 72)
//    public static IFontRenderer regular72;
//    @FontDetails(fontName = "regular", fontSize = 180)
//    public static IFontRenderer regular180;
//    @FontDetails(fontName = "Roboto-Light.ttf ", fontSize = 120)
//    public static IFontRenderer Light120;

    @FontDetails(fontName = "Roboto Medium", fontSize = 35)
    public static IFontRenderer font35;
    @FontDetails(fontName = "Roboto Medium", fontSize = 30)
    public static IFontRenderer font30;
    @FontDetails(fontName = "Roboto Medium", fontSize = 25)
    public static IFontRenderer font25;
    @FontDetails(fontName = "MiSans", fontSize = 20)
    public static IFontRenderer font20;
    @FontDetails(fontName = "Roboto Medium", fontSize = 40)
    public static IFontRenderer font40;
    @FontDetails(fontName = "Roboto Medium", fontSize = 80)
    public static IFontRenderer font80;

//
    @FontDetails(fontName = "Roboto Bold", fontSize =72)
    public static IFontRenderer fontBold72;
//    @FontDetails(fontName = "Roboto Bold", fontSize = 120)
//    public static IFontRenderer fontBold120;
//    @FontDetails(fontName = "Roboto Bold", fontSize = 180)
//    public static IFontRenderer fontBold180;

    //
//    @FontDetails(fontName = "Poppins120", fontSize = 120)
//    public static IFontRenderer Poppins120;
    @FontDetails(fontName = "Poppins35", fontSize = 35)
    public static IFontRenderer Poppins35;
    //    @FontDetails(fontName = "Poppins37", fontSize = 37)
//    public static IFontRenderer Poppins37;
//    @FontDetails(fontName = "Poppins45", fontSize = 45)
//    public static IFontRenderer Poppins45;
//    @FontDetails(fontName = "Poppins60", fontSize = 60)
//    public static IFontRenderer Poppins60;
    @FontDetails(fontName = "Poppins40", fontSize = 24)
    public static IFontRenderer Poppins40;
    @FontDetails(fontName = "Poppins30", fontSize = 30)
    public static IFontRenderer Poppins30;
    //    @FontDetails(fontName = "Poppins72", fontSize = 72)
//    public static IFontRenderer Poppins72;
//
//    @FontDetails(fontName = "SFUI Regular", fontSize = 18)
//    public static IFontRenderer fontSFUI18;
    @FontDetails(fontName = "SFUI Regular", fontSize = 35)
    public static IFontRenderer fontSFUI35;
    @FontDetails(fontName = "SFUI Regular", fontSize = 15)
    public static IFontRenderer fontSFUI15;
    @FontDetails(fontName = "SFUI Regular", fontSize = 40)
    public static IFontRenderer fontSFUI40;
    //    @FontDetails(fontName = "SFUI Regular", fontSize = 56)
//    public static IFontRenderer fontSFUI56;
    @FontDetails(fontName = "SFUI Regular", fontSize = 120)
    public static IFontRenderer fontSFUI120;

//    @FontDetails(fontName = "AlibabaSansLight35", fontSize = 35)
//    public static IFontRenderer AlibabaSansLight35;
//    @FontDetails(fontName = "AlibabaSansLight45", fontSize = 45)
//    public static IFontRenderer AlibabaSansLight45;
//    @FontDetails(fontName = "AlibabaSansLight60", fontSize = 60)
//    public static IFontRenderer AlibabaSansLight60;

    @FontDetails(fontName = "ComfortaaRegular35", fontSize = 35)
    public static IFontRenderer ComfortaaRegular35;
    @FontDetails(fontName = "ComfortaaRegular45", fontSize = 45)
    public static IFontRenderer ComfortaaRegular45;
//    @FontDetails(fontName = "ComfortaaRegular60", fontSize = 60)
//    public static IFontRenderer ComfortaaRegular60;

    @FontDetails(fontName = "CasanovaScotia35", fontSize = 35)
    public static IFontRenderer CasanovaScotia35;
    @FontDetails(fontName = "CasanovaScotia45", fontSize = 45)
    public static IFontRenderer CasanovaScotia45;
    @FontDetails(fontName = "Roboto Sfui", fontSize = 35)
    public static IFontRenderer Sfui35;
    @FontDetails(fontName = "Roboto Wqy", fontSize = 30)
    public static IFontRenderer wqy30;
    @FontDetails(fontName = "Roboto Bold", fontSize = 180)
    public static IFontRenderer fontBold180;
    @FontDetails(fontName = "Roboto Wqy", fontSize = 35)
    public static IFontRenderer wqy35;
    @FontDetails(fontName = "Roboto Comfortaa", fontSize = 35)
    public static IFontRenderer Comfortaa35;

    @FontDetails(fontName = "tenacitybold", fontSize = 35)
    public static IFontRenderer tenacitybold35;


    @FontDetails(fontName = "Ico", fontSize = 20)
    public static IFontRenderer ico2;
    @FontDetails(fontName = "Nico", fontSize = 180)
    public static GameFontRenderer nico80;
    @FontDetails(fontName = "Posteraaa", fontSize = 35)
    public static IFontRenderer Posterama35;
    @FontDetails(fontName = "CNFont", fontSize = 40)
    public static CFontRenderer CNFont40;
    @FontDetails(fontName = "Comforaa", fontSize = 35)
    public static IFontRenderer Comforaa35;
    @FontDetails(fontName = "Comforaa", fontSize = 30)
    public static IFontRenderer Comforaa30;
    @FontDetails(fontName = "Biko", fontSize = 35)
    public static IFontRenderer Biko35;
    @FontDetails(fontName = "Biko", fontSize = 30)
    public static IFontRenderer Biko30;
    @FontDetails(fontName = "Helvetica-Light", fontSize = 40)
    public static IFontRenderer jello40;
    @FontDetails(fontName = "JelloLight", fontSize = 96)
    public static IFontRenderer jello96;
    @FontDetails(fontName = "Posterama", fontSize = 18)
    public static IFontRenderer posterama35;
    @FontDetails(fontName = "Tahoma Bold", fontSize = 35)
    public static GameFontRenderer fontTahoma;
    public static TTFFontRenderer fontTahomaSmall;
//    @FontDetails(fontName = "CasanovaScotia60", fontSize = 60)
//    public static IFontRenderer CasanovaScotia60;

    public static void loadFonts() {
        long l = System.currentTimeMillis();

        ClientUtils.getLogger().info("Loading Fonts.");

//        downloadFonts();
        fontTahomaSmall = new TTFFontRenderer(getFont("Tahoma.ttf", 11));
        fontTahoma = new GameFontRenderer(getFont("TahomaBold.ttf", 35));
        jello96 = classProvider.wrapFontRenderer(new GameFontRenderer(getFont("jellolight.ttf", 96)));
        jello40 = classProvider.wrapFontRenderer(new GameFontRenderer(getFont("jellolight2.ttf", 40)));
        CNFont40 = new CFontRenderer(getCNFont( 36), true, true);
        posterama35 = getLocalFont("posterama.ttf", 18);
        fontSFUI15 = classProvider.wrapFontRenderer(new GameFontRenderer(getSFUI(15)));
        Biko30 = classProvider.wrapFontRenderer(new GameFontRenderer(getBiko(30)));
        Biko35 = classProvider.wrapFontRenderer(new GameFontRenderer(getBiko(35)));
        Comforaa30 = classProvider.wrapFontRenderer(new GameFontRenderer(getcomforaa(30)));
        Comforaa35 = classProvider.wrapFontRenderer(new GameFontRenderer(getcomforaa(35)));
        Posterama35 = classProvider.wrapFontRenderer(new GameFontRenderer(getPosterama(35)));
        Posterama30 = classProvider.wrapFontRenderer(new GameFontRenderer(getPosterama(30)));
        ico2 = classProvider.wrapFontRenderer(new GameFontRenderer(gets2(16)));
        fontBold72 = classProvider.wrapFontRenderer(new GameFontRenderer(getfontBold( 72)));
        font20 = classProvider.wrapFontRenderer(new GameFontRenderer(getFont("MiSans.ttf", 20)));
        tenacitybold35 = classProvider.wrapFontRenderer(new GameFontRenderer(GetTenacityBold(24)));
        wqy35 = classProvider.wrapFontRenderer(new GameFontRenderer(getFont("Roboto-Wqy.ttf", 35)));
        Sfui35 = classProvider.wrapFontRenderer(new GameFontRenderer(getFont("Roboto-Sfui.ttf", 35)));
        wqy30 = classProvider.wrapFontRenderer(new GameFontRenderer(getFont("Roboto-Wqy.ttf", 30)));
        font35 = classProvider.wrapFontRenderer(new GameFontRenderer(getSFUI(35)));
        nico80 = new GameFontRenderer(getnico(80));
        font25 = classProvider.wrapFontRenderer(new GameFontRenderer(getSFUI(25)));
        font40 = classProvider.wrapFontRenderer(new GameFontRenderer(getSFUI(40)));
        font30 = classProvider.wrapFontRenderer(new GameFontRenderer(getSFUI(30)));
        font80 = classProvider.wrapFontRenderer(new GameFontRenderer(getSFUI(80)));
        com120 = classProvider.wrapFontRenderer(new GameFontRenderer(getCom(120)));
        com40 = classProvider.wrapFontRenderer(new GameFontRenderer(getCom(40)));
        fontBold180 = classProvider.wrapFontRenderer(new GameFontRenderer(getFont("Roboto-Bold.ttf", 180)));
        Comfortaa35 = classProvider.wrapFontRenderer(new GameFontRenderer(getFont("Roboto-Comfortaa.ttf",35)));

//        fontSFUI18 = classProvider.wrapFontRenderer(new GameFontRenderer(getSFUI(18)));
        fontSFUI35 = classProvider.wrapFontRenderer(new GameFontRenderer(getSFUI(35)));
        fontSFUI40 = classProvider.wrapFontRenderer(new GameFontRenderer(getSFUI(40)));
//        fontSFUI56 = classProvider.wrapFontRenderer(new GameFontRenderer(getSFUI(56)));
        fontSFUI120 = classProvider.wrapFontRenderer(new GameFontRenderer(getSFUI(120)));

        Poppins35 =classProvider.wrapFontRenderer(new GameFontRenderer(getPoppins( 35)));
//        Poppins37=classProvider.wrapFontRenderer(new GameFontRenderer(getPoppins(37)));
        Poppins30=classProvider.wrapFontRenderer(new GameFontRenderer(getPoppins(30)));

        Poppins40 =classProvider.wrapFontRenderer(new GameFontRenderer(getPoppins( 40)));
//        Poppins60=classProvider.wrapFontRenderer(new GameFontRenderer(getPoppins(60)));
//        Poppins45=classProvider.wrapFontRenderer(new GameFontRenderer(getPoppins( 45)));
//        Poppins72 =classProvider.wrapFontRenderer(new GameFontRenderer(getPoppins(72)));
//        Poppins120 =classProvider.wrapFontRenderer(new GameFontRenderer(getPoppins(120)));

        regular30 = classProvider.wrapFontRenderer(new GameFontRenderer(getregular(30)));
        regular35 = classProvider.wrapFontRenderer(new GameFontRenderer(getregular(35)));
        regular40 = classProvider.wrapFontRenderer(new GameFontRenderer(getregular(40)));
//        regular45 = classProvider.wrapFontRenderer(new GameFontRenderer(getregular(45)));
//        regular72 = classProvider.wrapFontRenderer(new GameFontRenderer(getregular(72)));
//        regular180 = classProvider.wrapFontRenderer(new GameFontRenderer(getregular(180)));

        bold35 = classProvider.wrapFontRenderer(new GameFontRenderer(getBold(35)));
        bold40 = classProvider.wrapFontRenderer(new GameFontRenderer(getBold(40)));
//        bold45 = classProvider.wrapFontRenderer(new GameFontRenderer(getBold(45)));
        bold30 = classProvider.wrapFontRenderer(new GameFontRenderer(getBold(30)));
//        bold72 = classProvider.wrapFontRenderer(new GameFontRenderer(getBold(72)));
//        bold180 = classProvider.wrapFontRenderer(new GameFontRenderer(getBold(180)));

//        AlibabaSansLight35 = classProvider.wrapFontRenderer(new GameFontRenderer(getAlibabaSansLight(35)));
//        AlibabaSansLight45 = classProvider.wrapFontRenderer(new GameFontRenderer(getAlibabaSansLight(45)));
//        AlibabaSansLight60 = classProvider.wrapFontRenderer(new GameFontRenderer(getAlibabaSansLight(60)));

        ComfortaaRegular35 = classProvider.wrapFontRenderer(new GameFontRenderer(getComfortaaRegular(35)));
        ComfortaaRegular45 = classProvider.wrapFontRenderer(new GameFontRenderer(getComfortaaRegular(45)));
//        ComfortaaRegular60 = classProvider.wrapFontRenderer(new GameFontRenderer(getComfortaaRegular(60)));

        CasanovaScotia35 = classProvider.wrapFontRenderer(new GameFontRenderer(getCasanovaScotia(35)));
        CasanovaScotia45 = classProvider.wrapFontRenderer(new GameFontRenderer(getCasanovaScotia(45)));
//        CasanovaScotia60 = classProvider.wrapFontRenderer(new GameFontRenderer(getCasanovaScotia(60)));

        try {
            CUSTOM_FONT_RENDERERS.clear();

            final File fontsFile = new File(LiquidBounce.fileManager.fontsDir, "fonts.json");

            if (fontsFile.exists()) {
                final JsonElement jsonElement = new JsonParser().parse(new BufferedReader(new FileReader(fontsFile)));

                if (jsonElement instanceof JsonNull)
                    return;

                final JsonArray jsonArray = (JsonArray) jsonElement;

                for (final JsonElement element : jsonArray) {
                    if (element instanceof JsonNull)
                        return;

                    final JsonObject fontObject = (JsonObject) element;

                    Font font = getFont(fontObject.get("fontFile").getAsString(), fontObject.get("fontSize").getAsInt());

                    CUSTOM_FONT_RENDERERS.put(new FontInfo(font), classProvider.wrapFontRenderer(new GameFontRenderer(font)));
                }
            } else {
                fontsFile.createNewFile();

                final PrintWriter printWriter = new PrintWriter(new FileWriter(fontsFile));
                printWriter.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonArray()));
                printWriter.close();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        ClientUtils.getLogger().info("Loaded Fonts. (" + (System.currentTimeMillis() - l) + "ms)");
    }
    private static Font getCNFont(final int size) {
        try {
            final InputStream inputStream = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/CNFont.ttf")).getInputStream();
            Font awtClientFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtClientFont = awtClientFont.deriveFont(Font.PLAIN, size);
            inputStream.close();
            return awtClientFont;
        } catch (final Exception e) {
            e.printStackTrace();

            return new Font("default", Font.PLAIN, size);
        }
    }

    private static void downloadFonts() {
        try {
            final File outputFile = new File(LiquidBounce.fileManager.fontsDir, "roboto.zip");

            if (!outputFile.exists()) {
                ClientUtils.getLogger().info("Downloading fonts...");
                HttpUtils.download(LiquidBounce.CLIENT_CLOUD + "/fonts/Roboto.zip", outputFile);
                ClientUtils.getLogger().info("Extract fonts...");
                extractZip(outputFile.getPath(), LiquidBounce.fileManager.fontsDir.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Font getnico(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/nico.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getBiko(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/Biko_Regular.otf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getcomforaa(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/Comfortaa-Regular.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getPosterama(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/PosteramaText-Regular.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("Posterama", 0, size);
        }
        return font;
    }
    private static Font getJello(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/jello.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font gets2(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/icon.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("Posterama", 0, size);
        }
        return font;
    }
    private static Font getCom(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/comfortaa.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getfontBold(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/roboto-medium.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    public static IFontRenderer getFontRenderer(final String name, final int size) {
        for (final Field field : Fonts.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);

                Object o = field.get(null);

                if (o instanceof IFontRenderer) {
                    FontDetails fontDetails = field.getAnnotation(FontDetails.class);

                    if (fontDetails.fontName().equals(name) && fontDetails.fontSize() == size)
                        return (IFontRenderer) o;
                }
            } catch (final IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return CUSTOM_FONT_RENDERERS.getOrDefault(new FontInfo(name, size), minecraftFont);
    }

    public static FontInfo getFontDetails(final IFontRenderer fontRenderer) {
        for (final Field field : Fonts.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);

                final Object o = field.get(null);

                if (o.equals(fontRenderer)) {
                    final FontDetails fontDetails = field.getAnnotation(FontDetails.class);

                    return new FontInfo(fontDetails.fontName(), fontDetails.fontSize());
                }
            } catch (final IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        for (Map.Entry<FontInfo, IFontRenderer> entry : CUSTOM_FONT_RENDERERS.entrySet()) {
            if (entry.getValue() == fontRenderer)
                return entry.getKey();
        }

        return null;
    }

    private static Font getPoppins(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/poppins.otf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getregular(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/regular.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getBold(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/bold.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    public float getCharWidth(char c) {
        return fontSFUI35.getStringWidth(String.valueOf(c));
    }

    private static Font getSFUI(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/sfuidisplayregular.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getComfortaaRegular(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/ComfortaaRegular.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getCasanovaScotia(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/CasanovaScotia.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getAlibabaSansLight(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/AlibabaSansLight.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font GetTenacityBold(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("loserline/font/tenacitybold.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getFlux(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/font/fluxicon.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    public static List<IFontRenderer> getFonts() {
        final List<IFontRenderer> fonts = new ArrayList<>();

        for (final Field fontField : Fonts.class.getDeclaredFields()) {
            try {
                fontField.setAccessible(true);

                final Object fontObj = fontField.get(null);

                if (fontObj instanceof IFontRenderer) fonts.add((IFontRenderer) fontObj);
            } catch (final IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        fonts.addAll(Fonts.CUSTOM_FONT_RENDERERS.values());

        return fonts;
    }

    private static Font getFont(final String fontName, final int size) {
        try {
            final InputStream inputStream = new FileInputStream(new File(LiquidBounce.fileManager.fontsDir, fontName));
            Font awtClientFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtClientFont = awtClientFont.deriveFont(Font.PLAIN, size);
            inputStream.close();
            return awtClientFont;
        } catch (final Exception e) {
            e.printStackTrace();

            return new Font("default", Font.PLAIN, size);
        }
    }
    private static IFontRenderer getLocalFont(final String fontName, final int size) {
        Font font;
        try {
            final InputStream inputStream = mc2.getResourceManager().getResource(new ResourceLocation("liquidbounce/font/" + fontName)).getInputStream();
            Font awtClientFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtClientFont = awtClientFont.deriveFont(Font.PLAIN, size);
            inputStream.close();
            font = awtClientFont;
        } catch (final Exception e) {
            e.printStackTrace();
            font = new Font("default", Font.PLAIN, size);
        }

        return classProvider.wrapFontRenderer(new GameFontRenderer(font));
    }

    private static void extractZip(final String zipFile, final String outputFolder) {
        final byte[] buffer = new byte[1024];

        try {
            final File folder = new File(outputFolder);

            if (!folder.exists()) folder.mkdir();

            final ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile));

            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                File newFile = new File(outputFolder + File.separator + zipEntry.getName());
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fileOutputStream = new FileOutputStream(newFile);

                int i;
                while ((i = zipInputStream.read(buffer)) > 0)
                    fileOutputStream.write(buffer, 0, i);

                fileOutputStream.close();
                zipEntry = zipInputStream.getNextEntry();
            }

            zipInputStream.closeEntry();
            zipInputStream.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static class FontInfo {
        private final String name;
        private final int fontSize;

        public FontInfo(String name, int fontSize) {
            this.name = name;
            this.fontSize = fontSize;
        }

        public FontInfo(Font font) {
            this(font.getName(), font.getSize());
        }

        public String getName() {
            return name;
        }

        public int getFontSize() {
            return fontSize;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            FontInfo fontInfo = (FontInfo) o;

            if (fontSize != fontInfo.fontSize) return false;
            return Objects.equals(name, fontInfo.name);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + fontSize;
            return result;
        }
    }

}