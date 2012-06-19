/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image2d;

import java.awt.Color;
import java.awt.image.BufferedImage;
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
    public static BufferedImage convolution(BufferedImage _images, float kernel[][], int wigth, int height, int sizeKernel, int kernelXY) {
        BufferedImage imageOutput = _images.getSubimage(0, 0, wigth, height);       // Set initial BufferedImage
        int r = 0, g = 0, b = 0;            // Store channel color RGB 
        // Initial array Store image to array and size equal Image size
        int pixel[][] = pixels.getPixels(imageOutput);


        // calculate imageInput --------------------
        for (int i = 0; i < wigth; i++) {
            for (int j = 0; j < height; j++) {
                System.out.printf("%d,%d=", i, j);
                // get pixels to array ** old **
                //pixels[i][j] = imageOutput.getRGB(i, j); ** old **

//                r = RGB.red(pixel, i, j);
//                g = RGB.green(pixel, i, j);
//                b = RGB.blue(pixel, i, j);

                for (int k = i; k < sizeKernel - 1; k++) {
                    for (int l = j; l < sizeKernel - 1; l++) {

                        //  int xLocat = i + (k - kernelXY);
                        //  int yLocat = j + (l - kernelXY);

                        //     if (xLocat >= 0 && xLocat < i && yLocat >= 0 && yLocat < j) {
                        //try {

                        // calculate a RGB by chip bit
                        r += RGB.red(pixel, i, j) * (kernel[i - k + 1][j - l + 1] / 16);
                        g += RGB.green(pixel, i, j) * (kernel[i - k + 1][j - l + 1] / 16);
                        b += RGB.blue(pixel, i, j) * (kernel[i - k + 1][j - l + 1] / 16);

                        int rgb = (r << 16) | (g << 8) | b;
                        System.out.println("RED: " + r + " GREEN: " + g + " BLUE: " + b + "\n");
                        //set RGB revert to image
                        imageOutput.setRGB(i, j, rgb);

                        //  } catch (Exception e) {
                        //      System.out.println(e.getMessage());
                        //  }

                        //} // end if
                        //end j
                    }
                    //end k
                }
                //end j
            }
            // end i
        }
        return imageOutput;
    }
//----------------------------------end-----------------------------------------
// -------------------------------Fillter --------------------------------------

    public static BufferedImage gaussianFillter(BufferedImage _image) {

        // kernel gaussian
        float gaussian[][] = {
            {1, 2, 1},
            {2, 4, 2},
            {1, 2, 1}
        };

        // get value _image and kernel
        int wight = _image.getWidth(); // image wight
        int heigth = _image.getHeight(); // image hight 
        int kernelSize = gaussian.length; // size kernel
        int kernelXY = kernelSize / 2; // find a center of kernel

        // make a result with convolution method
        // return image result
        return convolution(_image, gaussian, wight, heigth, kernelSize, kernelXY);

    }
}
