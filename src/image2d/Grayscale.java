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
public class Grayscale extends Filter {

    @Override
    public BufferedImage apply(BufferedImage _image) {
        runningFlag = true;
        BufferedImage imageOutput = Unitys.copyImage(_image);
        int grayscale;
        for (int i = 0; i < _image.getWidth(); i++) {
            for (int j = 0; j < _image.getHeight(); j++) {
                int rgb;
                int p = RGB.doGetRGB(_image, i, j);

                rgb = (int) ((((p >> 16) & 0xFF) * 0.2125) + (((p >> 8) & 0xFF) * 0.7154) + ((p & 0xFF) * 0.0721));
                rgb = (rgb << 16) | (rgb << 8) | (rgb);

                grayscale = rgb; //sum RGB
                imageOutput.setRGB(i, j, grayscale);
            }
        }
        cancelFlag = false;
        runningFlag = false;
        
        return imageOutput;
    }
}
