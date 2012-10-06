/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image2d;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 *
 * @author pratchaya
 */
public class Unitys {

    //----------------------------------- helper method---------------------------------
// use to copy image 
    public static BufferedImage copyImg(BufferedImage _image) {
        ColorModel cm = _image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = _image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);

    }
}
