/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image2d;

//import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Image2D {

    public Image2D() {
        String url = "test.jpg";
        BufferedImage image  = ImageProcessor.load_image(url);
        image = ImageProcessor.gaussianFillter(image);
        JFrame frame = new JFrame("Display Image");
        ImagePanel iPanel = new ImagePanel(image);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add("Center", iPanel);
        frame.setSize(image.getWidth(), image.getHeight());
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        Image2D i = new Image2D();
    }
}
