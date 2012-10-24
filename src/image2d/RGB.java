/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image2d;

import java.awt.image.BufferedImage;

public class RGB {

    // shift bit red color
    public static int alpha(int pixels[][], int x, int y) {
        int a = 0;
        a = (pixels[x][y] >> 24) & 0xff;
        return a;
    }

    // shift bit red color
    public static int red(int pixels[][], int x, int y) {
        int r = 0;
        r = (pixels[x][y] >> 16) & 0xff;
        return r;
    }
    // shift bit green color

    public static int green(int pixels[][], int x, int y) {
        int r = 0;
        r = (pixels[x][y] >> 8) & 0xff;
        return r;
    }
    // shift bit blue color

    public static int blue(int pixels[][], int x, int y) {
        int r = 0;
        r = (pixels[x][y]) & 0xff;
        return r;
    }
    // return bit RGB 

    public static int getRGBExtended(BufferedImage image, int i, int j) {
        int width = image.getWidth();
        int height = image.getHeight();
        i = Math.max(0, Math.min(width - 1, i));
        j = Math.max(0, Math.min(height - 1, j));
        return image.getRGB(i, j);
    }
}
