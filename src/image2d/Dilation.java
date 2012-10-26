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
public class Dilation {

    private static int maxPixel(BufferedImage _image, int _w, int _h, int r) {
        int maxR = 0;
        int maxG = 0;
        int maxB = 0;
        int radiusPow = r * r;
        int _r = r/2;
        for (int i = -_r; i < _r; i++) {
            for (int j = -_r; j < _r; j++) {
                if (i * i + j * j < radiusPow) {
                    int c = RGB.getRGBW(_image, _w + i, _h + j);
                    maxR = Math.max(maxR, (c >> 16) & 0xFF);
                    maxG = Math.max(maxG, (c >> 8) & 0xFF);
                    maxB = Math.max(maxB, c & 0xFF);
                } // end if
            } // end j
        } // end i
        return (maxR << 16) | (maxG << 8) | maxB;
    }

    public static BufferedImage apply(BufferedImage _image, int _radius) {

        int width = _image.getWidth();
        int height = _image.getHeight();
        int radius = _radius;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image.setRGB(i, j, maxPixel(_image, i, j, radius));
            }
        }

        return image;
    }
}
