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
public class Guassian extends Filter {
    double sigma = 0;
    int size = 3;
    public Guassian(double sigma, int size){
        this.sigma = sigma;
        this.size = size; 
    }
    
    public static double[][] kernel(int _size , double sigma) {
        int size = _size;
        int _height = 5;
        double gaussian[][] = new double[size][size];
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                int xValue = i - (size / 2);
                int yValue = j - (size / 2);
                gaussian[i][j] = (1 / (2 * Math.PI * Math.pow(sigma, 2))) * (Math.pow(Math.E, -((Math.pow(xValue, 2) + Math.pow(yValue, 2)) / (2 * Math.pow(sigma, 2)))));
            }
        }

        return gaussian;
    }

    @Override
    public BufferedImage apply(BufferedImage _image) {

        BufferedImage imageOutput = Unitys.copyImage(_image);     // Set initial BufferedImage
        int c = 0;

        double gaussian[][] = kernel(size, this.sigma);
        int wight = _image.getWidth(); // image wight
        int heigth = _image.getHeight(); // image hight 
        int kernelSize = gaussian.length; // size kernel
        int kernelXY = kernelSize / 2; // find a center of kernel

        // make a result with convolution method
        // return image result

        for (int i = 0 + kernelXY; i < wight - kernelXY - 1; i++) {
            for (int j = 0 + kernelXY; j < heigth - kernelXY - 1; j++) {
                int r = 0, g = 0, b = 0; // store RGB
                int p = RGB.getRGBExtended(_image, i, j);
                for (int k = -(kernelXY); k < kernelXY + 1; k++) {
                    for (int l = -(kernelXY); l < kernelXY + 1; l++) {

                        // calculate a RGB by chip bit
                        r += ((p >> 16) & 0xFF) * gaussian[k + kernelXY][l + kernelXY];
                        g += ((p >> 8) & 0xFF) * gaussian[k + kernelXY][l + kernelXY];
                        b += (p & 0xFF) * gaussian[k + kernelXY][l + kernelXY];

                    } //end k
                }//end j
                int rgb = ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
                //set RGB revert to image
                imageOutput.setRGB(i, j, rgb);
            }// end i
        }  //end j

        return imageOutput;
    }

}
