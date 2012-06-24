/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image2d;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author pratchaya
 */
public class ImageProcessor {

    // get image form url to BufferedImage
    public static BufferedImage load_image(String url) {
        // init BufferedImage null
        BufferedImage image = null;
        try {
            // read file form BufferedImage
            image = ImageIO.read(new File(url));

        } catch (Exception e) {
            // print when error
            System.out.println(e.getMessage());
        }
        // return result image when read url done!
        return image;
    }
    //---------------------------------end--------------------------------------

    // -----------------------------Convolution --------------------------------
    public static BufferedImage convolution(BufferedImage _images, double kernel[][], int wigth, int height, int sizeKernel, int kernelXY) {
        BufferedImage imageOutput = copyImg(_images);     // Set initial BufferedImage
        int pixel[][] = pixels.getPixels(_images); //use to store pixels

        // calculate image
        for (int i = 0 + kernelXY; i < wigth - kernelXY - 1; i++) {
            for (int j = 0 + kernelXY; j < height - kernelXY - 1; j++) {
                int a = 0, r = 0, g = 0, b = 0; // store RGB

                for (int k = -(kernelXY); k < kernelXY + 1; k++) {
                    for (int l = -(kernelXY); l < kernelXY + 1; l++) {

                        // calculate a RGB by chip bit
                        a += RGB.alpha(pixel, i - (k), j - (l)) * kernel[k + kernelXY][l + kernelXY];
                        r += RGB.red(pixel, i - (k), j - (l)) * kernel[k + kernelXY][l + kernelXY];
                        g += RGB.green(pixel, i - (k), j - (l)) * kernel[k + kernelXY][l + kernelXY];
                        b += RGB.blue(pixel, i - (k), j - (l)) * kernel[k + kernelXY][l + kernelXY];

                    } //end k
                }//end j
                //System.out.println(i + "," + j + ": " + "RED: " + r + " GREEN: " + g + " BLUE: " + b + "\n");
                int rgb = ((a & 0xff) << 24) | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
                //set RGB revert to image
                imageOutput.setRGB(i, j, rgb);
            }// end i
        }  //end j

        return imageOutput;
    }
//----------------------------------end-----------------------------------------
// -------------------------------Fillter --------------------------------------

    public static BufferedImage gaussianFillter(BufferedImage _image, int _height, int _wight, double sigma) {

        // kernel gaussian
        double gaussian[][] = new double[_wight][_height];
        System.out.println("gaussing value: ");
        for (int j = 0; j < _height; j++) {
            for (int i = 0; i < _wight; i++) {
                int xValue = i - (_wight / 2);
                int yValue = j - (_height / 2);
                gaussian[i][j] = (1 / (2 * Math.PI * Math.pow(sigma, 2))) * (Math.pow(Math.E, -((Math.pow(xValue, 2) + Math.pow(yValue, 2)) / (2 * Math.pow(sigma, 2)))));
                System.out.print(gaussian[i][j] + " ");
                //   System.out.print(gaussian[i][j]);
            }
            System.out.println(' ');
        }

        // get value _image and kernel
        int wight = _image.getWidth(); // image wight
        int heigth = _image.getHeight(); // image hight 
        int kernelSize = gaussian.length; // size kernel
        int kernelXY = kernelSize / 2; // find a center of kernel
        System.out.println("kernel size / 2: " + kernelXY);

        // make a result with convolution method
        // return image result
        return convolution(_image, gaussian, wight, heigth, kernelSize, kernelXY);

    }

    public static BufferedImage grayscaleFillter(BufferedImage _image) {
        BufferedImage imageOutput = ImageProcessor.copyImg(_image);
        int newPixel;
        int p[][] = pixels.getPixels(_image);
        for (int i = 0; i < _image.getWidth(); i++) {
            for (int j = 0; j < _image.getHeight(); j++) {
                int a, r, g, b;
                a = RGB.alpha(p, i, j);
                r = RGB.red(p, i, j);
                g = RGB.green(p, i, j);
                b = RGB.blue(p, i, j);
                r = (int) (0.21 * r + 0.71 * g + 0.07 * b);
                newPixel = colorToRGB(a, r, r, r);
                imageOutput.setRGB(i, j, newPixel);
            }
        }
        return imageOutput;
    }
    //----------------------------------end Fillter--------------------------------------------

    //----------------------------------- helper method------------------------------------
    // use to copy image 
    public static BufferedImage copyImg(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();

        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    private static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }
}
