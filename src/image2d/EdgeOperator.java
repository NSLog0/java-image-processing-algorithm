/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image2d;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author pratchaya
 */
public class EdgeOperator {

    // ---------------------------- sobel --------------------------------------
    // ** this template for sobel **
    public double[][] edgeHorizontal() {

        double sobel[][] = {
            {-1, -2, -1},
            {0, 0, 0},
            {1, 2, 1}
        };

        return sobel;
    }

    public double[][] edgeVertical() {
        double sobel[][] = {
            {-1, 0, 1},
            {-2, 0, 2},
            {-1, 0, 1}
        };

        return sobel;
    }
    // ----------------------------- end sobel ---------------------------------

    public BufferedImage sobelOperation(BufferedImage _image, double horizon[][], double vertical[][]) {
        BufferedImage imageOutput = Unitys.copyImage(_image);     // Set initial BufferedImage

        int kernelXY = horizon.length / 2;
        // calculate image
        for (int i = 0; i < imageOutput.getWidth(); i++) {
            for (int j = 0; j < imageOutput.getHeight(); j++) {
                int r = 0, g = 0, b = 0; // store RGB
                int horiz = 0, verti = 0;
                //  int p = RGB.getRGBExtended(_image, i, j);
                // horizontal
                for (int k = -(kernelXY); k < kernelXY + 1; k++) {
                    for (int l = -(kernelXY); l < kernelXY + 1; l++) {
                        int p = RGB.doGetRGB(_image, i + k, j + l);
                        // calculate a RGB by chip bit

                        r += ((p >> 16) & 0xff) * horizon[k + kernelXY][l + kernelXY];
                        g += ((p >> 8) & 0xff) * horizon[k + kernelXY][l + kernelXY];
                        b += (p & 0xff) * horizon[k + kernelXY][l + kernelXY];

                    } //end k
                }//end j
                horiz += ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);



                // vertical
                for (int k = -(kernelXY); k < kernelXY + 1; k++) {
                    for (int l = -(kernelXY); l < kernelXY + 1; l++) {
                        int p = RGB.doGetRGB(_image, i + k, j + l);
                        // calculate a RGB by chip bit

                        r += ((p >> 16) & 0xff) * vertical[k + kernelXY][l + kernelXY];
                        g += ((p >> 8) & 0xff) * vertical[k + kernelXY][l + kernelXY];
                        b += (p & 0xff) * vertical[k + kernelXY][l + kernelXY];

                    } //end k
                }//end j
                verti += ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);

                // add x-coordinate,y-coordinate form (diff) wiht sqrt(x.diff^2)+sqrt(y.diff^2)+
                double rgb = Math.sqrt(Math.pow(horiz, 2.0)) + Math.sqrt(Math.pow(verti, 2.0));
                // set color 0-255
                if (rgb > 255) {
                    rgb = 255;
                }
                if (rgb < 0) {
                    rgb = 0;
                }
                Color c = new Color((int) rgb, (int) rgb, (int) rgb);
                //set RGB revert to image
                imageOutput.setRGB(i, j, (int) c.getRGB());
            }// end i
        }  //end j

        return imageOutput;
    }
}
