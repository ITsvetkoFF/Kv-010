package com.saucelabs.Tests.OldTests;

import com.saucelabs.AnyPage;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by Roma on 18.11.2014.
 */
public class zSikuliSample {

    static WebDriver driver = new FirefoxDriver();
    static AnyPage anyPage = new AnyPage(driver);

//    @Test
//    public void sikuliSample() throws IOException {
//
///*----------------------------------------Selenium code block---------------------------------------------------------*/
//
//        WebDriver driver = new FirefoxDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        driver.get("http://localhost:8090/#/map");
//        //driver.get("http://176.36.11.25/#/map");
//
//        driver.findElement(By.xpath("//button[@class='navbar-brand b-menu__button']")).click();
//        List<WebElement> tabs = driver.findElements(By.xpath("//li[@class='ng-isolate-scope']/a/tab-heading/i"));
//        tabs.get(1).click();
//        tabs.get(1).click();
//
///*------------------------------------------Sikuli code block---------------------------------------------------------*/
//
//        DesktopKeyboard keyboard = new DesktopKeyboard();
//        Mouse mouse = new DesktopMouse();
//        Canvas canvas = new DesktopCanvas();
//        ScreenRegion screenRegion = new DesktopScreenRegion();
//
//        Target triangle = new ImageTarget(new File(".\\resources\\images\\Triangle.jpg"));
//        triangle.setMinScore(0.8);
//
//        Target dropZone = new ImageTarget(new File(".\\resources\\images\\Drop Zone.jpg"));
//        dropZone.setMinScore(0.6);
//
//        BufferedImage bigKoala = ImageIO.read(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg"));
//        BufferedImage smallKoala = bigKoala.getSubimage(320, 120, 600, 600);
//
//        BufferedImage resizeBigKoala = resize(bigKoala, 85, 64);
//        Target resizeBigKoalaTarget = new ImageTarget(resizeBigKoala);
//        resizeBigKoalaTarget.setMinScore(0.7);
//
//        BufferedImage bigDesert = ImageIO.read(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg"));
//        BufferedImage resizeBigDesert = resize(bigDesert, 85, 64);
//        Target resizeBigDesertTarget = new ImageTarget(resizeBigDesert);
//        resizeBigDesertTarget.setMinScore(0.7);
//
//        Target cross = new ImageTarget(new File(".\\resources\\images\\Cross.jpg"));
//        cross.setMinScore(0.8);
//
//        keyboard.keyDown(KeyEvent.VK_WINDOWS);
//        keyboard.keyDown(KeyEvent.VK_E);
//        keyboard.keyUp(KeyEvent.VK_E);
//        keyboard.keyUp(KeyEvent.VK_WINDOWS);
//        try {
//            Thread.sleep(700);
//        } catch (Exception e) {
//        }
//        keyboard.keyDown(KeyEvent.VK_WINDOWS);
//        keyboard.keyDown(KeyEvent.VK_UP);
//        keyboard.keyUp(KeyEvent.VK_UP);
//        keyboard.keyUp(KeyEvent.VK_WINDOWS);
//        try {
//            Thread.sleep(150);
//        } catch (Exception e) {
//        }
//        keyboard.keyDown(KeyEvent.VK_WINDOWS);
//        keyboard.keyDown(KeyEvent.VK_LEFT);
//        keyboard.keyUp(KeyEvent.VK_LEFT);
//        keyboard.keyUp(KeyEvent.VK_WINDOWS);
//        try {
//            Thread.sleep(150);
//        } catch (Exception e) {
//        }
//
//        List<ScreenRegion> screenRegionList = screenRegion.findAll(triangle);
//        for(ScreenRegion screen : screenRegionList) {
//            canvas.addBox(screen);
//        }
//        canvas.display(1);
//        canvas.clear();
//
//        int x = 0;
//        int y = 0;
//        int width = 0;
//        int height = 0;
//        for (ScreenRegion screen : screenRegionList) {
//            if (screen.getCenter().getX() > x) {
//                x = screen.getCenter().getX();
//                y = screen.getCenter().getY();
//                width = screen.getBounds().width;
//                height = screen.getBounds().height;
//            }
//        }
//        screenRegion = new DesktopScreenRegion(x, y, width, height);
//
//        canvas.addCircle(screenRegion.getCenter());
//        canvas.addLabel(screenRegion.getCenter(), "We get this!");
//        canvas.display(1);
//        canvas.clear();
//        mouse.click(screenRegion.getCenter().getRelativeScreenLocation(100, 0));
//        keyboard.paste("C:\\Users\\Public\\Pictures\\Sample Pictures");
//        keyboard.keyDown(KeyEvent.VK_ENTER);
//        keyboard.keyUp(KeyEvent.VK_ENTER);
//
//        ScreenRegion dropZoneScreenRegion = new DesktopScreenRegion().wait(dropZone, 10);
//        canvas.addBox(dropZoneScreenRegion);
//        canvas.addLabel(dropZoneScreenRegion, "We found Drop Zone!");
//        canvas.display(1);
//        canvas.clear();
//
//        canvas.addImage(screenRegionList.get(0).getCenter(), smallKoala);
//        canvas.addLabel(screenRegionList.get(0), "           We want choose this nice Koala!");
//        canvas.display(1);
//        canvas.clear();
//
//        ScreenRegion koalaScreenRegion = new DesktopScreenRegion().wait(resizeBigKoalaTarget, 10);
//        mouse.drag(koalaScreenRegion.getCenter());
//        mouse.drop(dropZoneScreenRegion.getCenter());
//
//        ScreenRegion desertScreenRegion = new DesktopScreenRegion().wait(resizeBigDesertTarget, 10);
//        mouse.drag(desertScreenRegion.getCenter());
//        mouse.drop(dropZoneScreenRegion.getCenter());
//
//        ScreenRegion crossScreenRegion = new DesktopScreenRegion().wait(cross, 10);
//        mouse.click(crossScreenRegion.getCenter());
//    }
//
//    public BufferedImage resize(BufferedImage img, int newW, int newH) {
//        int w = img.getWidth();
//        int h = img.getHeight();
//        BufferedImage dImg = new BufferedImage(newW, newH, img.getType());
//        Graphics2D g = dImg.createGraphics();
//        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
//        g.dispose();
//        return dImg;
//    }

    @BeforeTest
    public void setting() {
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().maximize();
        //driver.get("http://localhost:8090/#/map");
        driver.get("http://176.36.11.25/#/map");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void addProblem() throws IOException {
        anyPage.addProblemSymbiosis(50, 30, 18,
                "Коалам загрожує небезпека", "Загрози біорізноманіттю",
                "Через вирубку лісів, місцевість перетворюється на пустелю, " +
                "бідненьким коалам нічого їсти", "Заборонити вирубку лісів!",
        Arrays.asList("C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg",
                      "C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg"),
        Arrays.asList("Голодна коала", "Ось як вже подекуди виглядає ліс!"));
    }

    @AfterTest
    public void shutdown() {
        driver.quit();
    }
}
