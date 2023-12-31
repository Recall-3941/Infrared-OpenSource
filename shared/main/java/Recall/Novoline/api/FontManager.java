/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package Recall.Novoline.api;


@FunctionalInterface
public interface FontManager {

    FontFamily fontFamily(FontType fontType);

    default FontRenderer font(FontType fontType, int size) {
        return fontFamily(fontType).ofSize(size);
    }
}
