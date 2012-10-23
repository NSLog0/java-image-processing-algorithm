/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image2d;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 *
 * @author pratchaya
 */
public class Unitys {

    //----------------------------------- helper method---------------------------------
// use to copy image 
    public BufferedImage copyImg(BufferedImage _image) {
        ColorModel cm = _image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = _image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);

    }

    //-------------------
    public int operation_Number(String operator, int operand1, int operand2) {
        int _num = 0;
        try {
            _num = (operator.equals("+")) ? operand1 + operand2
                    : (operator.equals("-")) ? operand1 - operand2 : (operator.equals("*")) ? operand1 * operand2
                    : (operator.equals("/") && operand2 != 0) ? operand1 / operand2 : operand1 / operand2;

        } catch (ArithmeticException e) {
            e.getMessage();
        }
        return _num;

    }

    //-------------- set value array2D --------------------------//
    public int[][] randArray(int arr[][], int val) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                // set value
                arr[i][j] = val;
            } // end j
        } // end i
        return arr;
    }

    public int[] randArray(int arr[], int val) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = val;
        }
        return arr;
    }
}
