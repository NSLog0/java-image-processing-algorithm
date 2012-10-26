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
public class Histogram {

    public static int[] histogtam(BufferedImage _image) {
        int interval[] = new int[256];
        for (int i = 0; i < _image.getWidth(); i++) {
            for (int j = 0; j < _image.getHeight(); j++) {
                int p = RGB.getRGBW(_image, i, j);
                int r = (p >> 16) & 0xff;
                interval[r]++;
            }

        }
        return interval;

    }

}
