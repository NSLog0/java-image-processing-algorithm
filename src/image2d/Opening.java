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
public class Opening {

    public static BufferedImage apply(BufferedImage _image, int r) {
        _image = Erosion.apply(_image, r);
        _image = Dilation.apply(_image, r);
        return _image;
    }
}
