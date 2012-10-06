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
public class GetPixels{

    //  static int w, h;
    // use for store pixel image  to array 
    public static int[][] getPixel(BufferedImage image) {
        int pixel[][] = new int[image.getWidth()][image.getHeight()];
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                pixel[i][j] = image.getRGB(i, j);
            }
        }
        // return pixels
        return pixel;
    }

}
