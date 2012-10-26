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

    public static BufferedImage findEdgeSobel(BufferedImage _image) {
        // get kernel horizontal
        double horizontal[][] = EdgeOperator.edgeHorizontal();
        // get kernel vertical
        double vertical[][] = EdgeOperator.edgeVertical();
        // get result
        return EdgeOperator.sobelOperation(_image, horizontal, vertical);

    }
}
