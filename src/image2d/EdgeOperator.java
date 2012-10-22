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
public class EdgeOperator {

    public static BufferedImage edgeHorizontal(BufferedImage _image) {
        BufferedImage image = Unitys.copyImg(_image);
        double sobel[][] = {
            {-1, -2, -1},
            {0, 0, 0},
            {1, 2, 1}
        };
        int wight = _image.getWidth(); // image wight
        int heigth = _image.getHeight(); // image hight 
        int kernelSize = sobel.length; // size kernel
        int kernelXY = kernelSize / 2; // find a center of kernel

        // make a result with convolution method
        // return image result
        image = ImageProcessor.convolution(image, sobel, wight, heigth, kernelSize, kernelXY);

        return image;
    }

    public static BufferedImage edgeVertical(BufferedImage _image) {
        BufferedImage image = Unitys.copyImg(_image);
        double sobel[][] = {
            {-1, 0, 1},
            {-2, 0, 2},
            {-1, 0, 1}
        };
        int wight = _image.getWidth(); // image wight
        int heigth = _image.getHeight(); // image hight 
        int kernelSize = sobel.length; // size kernel
        int kernelXY = kernelSize / 2; // find a center of kernel

        // make a result with convolution method
        // return image result
        image = ImageProcessor.convolution(image, sobel, wight, heigth, kernelSize, kernelXY);

        return image;
    }
}
