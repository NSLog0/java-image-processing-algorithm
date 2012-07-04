/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image2d;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Iterator;
/**
 *
 * @author pratchaya
 */
public class Interval {

    public static void counter(BufferedImage _image) {
        BufferedImage output = ImageProcessor.copyImg(_image);
        int[][] p = Get_Pixels.getPixels(_image);
        HashMap<Integer, Integer> r = new HashMap<Integer, Integer>(); // store red
        HashMap<Integer, Integer> g = new HashMap<Integer, Integer>(); // store green
        HashMap<Integer, Integer> b = new HashMap<Integer, Integer>(); // store blue

        for (int i = 0; i < _image.getWidth(); i++) {
            for (int j = 0; j < _image.getHeight(); j++) {

                // store count value into hashmap 
                // RGB.red, RGB.green, RGB.blue is use to shift color to get RGB
                if (r.get(RGB.red(p, j, j)) == null) {
                    r.put(RGB.red(p, j, j), 1);
                } else {
                    r.put(RGB.red(p, j, j), r.get(RGB.red(p, j, j)) + 1);
                }


                if (g.get(RGB.green(p, j, j)) == null) {
                    g.put(RGB.green(p, j, j), 1);
                } else {
                    g.put(RGB.green(p, j, j), g.get(RGB.green(p, j, j)) + 1);
                }


                if (b.get(RGB.blue(p, j, j)) == null) {
                    b.put(RGB.blue(p, j, j), 1);
                } else {
                    b.put(RGB.blue(p, j, j), b.get(RGB.blue(p, j, j)) + 1);
                }


            } // end j
        } //end i

System.out.println("---------R----------");
        Iterator<Integer> printR = r.keySet().iterator();
        while (printR.hasNext()) {
            int key = printR.next();
            int value = r.get(key);
            System.out.println("Key : " + key + "   Value: " + value);
        }

        System.out.println("----------G---------");
          Iterator<Integer> printG = g.keySet().iterator();
        while (printG.hasNext()) {
            int key = printG.next();
            int value = g.get(key);
            System.out.println("Key : " + key + "   Value: " + value);
        }
          System.out.println("----------B---------");
          Iterator<Integer> printB = b.keySet().iterator();
        while (printB.hasNext()) {
            int key = printB.next();
            int value = b.get(key);
            System.out.println("Key : " + key + "   Value: " + value);
        }
    }
}
