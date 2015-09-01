package com.saucelabs.archive.OldTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.saucelabs.utility.ImageDistanceCalculator;

import java.io.IOException;

/**
 * Created by ykadytc on 28.10.2014.
 */
public class zImageDistanceTest {
    @Test
    public void getImagesAndCompare() throws IOException {
        String image1URL = "http://i.imgur.com/1X4tNs7.jpg";
        String image2URL = "http://i.imgur.com/zGSgYyt.jpg";

        Assert.assertTrue(ImageDistanceCalculator.isImagesSimilar(image1URL, image2URL));
    }
}
