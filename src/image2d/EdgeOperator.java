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
}
