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
public class Erosion {

    private static int minPixel(BufferedImage _image, int _w, int _h, int r) {
        int minR = 255;
        int minG = 255;
        int minB = 255;
        int radiusPow = r * r;
        int _r = r/2;
        for (int i = -_r; i < _r; i++) {
            for (int j = -_r; j < _r; j++) {
                if (i * i + j * j < radiusPow) {
                    int c = RGB.getRGBW(_image, _w + i, _h + j);
                    minR = Math.min(minR, (c >> 16) & 0xFF);
                    minG = Math.min(minG, (c >> 8) & 0xFF);
                    minB = Math.min(minB, c & 0xFF);
                } // end if
            } // end j
        } // end i

        return (minR << 16) | (minG << 8) | minB;
    }

    public static BufferedImage apply(BufferedImage _image, int _radius) {

        int width = _image.getWidth();
        int height = _image.getHeight();
        int radius = _radius;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image.setRGB(i, j, minPixel(_image, i, j, radius));
            }
        }

        return image;
    }
}
