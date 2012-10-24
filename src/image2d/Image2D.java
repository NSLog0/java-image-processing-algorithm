/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image2d;

//import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Image2D {

    public Image2D(int x){
         Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = toolkit.getScreenSize();
        String url = "images/wl2.jpg"; // this program have 4 images : wr.png ,sh.jpg , ca.jpg , icon.jpg ,r1,r2,r3,r4.jpg
        BufferedImage image = ImageReader.load_image(url);
        image = new Guassian(0.7, 3).apply(image);
        image = new Grayscale().apply(image);
        image = new Threshold().apply(image);
      //  image = new Opening(3).apply(image);
        image = new EdgeDetector().findEdgeSobel(image);
        
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
    public Image2D() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = toolkit.getScreenSize();
        String url = "images/wl2.jpg"; // this program have 4 images : wr.png ,sh.jpg , ca.jpg , icon.jpg ,r1,r2,r3,r4.jpg
        BufferedImage image = ImageReader.load_image(url);
        image = new Guassian(0.7, 3).apply(image);
        image = new Grayscale().apply(image);
        image = new Threshold().apply(image);
        image = new Opening(3).apply(image);
        image = new EdgeDetector().findEdgeSobel(image);
        
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

    public Image2D(String url) {
    }

    public static void main(String[] args) {
        Image2D i = new Image2D();
        Image2D x = new Image2D(3);
    }
}
