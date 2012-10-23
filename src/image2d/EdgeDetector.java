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
public class EdgeDetector {

    public static BufferedImage findEdgeSobel(BufferedImage _image) {
        // get kernel horizontal
        double horizontal[][] = new EdgeOperator().edgeHorizontal();
        // get kernel vertical
        double vertical[][] = new EdgeOperator().edgeVertical();
        // copy image form original
        BufferedImage imageOutput = new Unitys().copyImg(_image);
         
        // get result
        return ImageProcessor.convolution(_image, horizontal, vertical);
    }
}
