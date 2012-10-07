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

    public static BufferedImage erode(int kernel, BufferedImage _image) {
        BufferedImage imageOutput = Unitys.copyImg(_image); // copy image
        int pixel[][] = doPixels.getPixel(imageOutput); // get pixel
        int kernelMask[][] = new int[kernel][kernel];
        int kernelXY = Unitys.operation_Number("/", kernelMask.length, 2);
        int temp = 0;
        for (int i = 0; i < kernelMask.length; i++) {
            for (int j = 0; j < kernelMask.length; j++) {
                kernelMask[i][j] = 1;
            }
        }
        for (int i = 0 + kernelXY; i < _image.getWidth() - kernelXY - 1; i++) {
            for (int j = 0 + kernelXY; j < _image.getHeight() - kernelXY - 1; j++) {
                int c = 0;
                for (int k = -(kernelXY); k < kernelXY + 1; k++) {
                    for (int l = -(kernelXY); l < kernelXY + 1; l++) {
                    }
                }
            }
        }
        return imageOutput;
    }
}