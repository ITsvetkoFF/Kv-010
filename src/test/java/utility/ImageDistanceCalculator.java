package utility;

import javax.imageio.ImageIO;
import javax.media.jai.iterator.RandomIter;
import javax.media.jai.iterator.RandomIterFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by ykadytc on 28.10.2014.
 * Refactoring by Valery on 20.08.2015.
 * Based on source code publiced at http://www.lac.inpe.br/JIPCookbook/6050-howto-compareimages.jsp
 * by Rafael Santos.
 * This class uses a very simple, naive similarity algorithm to compare 2 images.
  */
public class ImageDistanceCalculator {
    private static final double similarityThreshold = 500.0;
    private static final int baseSize = 300;

    /**
     * This method determines whether or not the same image.
     *
     * @param image1URLString is the url of first picture.
     * @param image2URLString is the url of second picture.
     * @return true if images are similar.
     * @throws IOException
     */
    public static boolean isImagesSimilar(String image1URLString, String image2URLString) throws IOException {
        URL image1URL = new URL(image1URLString);
        URL image2URL = new URL(image2URLString);
        BufferedImage image1 = ImageIO.read(image1URL);
        BufferedImage image2 = ImageIO.read(image2URL);
        double dist = ImageDistanceCalculator.imageDistance(image1, image2);
        return dist < similarityThreshold;
    }

    /**
     * This method determines distance between two images.
     *
     * @param image1 is the first image.
     * @param image2 is the second image.
     * @return distance between two images.
     */
    public static double imageDistance ( Image image1, Image image2 ) {
        BufferedImage img1 = toBufferedImage(image1);
        BufferedImage img2 = toBufferedImage(image2);
        Color[][] signature = calcSignature(rescale(img1));
        Color[][] sigOther = calcSignature(rescale(img2));
        double dist = 0;
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                int r1 = signature[x][y].getRed();
                int g1 = signature[x][y].getGreen();
                int b1 = signature[x][y].getBlue();
                int r2 = sigOther[x][y].getRed();
                int g2 = sigOther[x][y].getGreen();
                int b2 = sigOther[x][y].getBlue();
                double tempDist = Math.sqrt((r1 - r2) * (r1 - r2) + (g1 - g2) * (g1 - g2) + (b1 - b2) * (b1 - b2));
                dist += tempDist;
            }
        }
        return dist;
    }

    /**
     * This method of image buffers.
     *
     * @param image is a picture that needs to be buffered.
     * @return buffered image.
     */
    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {return (BufferedImage)image;}
        image = new ImageIcon(image).getImage();
        boolean hasAlpha = hasAlpha(image);
        BufferedImage bImage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            if (hasAlpha == true) {transparency = Transparency.BITMASK;}
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bImage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            e.printStackTrace();
        }
        if (bImage == null) {
            int type = BufferedImage.TYPE_INT_RGB;
            if (hasAlpha == true) {type = BufferedImage.TYPE_INT_ARGB;}
            bImage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
        Graphics g = bImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bImage;
    }

    /**
     * Returns whether or not alpha (component for the specified pixel, scaled from 0 to 255) is supported in this ColorModel.
     *
     * @param image is a picture in which we must learn there is in alpha or no.
     * @return true if the alpha has in picture.
     */
    public static boolean hasAlpha(Image image) {
        if (image instanceof BufferedImage) {
            return ((BufferedImage)image).getColorModel().hasAlpha();
        }
        PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pg.getColorModel().hasAlpha();
    }

    /**
     * This method calculates and returns signature vectors for the input image.
     *
     * @param rImage the input image for calculation signature of the vector.
     * @return signature vector.
     */
    private static Color[][] calcSignature(RenderedImage rImage){
        // Get memory for the signature.
        Color[][] sig = new Color[5][5];
        // For each of the 25 signature values average the pixels around it.
        // Note that the coordinate of the central pixel is in proportions.
        float[] prop = new float[] {1f / 10f, 3f / 10f, 5f / 10f, 7f / 10f, 9f / 10f};
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                sig[x][y] = averageAround(rImage, prop[x], prop[y]);
            }
        }
        return sig;
    }

    /**
     * This method averages the pixel values around a central point and return the
     * average as an instance of Color. The point coordinates are proportional to
     * the image.
     *
     * @param rImage is the viewing image.
     * @param px x coordinate of the point.
     * @param py y coordinate of the point.
     * @return the average as an instance of Color.
     */
    private static Color averageAround(RenderedImage rImage, double px, double py){
        // Get an iterator for the image.
        RandomIter iterator = RandomIterFactory.create(rImage, null);
        // Get memory for a pixel and for the accumulator.
        double[] pixel = new double[3];
        double[] accum = new double[3];
        // The size of the sampling area.
        int sampleSize = 15;
        int numPixels = 0;
        // Sample the pixels.
        for (double x = px * baseSize - sampleSize; x < px * baseSize + sampleSize; x++){
            for (double y = py * baseSize - sampleSize; y < py * baseSize + sampleSize; y++){
                iterator.getPixel((int) x, (int) y, pixel);
                accum[0] += pixel[0];
                accum[1] += pixel[1];
                accum[2] += pixel[2];
                numPixels++;
            }
        }
        // Average the accumulated values.
        accum[0] /= numPixels;
        accum[1] /= numPixels;
        accum[2] /= numPixels;
        return new Color((int) accum[0], (int) accum[1], (int) accum[2]);
    }

    /**
     * This method rescales an image to 300,300 pixels
     * using the JAI scale operator.
     *
     * @param bImage is image for conversion.
     * @return the transformed image.
     */
    private static BufferedImage rescale(BufferedImage bImage) {
        BufferedImage scaledImage = new BufferedImage(baseSize, baseSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(bImage, 0, 0, baseSize, baseSize, null);
        graphics2D.dispose();
        return scaledImage;
    }
}
