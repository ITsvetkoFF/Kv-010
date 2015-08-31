package utility;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

/**
 * Created by acidroed on 21.08.2015.
 */



public class SingletonWebDriver {
    private static volatile WebDriver instance;

    public static WebDriver getInstance() {
        WebDriver localInstance = instance;
        if (localInstance == null) {
            synchronized (WebDriver.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new FirefoxDriver();
                    instance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                }
            }
        }
        return localInstance;
    }
}

