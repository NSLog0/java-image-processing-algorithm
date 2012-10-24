/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package image2d;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author pratchaya
 */
public class FilterSequence extends Filter {

    ArrayList<Filter> seq = new ArrayList<Filter>();
    int index = 0;

    public FilterSequence() {
        // do nothing
    }

    @Override
    public BufferedImage apply(BufferedImage image) {
        runningFlag = true;

        for (index = 0; index < seq.size() && cancelFlag == false; index++) {
            image = seq.get(index).apply(image);
        }

        cancelFlag = false;
        runningFlag = false;

        return image;
    }

    public void append(Filter filter) {
        seq.add(filter);
    }

    /**
     * re-write for value correctness
     */
    @Override
    public int getProgress() {
        return (100 * index + seq.get(index).getProgress()) / seq.size();
    }
}
