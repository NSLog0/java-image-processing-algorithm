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
public class Morphological {

    public static BufferedImage ErosionImage(BufferedImage _image) {
        BufferedImage imageOutput = Unitys.copyImg(_image);
        int pixel[][] = GetPixels.getPixel(_image); //use to store pixels
        int kernel[][] = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int tmp = 1000000;
    }
}