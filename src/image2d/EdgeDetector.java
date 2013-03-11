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
public class EdgeDetector {

    public static BufferedImage EdgeSobel(BufferedImage _image) {
        // get kernel horizontal
        int horizontal[][] = EdgeOperator.edgeHorizontal();
        // get kernel vertical
        int vertical[][] = EdgeOperator.edgeVertical();
        // get result
        return EdgeOperator.apply(_image, horizontal, vertical);

    }
}
