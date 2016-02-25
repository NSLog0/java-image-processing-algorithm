# Image Processing with java
##About this project
This code I was developed on 3-4 years ago, When I study on junior in University. My purpose to use in Senior project, So now I don't work about image processing field anymore, I decided to open for everyone who interested in image processing to lean form my code. 

This project develop on pure java, basic concept about image processing and basic mathematical formula 


#What's inside the project
###Image2D.java 
Main class, show example to use function

###Gaussian.java
The algorithm for bluring image, I use Gaussian distribution method
#####Link
*  https://en.wikipedia.org/wiki/Gaussian_blur
*  http://www.pixelstech.net/article/1353768112-Gaussian-Blur-Algorithm
*  http://blog.ivank.net/fastest-gaussian-blur.html

###Grayscale.java
The algorithm for convert image to gray color, In this file I use Luminosity method, However I can read other method and example at below link
#####Link
* https://en.wikipedia.org/wiki/Grayscale
* http://developer.bostjan-cigan.com/java-color-image-to-grayscale-conversion-algorithm/
* http://www.tannerhelland.com/3643/grayscale-image-algorithm-vb6/
* http://www.johndcook.com/blog/2009/08/24/algorithms-convert-color-grayscale/

###Histogram.java
#####understand about histogram 
* http://www.tutorialspoint.com/dip/histograms_introduction.htm
* http://www.tutorialspoint.com/dip/histogram_equalization.htm

#####Math formula
* https://en.wikipedia.org/wiki/Histogram

###Threshold.java
The algorithm to convert image to binary color , In this file I use Otsu's method,
However you must have understand about histogram first cause by threshold algorithm base on histogram
#####understand about Threshold 
* https://en.wikipedia.org/wiki/Thresholding_(image_processing)

#####Math formula for Otsu's method
* http://www.ancient-asia-journal.com/articles/10.5334/aa.06113/
* https://en.wikipedia.org/wiki/Otsu%27s_method

###EdgeOperator.java
The algorithm to find edge of image , I use Canny Edge algorithm 
#####Understand about  Canny Edge algorithm
* https://en.wikipedia.org/wiki/Canny_edge_detector

#####Definetion and example 
I follow by this link to implement canny edge and use convolution method to apply value to image
* http://dasl.mem.drexel.edu/alumni/bGreen/www.pages.drexel.edu/_weg22/can_tut.html

####Definetion on OpenCv website
* http://docs.opencv.org/2.4/doc/tutorials/imgproc/imgtrans/canny_detector/canny_detector.html

###AutoBalance.java
#####link
* https://en.wikipedia.org/wiki/Color_balance

##Remove or Add image area (Morphological)
#####Concept 
* https://www.cs.auckland.ac.nz/courses/compsci773s1c/lectures/ImageProcessing-html/topic4.htm

###Erosion.java
#####link
* http://homepages.inf.ed.ac.uk/rbf/HIPR2/erode.htm

###Dilation.java
#####link
* http://homepages.inf.ed.ac.uk/rbf/HIPR2/dilate.htm

###See also Morphological
#####Closing
* https://en.wikipedia.org/wiki/Closing_(morphology)

#####Opening
* https://en.wikipedia.org/wiki/Opening_(morphology)

##Read pixel color value 
###RGB.java
If you get value of pixle from RGB.java to access RGB value on Dicimal digit, You must use bitwise operators to shitf bit. R >> 16 , G >> 8 and B, Then add maksk by (& 0xFF), See example in below link or file this project 
#####link 
* http://stackoverflow.com/questions/22391353/get-color-of-each-pixel-of-an-image-using-bufferedimages
* http://stackoverflow.com/questions/6524196/java-get-pixel-array-from-image

#Convoluion 
##To apply vaule to image, You must have understand Convoluion method
* https://en.wikipedia.org/wiki/Convolution
* http://mathworld.wolfram.com/Convolution.html
* http://www.songho.ca/dsp/convolution/convolution.html

## License

This project is licensed under the MIT license. See the [LICENSE](LICENSE) file for more info.
