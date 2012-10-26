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
public class Threshold {

    public static BufferedImage apply(BufferedImage _image) {
        int _r, p, r, g, b;
        double threshold = otsuTreshold(_image);
        BufferedImage imageOutput = new BufferedImage(_image.getWidth(), _image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);   // Set initial BufferedImage
        for (int i = 0; i < _image.getWidth(); i++) {
            for (int j = 0; j < _image.getHeight(); j++) {

                // Get pixels
                r = RGB.getRGBW(_image, i, j);
                r = ((r >> 16) & 0xff);

                if (r > threshold) {
                    p = 255;
                } else {
                    p = 0;
                }
                p = (p << 16) | (p << 8) | (p);
                imageOutput.setRGB(i, j, p);

            }
        }

        return imageOutput;
    }

    public static int otsuTreshold(BufferedImage _image) {
        int _histogram[] = Histogram.histogtam(_image);

        int total = _image.getWidth() * _image.getHeight();
        float sum = 0;
        for (int i = 0; i < 256; i++) {
            sum += i * _histogram[i];
        }
        float sum_bg = 0;
        int wight_bg = 0, wight_fg = 0;

        float varMax = 0;
        int threshold = 0;

        for (int i = 0; i < 256; i++) {
            wight_bg += _histogram[i];
            if (wight_bg == 0) {
                continue;
            }
            wight_fg = total - wight_bg;

            if (wight_fg == 0) {
                break;
            }

            sum_bg += (float) (i * _histogram[i]);
            float mean_bg = sum_bg / wight_bg;
            float mean_fg = (sum - sum_bg) / wight_fg;
            float varBetween = (float) wight_bg * (float) wight_fg * (mean_bg - mean_fg) * (mean_bg - mean_fg);
            if (varBetween > varMax) {
                varMax = varBetween;
                threshold = i;
            }
        }
        return threshold;
    }
}
