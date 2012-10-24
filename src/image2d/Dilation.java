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
public class Dilation extends Filter {

    int radius;

    public Dilation(int radius) {
        this.radius = radius;
    }

    private int maxAround(BufferedImage image, int row, int col) {
        int maxR = 0;
        int maxG = 0;
        int maxB = 0;
        int radius2 = radius * radius;
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                if (i * i + j * j <= radius2) {
                    int c = new Unitys().getRGBExtended(image, col + i, row + j);
                    maxR = Math.max(maxR, (c >> 16) & 0xFF);
                    maxG = Math.max(maxG, (c >> 8) & 0xFF);
                    maxB = Math.max(maxB, c & 0xFF);
                }
            }
        }
        return (maxR << 16) | (maxG << 8) | maxB;
    }

    @Override
    public BufferedImage apply(BufferedImage image) {

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for (int i = 0; i < width && cancelFlag == false; i++) {
            progress = 100 * i / width;
            for (int j = 0; j < height && cancelFlag == false; j++) {
                img.setRGB(i, j, maxAround(image, i, j));
            }
        }

        return img;
    }
}
