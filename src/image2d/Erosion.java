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
public class Erosion extends Filter {

    int radius;

    public Erosion(int radius) {
        this.radius = radius;
    }

    private int minAround(BufferedImage image, int row, int col) {
        int minR = 255;
        int minG = 255;
        int minB = 255;
        int radius2 = radius * radius;
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                if (i * i + j * j <= radius2) {
                    int c = new Unitys().getRGBExtended(image, col + i, row + j);
                    minR = Math.min(minR, (c >> 16) & 0xFF);
                    minG = Math.min(minG, (c >> 8) & 0xFF);
                    minB = Math.min(minB,  c & 0xFF);
                }
            }
        }
        return (minR << 16) | (minG << 8) | minB;
    }

    @Override
    public BufferedImage apply(BufferedImage image) {

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for (int i = 0; i < width; i++) {
            progress = 100 * i / width;
            for (int j = 0; j < height && cancelFlag == false; j++) {
                img.setRGB(i, j, minAround(image, i, j));
            }
        }

        return img;
    }
}
