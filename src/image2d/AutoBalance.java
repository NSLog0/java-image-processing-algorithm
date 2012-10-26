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
public class AutoBalance {

    public static int[] balancing(BufferedImage _image) {
        int _histogram[] =Histogram.histogtam(_image);
        int _factor[] = new int[256];
        _factor = new Unitys().randArray(_factor, 0);
        int sum = 0;
        float scale = (float) (255.0 / (_image.getWidth() * _image.getHeight()));

        for (int i = 0; i < _factor.length; i++) {
            sum += _histogram[i];
            int value =
                    (int) (sum * scale);
            if (value > 255) {
                _factor[i] = 255;
            } else {
                _factor[i] = value;
            }
        }
        return _factor;

    }

    public static BufferedImage apply(BufferedImage _image) {
        int new_Histogram[] = balancing(_image);
        BufferedImage output = new BufferedImage(_image.getWidth(), _image.getHeight(), BufferedImage.TYPE_3BYTE_BGR); 
        int r, g, b, rgb;
        for (int i = 0; i < _image.getWidth(); i++) {
            for (int j = 0; j < _image.getHeight(); j++) {
                int p = RGB.getRGBW(_image, i, j);
                r = (p >> 16) & 0xff;
                g = (p >> 8) & 0xff;
                b = (p & 0xff);

                r = new_Histogram[r];
                g = new_Histogram[g];
                b = new_Histogram[b];

                output.setRGB(i, j, (r << 16) | (g << 8) | (b));
            }

        }

        return output;

    }
}
