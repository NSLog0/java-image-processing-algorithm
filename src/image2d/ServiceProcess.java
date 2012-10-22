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
public class ServiceProcess {

    public static BufferedImage thresholdComplete(BufferedImage _image) {
        BufferedImage image = ImageProcessor.gaussianFillter(_image, 3, 3, 0.7);
        image = ImageProcessor.grayscaleFillter(image);
        //image = ImageProcessor.balancingImg(image);
        image = ImageProcessor.threshold(image);
        return image;
    }
    
    
//    
//    public static  BufferedImage sobelEdgeComplete(BufferedImage _image){
//        
//        
//        return ;
//    }
}
