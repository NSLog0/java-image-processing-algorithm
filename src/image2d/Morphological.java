/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image2d;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 *
 * @author pratchaya
 */
public class Morphological {

    public Morphological() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = toolkit.getScreenSize();
        String url = "images/wl5.jpg"; // this program have 4 images : wr.png ,sh.jpg , ca.jpg , icon.jpg ,r1,r2,r3,r4.jpg
        BufferedImage image = ImageProcessor.load_image(url);
        image = ImageProcessor.gaussianFillter(image, 3, 3, 0.7);
        image = ImageProcessor.grayscaleFillter(image);
        //image = ImageProcessor.balancingImg(image);
        image = ImageProcessor.threshold(image);
        


        JFrame frame = new JFrame("Display Image");
        ImagePanel iPanel = new ImagePanel(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add("Center", iPanel);
        //frame.setSize(65, 134);
        frame.setSize(image.getWidth() + 8, image.getHeight() + 34);
        frame.setLocation((int) image.getWidth() / dim.width + 455, (int) image.getHeight() / dim.height);
        frame.setVisible(true);

    }

//    public static BufferedImage erode(int kernel, BufferedImage _image) {
//        BufferedImage imageOutput = Unitys.copyImg(_image); // copy image
//        int pixel[][] = doPixels.getPixel(imageOutput); // get pixel
//        int kernelMask[][] = new int[kernel][kernel];
//        int kernelXY = Unitys.operation_Number("/", kernelMask.length, 2);
//        int temp = 0;
//        for (int i = 0; i < kernelMask.length; i++) {
//            for (int j = 0; j < kernelMask.length; j++) {
//                kernelMask[i][j] = 1;
//            }
//        }
//        for (int i = 0 + kernelXY; i < _image.getWidth() - kernelXY - 1; i++) {
//            for (int j = 0 + kernelXY; j < _image.getHeight() - kernelXY - 1; j++) {
//                int c = 0;
//                for (int k = -(kernelXY); k < kernelXY + 1; k++) {
//                    for (int l = -(kernelXY); l < kernelXY + 1; l++) {
//                    }
//                }
//            }
//        }
//        return imageOutput;
//    }

    public static void main(String[] args) {
        Morphological m = new Morphological();
    }
}