/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image2d;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author pratchaya
 */
public class ImageReader {

    // get image form url to BufferedImage
    public static BufferedImage load_image(String url) {
        // init BufferedImage null
        BufferedImage image = null;
        try {
            // read file form BufferedImage
            image = ImageIO.read(new File(url));
            if (!image.equals(null)) {
             
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage() + " File does not exist because name or extension file is not correct."
                    + "\nPlease check your file again.");
        }
        // return result image when read url done!
        return image;
    }
}
//---------------------------------end--------------------------------------
/*
 * public static int[] balancing(BufferedImage _image) { int _histogram[] =
 * histogtam(_image); int _factor[] = new int[256]; _factor = new
 * Unitys().randArray(_factor, 0); long sum = 0; float scale = (float) (255.0 /
 * (_image.getWidth() * _image.getHeight()));
 *
 * for (int i = 0; i < _factor.length; i++) { sum += _histogram[i]; int value =
 * (int) (sum * scale); if (value > 255) { _factor[i] = 255; } else { _factor[i]
 * = value; } } return _factor;
 *
 * }
 *
 * public static BufferedImage balancingImg(BufferedImage _image) { int
 * new_Histogram[] = balancing(_image); BufferedImage output =
 * Unitys.copyImage(_image); int r, g, b, rgb; for (int i = 0; i <
 * _image.getWidth(); i++) { for (int j = 0; j < _image.getHeight(); j++) { int
 * p = RGB.getRGBExtended(_image, i, j); r = (p >> 16) & 0xff; g = (p >> 8) &
 * 0xff; b = (p & 0xff);
 *
 * r = new_Histogram[r]; g = new_Histogram[g]; b = new_Histogram[b];
 *
 * r = (r << 16); g = (g << 8); b = (b);
 *
 * rgb = r + g + b;
 *
 * output.setRGB(i, j, rgb); }
 *
 * }
 *
 * return output;
 *
 * }
 *
 *
 */
