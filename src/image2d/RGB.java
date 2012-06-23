/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image2d;

public class RGB {

    // shift bit red color
    public static int red(int pixels[][], int y, int x) {
        int r = 0;
        r = (pixels[x][y] >> 16) & 0x000000FF;
        return r;
    }
    // shift bit green color

    public static int green(int pixels[][], int x, int y) {
        int g = 0;
        g = (pixels[x][y] >> 8) & 0x000000FF;
        return g;
    }
    // shift bit blue color

    public static int blue(int pixels[][], int x, int y) {
        int b = 0;
        b = (pixels[x][y]) & 0x000000FF;
        return b;
    }
    // return bit RGB 

    public static int convertRGB(int pixels[][], int x, int y) {
        int r = 0, g = 0, b = 0;
        int rgb = (pixels[x][y] << 16) | (pixels[x][y] << 8) | pixels[x][y];
        return rgb;
    }
}
