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
import java.util.ArrayList;
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
        int pixel[][] = Get_Pixels.getPixels(_images); //use to store pixels

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
        int newPixel;
        int p[][] = Get_Pixels.getPixels(_image);
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

    public static BufferedImage histogram_cal(BufferedImage _image) {
        int a, r, g, b, p = 0;
        BufferedImage imageOutput = copyImg(_image);

        ArrayList<int[]> histogramEQ = histogram(_image);

        for (int i = 0; i < _image.getWidth(); i++) {
            for (int j = 0; j < _image.getHeight(); j++) {

                // Get pixels by R, G, B
                a = new Color(_image.getRGB(i, j)).getAlpha();
                r = new Color(_image.getRGB(i, j)).getRed();
                g = new Color(_image.getRGB(i, j)).getGreen();
                b = new Color(_image.getRGB(i, j)).getBlue();

                // Set new pixel values using the histogram lookup table
                r = histogramEQ.get(0)[r];
                g = histogramEQ.get(1)[g];
                b = histogramEQ.get(2)[b];

                // Return back to original format
                p = colorToRGB(a, r, g, b);

                // Write pixels into image
                imageOutput.setRGB(i, j, p);

            }
        }


        return imageOutput;
    }

    // Get the histogram equalization lookup table for separate R, G, B channels
    public static ArrayList<int[]> histogram(BufferedImage _image) {

        // Fill the lookup table
        int histogramR[] = new int[256];
        int histogramG[] = new int[256];
        int histogramB[] = new int[256];
        // Get an image histogram - calculated values by R, G, B channels
        ArrayList<int[]> _histogram = imageHistogram(_image);

        // Create the lookup table
        ArrayList<int[]> imageHis = new ArrayList<int[]>();

        int sumr = 0;
        int sumg = 0;
        int sumb = 0;

        for (int i = 0; i < histogramR.length; i++) {
            histogramR[i] = 0;
            histogramG[i] = 0;
            histogramB[i] = 0;
        }



        // Calculate the scale factor
        float scale_factor = (float) (255.0 / (_image.getWidth() * _image.getHeight()));

        for (int i = 0; i < histogramR.length; i++) {
            sumr += _histogram.get(0)[i];
            int valr = (int) (sumr * scale_factor);
            if (valr > 255) {
                histogramR[i] = 255;
            } else {
                histogramR[i] = valr;
            }

            sumg += _histogram.get(1)[i];
            int valg = (int) (sumg * scale_factor);
            if (valg > 255) {
                histogramG[i] = 255;
            } else {
                histogramG[i] = valg;
            }

            sumb += _histogram.get(2)[i];
            int valb = (int) (sumb * scale_factor);
            if (valb > 255) {
                histogramB[i] = 255;
            } else {
                histogramB[i] = valb;
            }
        }

        imageHis.add(histogramR);
        imageHis.add(histogramG);
        imageHis.add(histogramB);

        return imageHis;
    }

    public static ArrayList<int[]> imageHistogram(BufferedImage _image) {
        // Create the lookup table
        ArrayList<int[]> _histogram = new ArrayList();

        // Fill the lookup table
        int histogramR[] = new int[256];
        int histogramG[] = new int[256];
        int histogramB[] = new int[256];

        for (int i = 0; i < histogramR.length; i++) {
            histogramR[i] = 0;
            histogramG[i] = 0;
            histogramB[i] = 0;

        }

        for (int i = 0; i < _image.getWidth(); i++) {
            for (int j = 0; j < _image.getHeight(); j++) {
                int r = new Color(_image.getRGB(i, j)).getRed();
                int g = new Color(_image.getRGB(i, j)).getGreen();
                int b = new Color(_image.getRGB(i, j)).getBlue();

                // Increase the values of colors
                histogramR[r]++;
                histogramG[g]++;
                histogramB[b]++;

            }
        }

        _histogram.add(histogramR);
        _histogram.add(histogramG);
        _histogram.add(histogramB);

        return _histogram;
    }

    ///////////////////////////////////////////////////////////////////////////////
    // --------------------------------not ok!!! ------------------------------
    public static BufferedImage threshold(BufferedImage _image) {
        int r, p;
        int threshold = 130;//otsuTreshold(_image);
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
                p = colorToRGB(alpha, p, p, p);
                imageOutput.setRGB(i, j, p);

            }
        }

        return imageOutput;

    }
// --------------------------------not ok!!! ------------------------------

//    public static int otsuTreshold(BufferedImage _image) {
//        ArrayList<int[]> _histogram = histogram(_image);
//        ???     
//         
//        
//        
//        
////        int total = _image.getWidth() * _image.getHeight();
////        float sum = 0;
////        for (int i = 0; i < 256; i++) {
////            sum += i * _histogram[i];
////        }
////        float sum_bg = 0;
////        int wight_bg = 0, wight_fg = 0;
////
////        float varMax = 0;
////        int threshold = 0;
////
////        for (int i = 0; i < 256; i++) {
////            wight_bg += _histogram[i];
////            if (wight_bg == 0) {
////                continue;
////            }
////            wight_fg = total - wight_bg;
////
////            if (wight_fg == 0) {
////                break;
////            }
////
////            sum_bg += (float) (i * _histogram[i]);
////            float mean_bg = sum_bg / wight_bg;
////            float mean_fg = (sum - sum_bg) / wight_fg;
////
////            float varBetween = (float) wight_bg * (float) wight_fg * (mean_bg - mean_fg) * (mean_bg - mean_fg);
////
////            if (varBetween > varMax) {
////                varMax = varBetween;
////                threshold = i;
////            }
////        }
////
////        return threshold;
//
//    }
/////////////////////////////////////////////////////////////////////////////////
//----------------------------------end Fillter-------------------------------------
//----------------------------------- helper method---------------------------------
// use to copy image 
    public static BufferedImage copyImg(BufferedImage _image) {
        ColorModel cm = _image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = _image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);

    }

    // Convert R, G, B, Alpha to standard 8 bit
    public static int colorToRGB(int alpha, int red, int green, int blue) {

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

    public static void printColor(BufferedImage _image) {
        BufferedImage output = copyImg(_image);
        int[][] p = Get_Pixels.getPixels(_image);

        for (int i = 0; i < _image.getWidth(); i++) {
            for (int j = 0; j < _image.getHeight(); j++) {

                System.out.print(p[i][j] + " ");
            }
            System.out.println("");
        }


    }
}
