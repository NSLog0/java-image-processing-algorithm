/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image2d;

import java.awt.image.BufferedImage;

/**
 *
 * @author pratchaya
 */
public class pixels {

    //  static int w, h;
    public static int[][] getPixels(BufferedImage image) {
        int pixel[][] = new int[image.getWidth()][image.getHeight()];
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                pixel[i][j] = image.getRGB(i, j);
            }
        }
        return pixel;
    }

    /*
     * public static int[][] backup_Pixel(int p[][]) { int _w = w; int _h = h;
     * int backup[][] = new int[_w][_h]; return backup; }
     *
     *
     * public static int img_Heigth(int _h) { int height = _h; return height; }
     *
     * public static int img_wight(int _w) { int wight = _w; return wight; }
     *
     */
}
