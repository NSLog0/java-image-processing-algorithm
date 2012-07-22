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
import javax.swing.JOptionPane;

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
            if(!image.equals(null)){
                System.out.println("Load data at URL:"+url+" done.");
                            
            }

        } catch (Exception e) {
           
            JOptionPane.showMessageDialog(null, e.getMessage()+" File does not exist because name or extension file is not correct."
                    + "\nPlease check your file again.");
        }
        // return result image when read url done!
        return image;
    }
    //---------------------------------end--------------------------------------

    // -----------------------------Convolution --------------------------------
    public static BufferedImage convolution(BufferedImage _images, double kernel[][], int wigth, int height, int sizeKernel, int kernelXY) {
        BufferedImage imageOutput = copyImg(_images);     // Set initial BufferedImage
        int pixel[][] = GetPixels.getPixel(_images); //use to store pixels

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
        for (int j = 0; j < _height; j++) {
            for (int i = 0; i < _wight; i++) {
                int xValue = i - (_wight / 2);
                int yValue = j - (_height / 2);
                gaussian[i][j] = (1 / (2 * Math.PI * Math.pow(sigma, 2))) * (Math.pow(Math.E, -((Math.pow(xValue, 2) + Math.pow(yValue, 2)) / (2 * Math.pow(sigma, 2)))));

            }

        }

        // get value _image and kernel
        int wight = _image.getWidth(); // image wight
        int heigth = _image.getHeight(); // image hight 
        int kernelSize = gaussian.length; // size kernel
        int kernelXY = kernelSize / 2; // find a center of kernel

        // make a result with convolution method
        // return image result
        return convolution(_image, gaussian, wight, heigth, kernelSize, kernelXY);
      
    }

    public static BufferedImage grayscaleFillter(BufferedImage _image) {
        BufferedImage imageOutput = ImageProcessor.copyImg(_image);
        int grays;
        int p[][] = GetPixels.getPixel(_image);
        for (int i = 0; i < _image.getWidth(); i++) {
            for (int j = 0; j < _image.getHeight(); j++) {
                int a, r, g, b;
                a = RGB.alpha(p, i, j);
                r = RGB.red(p, i, j);
                g = RGB.green(p, i, j);
                b = RGB.blue(p, i, j);
                grays = (int) (0.2125 * r + 0.7154 * g + 0.0721 * b);
                a = (a << 24);
                r = (grays << 16);
                g = (grays << 8);
                b = (grays);
                grays = a + r + g + b;
                imageOutput.setRGB(i, j, grays);
            }
        }
        return imageOutput;
    }

    public static int[] histogtam(BufferedImage _image) {
        BufferedImage output = copyImg(_image);
        int pixels[][] = GetPixels.getPixel(_image);
        int interval[] = new int[256];
        for (int i = 0; i < _image.getWidth(); i++) {
            for (int j = 0; j < _image.getHeight(); j++) {
                int r = (pixels[i][j] >> 16) & 0xff;
                interval[r]++;
            }

        }

        return interval;

    }

    public static int[] balancing(BufferedImage _image) {
        int _histogram[] = histogtam(_image);
        int _factor[] = new int[256];
        for (int i = 0; i < _factor.length; i++) {
            _factor[i] = 0;
        }
        long sum = 0;
        float scale = (float) (255.0 / (_image.getWidth() * _image.getHeight()));

        for (int i = 0; i < _factor.length; i++) {
            sum += _histogram[i];
            int value = (int) (sum * scale);
            if (value > 255) {
                _factor[i] = 255;
            } else {
                _factor[i] = value;
            }
        }
        return _factor;

    }

    public static BufferedImage balancingImg(BufferedImage _image) {
        int new_Histogram[] = balancing(_image);
        BufferedImage output = copyImg(_image);
        int pixel[][] = GetPixels.getPixel(output);
        int r, g, b, p;
        for (int i = 0; i < _image.getWidth(); i++) {
            for (int j = 0; j < _image.getHeight(); j++) {
                r = RGB.red(pixel, i, j);
                g = RGB.green(pixel, i, j);
                b = RGB.blue(pixel, i, j);

                r = new_Histogram[r];
                g = new_Histogram[g];
                b = new_Histogram[b];

                r = (r << 16);
                g = (g << 8);
                b = (b);

                p = r + g + b;

                output.setRGB(i, j, p);
            }

        }

        return output;

    }

    public static BufferedImage threshold(BufferedImage _image) {
        int _r, p, r, g, b;
        int threshold = otsuTreshold(_image);
        BufferedImage imageOutput = copyImg(_image);
        for (int i = 0; i < _image.getWidth(); i++) {
            for (int j = 0; j < _image.getHeight(); j++) {

                // Get pixels
                r = new Color(_image.getRGB(i, j)).getRed();
                int alpha = new Color(_image.getRGB(i, j)).getAlpha();
                if (r > threshold) {
                    p = 255;
                } else {
                    p = 0;
                }
                alpha = (alpha << 24);
                r = (p << 16);
                g = (p << 8);
                b = (p);

                p = alpha + r + g + b;
                imageOutput.setRGB(i, j, p);

            }
        }

        return imageOutput;

    }
// --------------------------------not ok!!! ------------------------------

    public static int otsuTreshold(BufferedImage _image) {
        int _histogram[] = histogtam(_image);

        int total = _image.getWidth() * _image.getHeight();
        float sum = 0;
        for (int i = 0; i < 256; i++) {
            sum += i * _histogram[i];
        }
        float sum_bg = 0;
        int wight_bg = 0, wight_fg = 0;

        float varMax = 0;
        int threshold = 0;

        for (int i = 0; i < 256; i++) {
            wight_bg += _histogram[i];
            if (wight_bg == 0) {
                continue;
            }
            wight_fg = total - wight_bg;

            if (wight_fg == 0) {
                break;
            }

            sum_bg += (float) (i * _histogram[i]);
            float mean_bg = sum_bg / wight_bg;
            float mean_fg = (sum - sum_bg) / wight_fg;
            float varBetween = (float) wight_bg * (float) wight_fg * (mean_bg - mean_fg) * (mean_bg - mean_fg);
            if (varBetween > varMax) {
                varMax = varBetween;
                threshold = i;
            }
        }
       
        return threshold;
    }

//----------------------------------end Fillter-------------------------------------
//----------------------------------- helper method---------------------------------
// use to copy image 
    public static BufferedImage copyImg(BufferedImage _image) {
        ColorModel cm = _image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = _image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);

    }
}
