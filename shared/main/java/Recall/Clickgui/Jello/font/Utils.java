/*
 * Decompiled with CFR 0_132.
 */
package Recall.Clickgui.Jello.font;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static boolean fuck = true;
    private static Minecraft mc = Minecraft.getMinecraft();

    public static boolean isContainerEmpty(Container container) {
        int i = 0;
        int slotAmount = container.inventorySlots.size() == 90 ? 54 : 27;
        while (i < slotAmount) {
            if (container.getSlot(i).getHasStack()) {
                return false;
            }
            ++i;
        }
        return true;
    }

    public static Minecraft getMinecraft() {
        return mc;
    }


    public static String getMD5(String input) {
        StringBuilder res = new StringBuilder();
        try {
            byte[] md5;
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(input.getBytes());
            byte[] arrby = md5 = algorithm.digest();
            int n = arrby.length;
            int n2 = 0;
            while (n2 < n) {
                byte aMd5 = arrby[n2];
                String tmp = Integer.toHexString(255 & aMd5);
                if (tmp.length() == 1) {
                    res.append("0").append(tmp);
                } else {
                    res.append(tmp);
                }
                ++n2;
            }
        }
        catch (NoSuchAlgorithmException algorithm) {
            // empty catch block
        }
        return res.toString();
    }


    public static int add(int number, int add, int max) {
        return number + add > max ? max : number + add;
    }

    public static int remove(int number, int remove, int min) {
        return number - remove < min ? min : number - remove;
    }

    public static int check(int number) {
        return number <= 0 ? 1 : (number > 255 ? 255 : number);
    }


}

