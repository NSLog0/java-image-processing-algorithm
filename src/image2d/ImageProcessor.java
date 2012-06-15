/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image2d;

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
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(url)); // read file form BufferedImage

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return image; // return result
    }
    //---------------------------------end--------------------------------------
    
    
    
    // -----------------------------Convolution --------------------------------
    public static BufferedImage convolution(BufferedImage images, float kernel[][], int wigth, int height, int sizeKernel, int kernelXY) {
        BufferedImage imageOutput = images.getSubimage(0, 0, wigth, height); // Set initial BufferedImage
        int r = 0, g = 0, b = 0; // Store channel color RGB 
        RGB c = new RGB(); // Chip bit RGB Class
        int pixels[][] = new int[wigth][height]; // Initial array Store image to array and size equal Image size


        // calculate imageInput --------------------
        for (int i = 0; i < wigth; i++) {
            for (int j = 0; j < height; j++) {
                // get pixels to array
                pixels[i][j] = imageOutput.getRGB(i, j);
                for (int k = i; k < sizeKernel - 1; k++) {
                    for (int l = j; l < sizeKernel - 1; l++) {
                        int xloc = i + (k - kernelXY);
                        int yloc = j + (l - kernelXY);
                        if (xloc >= 0 && xloc < i && yloc >= 0 && yloc < j) {
                            //try {

                            r += c.red(pixels, xloc, yloc) * kernel[i - k + 1][j - l + 1] / 16; // calculate a RED and call red method
                            g += c.green(pixels, xloc, yloc) * kernel[i - k + 1][j - l + 1] / 16; // calculate a GREEN and call green method
                            b += c.blue(pixels, xloc, yloc) * kernel[i - k + 1][j - l + 1] / 16; // calculate a BLUE and call blue method

                            int rgb = (r << 16) | (g << 8) | b;
                            imageOutput.setRGB(i, j, rgb);
                            //  } catch (Exception e) {
                            //      System.out.println(e.getMessage());
                            //  }

                        }
                    }
                }
            }
        }
        return imageOutput;

    }
//----------------------------------end-----------------------------------------

    
    
    
    
// -------------------------------Fillter --------------------------------------
    public static BufferedImage gaussianFillter(BufferedImage img) {

        float gaussian[][] = {
            {7, 2, 7},
            {2, 4, 2},
            {7, 2, 7}
        };

        // get size imageInput and kernel
        int wight = img.getWidth(); // image wight
        int heigth = img.getHeight(); // image hight 
        int kernelSize = gaussian.length; // size kernel
        int kernelXY = kernelSize / 2; // find a center of kernel

        // make a result with convolution method
        return convolution(img, gaussian, wight, heigth, kernelSize, kernelXY); // call function get result

    }
}
