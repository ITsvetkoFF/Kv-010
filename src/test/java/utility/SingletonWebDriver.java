package utility;

import com.saucelabs.AnyPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

/**
 * Created by acidroed on 21.08.2015.
 */
//public class SingletonWebDriver {
//    private WebDriver driver;
//
//    // Private constructor prevents instantiation from other classes
//    private SingletonWebDriver() {
//        driver = new FirefoxDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
////        driver.get(Constant.URLlocal);
////        AnyPage anypage = new AnyPage(driver);
////        anypage.getFirstResourceTitleFromMenu();
//    }
//
//    /**
//     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
//     * or the first access to SingletonHolder.INSTANCE, not before.
//     */
//    private static class SingletonHolder {
//        private static final SingletonWebDriver INSTANCE = new SingletonWebDriver();
//    }
//
//    public static WebDriver getDriver() {
//        return SingletonHolder.INSTANCE.driver;
//    }
//
//}


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


//public class SingletonWebDriver{
//    private static WebDriver instance;
//    private SingletonWebDriver () {}
//
//    public static WebDriver getInstance() {
//        if (instance == null) {
//            instance = new FirefoxDriver();
//            instance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        }
//
//        return instance;
//    }
//}