/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image2d;

import java.awt.image.BufferedImage;

public class RGB {

 public static int doGetRGB(BufferedImage image, int i, int j) {
        int width = image.getWidth();
        int height = image.getHeight();
        i = Math.max(0, Math.min(width - 1, i));
        j = Math.max(0, Math.min(height - 1, j));
        return image.getRGB(i, j);
    }
}
