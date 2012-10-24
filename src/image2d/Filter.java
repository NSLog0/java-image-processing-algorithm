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
public abstract  class Filter {
    
      /**
         * Apply an filter to an image. The result image will be allocated.
         * @param image Original image
         * @return Processed image
         */
        public abstract BufferedImage apply(BufferedImage image);
         
        protected boolean cancelFlag = false;
        
        protected boolean runningFlag = false;
        
        protected int progress = 0;

        public final void cancel() {
                if (runningFlag == true) {
                        cancelFlag = true;
                }
        }

        public int getProgress() {
                return progress;
        }

        public final boolean isRunning() {
                return runningFlag;
        }
    
}
