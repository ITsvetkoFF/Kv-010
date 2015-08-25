package com.saucelabs;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Roma on 23.10.2014.
 */
public class MapPage implements IMapPage {

    public static final By PROBLEM_TYPE = By.cssSelector(".problem label");
    public static final By ZOOM_OUT = By.xpath("//a[@title='Zoom out']");
    public static final By LEFT_SIDE_POINTER = By.xpath("//div[@class='b-left-side__pointer']");
    public static final By BY_PROBLEM_TYPE_WITH_LABEL_FOR = By.cssSelector(".problem label[for^='type']");
    public static final By PROBLEM_TYPE_STARTS_WITH_TYPE_BY_ID = By.xpath("//input[starts-with(@id, 'type')]");
    public static final By INPUT_FIELD_FOR_DATE = By.cssSelector(".datepicker .form-control");
    public static final By DATE_PICKER = By.cssSelector(".datepicker");
    public static final By CALENDAR_ICON = By.cssSelector(".fa-calendar");
    public static final By TODAY_BUTTON = By.cssSelector("span.btn-group>button.btn-info");
    public static final By CLEAR_BUTTON = By.cssSelector("button.btn-success");
    public static final By CLOSE_BUTTON = By.cssSelector("button.btn-danger");
    public static final By TOP_BUTTON_FOR_UPPER_PERIOD = By.className("ng-binding");
    public static final By MAP = By.id("map-content");
    public static final By NAV_BAR = By.className("container-fluid");
    public static final By ADD_PROBLEM_MENU = By.className("b-addProblem");

    private WebDriver driver;

    public MapPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void setView(double latitude, double longitude, int zoom) {
        JavascriptExecutor script = null;
        if (driver instanceof JavascriptExecutor)
            script = (JavascriptExecutor) driver;
        script.executeScript("var map = document.getElementById(\"map-content\");" +
                "angular.element(map).scope().$parent.$parent.$parent.geoJson._map.setView(["
                + latitude + "," + longitude + "]" + "," + zoom + ");");
    }

    /**
     * This method sets visible view. For begin method set view then find menu 'addProblem':
     * 1) menu 'addProblem' can be at right side if your screen is normal
     * 2) menu 'addProblem' can be at top side if you have a small size screen
     * After this operations we would found new center coordinate X, Y. Run angular script which uses our variables
     * @param latitude
     * @param longitude
     * @param zoom
     */
    @Override
    public void setVisibleView(double latitude, double longitude, int zoom) {
        int newCenterCoordinateX = 0;
        int newCenterCoordinateY = 0;
        int mapWidth = 0;
        int mapHeight = 0;
        int addProblemWidth = 0;
        int addProblemHeight = 0;

        JavascriptExecutor script = null;
        if (driver instanceof JavascriptExecutor)
            script = (JavascriptExecutor) driver;
        if (script != null) {
            script.executeScript(
                    "var map = document.getElementById(\"map-content\");" +
                    "var view = function() {angular.element(map).scope().$parent.$parent.$parent.geoJson" +
                            "._map.setView([" + latitude + "," + longitude + "]" + "," + zoom + ");};" +
                    "window.setTimeout(view, 0);"
            );
        }

        WebElement map = driver.findElement(MAP);
        int navBarHeight = driver.findElement(NAV_BAR).getSize().getHeight();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        System.out.println("Before Explicit Wait during set view");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 1);
        WebElement addProblem = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(ADD_PROBLEM_MENU));
        addProblemWidth = addProblem.getSize().getWidth();
        addProblemHeight = addProblem.getSize().getHeight();
        System.out.println("After Explicit Wait during set view");

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        mapWidth = map.getSize().getWidth();
        mapHeight = map.getSize().getHeight();

        if (mapWidth == addProblemWidth) {              // tall screen, menu 'addProblem' at the top
            newCenterCoordinateX = mapWidth / 2;
            newCenterCoordinateY = navBarHeight + 1 + addProblemHeight + 1 + (mapHeight - navBarHeight - addProblemHeight - 2) / 2;
        } else {                                        // wide screen, menu 'addProblem' at the right side
            newCenterCoordinateX = (mapWidth - addProblemWidth) / 2;
            newCenterCoordinateY = mapHeight / 2;
        }

        if (driver instanceof JavascriptExecutor)
            script = (JavascriptExecutor) driver;
        if (script != null) {
            script.executeScript(
                      "var map = document.getElementById(\"map-content\");"
                    + "var oldCenter = angular.element(map).scope().$parent.$parent.$parent.geoJson._map.getCenter();"
                    + "console.log(oldCenter);"
                    + "var newCenter = angular.element(map).scope().$parent.$parent.$parent"
                                      + ".geoJson._map.containerPointToLatLng([" + newCenterCoordinateX + ","
                                                                                 + newCenterCoordinateY + "]);"
                    + "console.log(newCenter);"
                    + "console.log([" + latitude + " + oldCenter['lat'] - newCenter['lat'],"
                                      + longitude + " + oldCenter['lng'] - newCenter['lng']]);"
                    + "angular.element(map).scope().$parent.$parent.$parent.geoJson" +
                                      "._map.setView([" + latitude + " + oldCenter['lat'] - newCenter['lat'],"
                                      + longitude + " + oldCenter['lng'] - newCenter['lng']]," + zoom + ");"
                    + "console.log(angular.element(map).scope().$parent.$parent.$parent"
                                      + ".geoJson._map.containerPointToLatLng([" + newCenterCoordinateX + ","
                                                                                 + newCenterCoordinateY + "]));");
        }
    }

    @Override
    public void clickAtPagesCenter() {
        int x;
        int y;

        WebElement map = driver.findElement(MAP);
        Dimension point = map.getSize();
        x = point.getWidth() / 2;
        y = point.getHeight() / 2;
        Actions builder = new Actions(driver);
        builder.moveToElement(map, x, y).clickAndHold().release().build().perform();
    }

    @Override
    public void clickAtVisibleMapCenter(int offset) {
        int x;
        int y;
        int mapWidth;
        int mapHeight;
        int addProblemWidth = 0;
        int addProblemHeight = 0;

        WebElement map = driver.findElement(MAP);
        int navBarHeight = driver.findElement(NAV_BAR).getSize().getHeight();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        System.out.println("Before Explicit Wait during click");
        try {
            WebElement addProblem = (new WebDriverWait(driver, 1))
                    .until(ExpectedConditions.presenceOfElementLocated(ADD_PROBLEM_MENU));
            addProblemWidth = addProblem.getSize().getWidth();
            addProblemHeight = addProblem.getSize().getHeight();
        } catch (Exception e) {
        }
        System.out.println("After Explicit Wait during click");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        mapWidth = map.getSize().getWidth();
        mapHeight = map.getSize().getHeight();
        if (mapWidth != addProblemWidth) {              // wide screen, addProblem at the left side
            x = (mapWidth - addProblemWidth) / 2;
            y = mapHeight / 2;
        } else {                                          // tall screen, addProblem at the top
            x = mapWidth / 2;
            y = navBarHeight + 1 + addProblemHeight + 1 + (mapHeight - navBarHeight - addProblemHeight - 2) / 2;
        }
        Actions builder = new Actions(driver);
        builder.moveToElement(map, x, y + offset).clickAndHold().release().build().perform();
    }

    @Override
    public void clickAtProblemByCoordinateVisible(double latitude, double longitude) {

        setVisibleView(latitude, longitude, 18);
        clickAtVisibleMapCenter(-10);
    }

    @Override
    public String getFilterTitle(int typeNumber) {

        List<WebElement> names = driver.findElements(PROBLEM_TYPE);

        return names.get(typeNumber).getAttribute("textContent");
    }

    @Override
    public void clickZoomOut() {
        WebElement zoomOut = driver.findElement(ZOOM_OUT);

        do {
            zoomOut.click();
        } while (!zoomOut.getAttribute("class").contains("disabled"));
    }

    @Override
    public void openFiltersBoard() {
        driver.findElement(LEFT_SIDE_POINTER).click();
    }

    @Override
    public void selectAllExceptOneFilter(int typeNumber) throws Exception {

        List<WebElement> filtersNames = driver.findElements(BY_PROBLEM_TYPE_WITH_LABEL_FOR);
        List<WebElement> filtersChecks = driver.findElements(PROBLEM_TYPE_STARTS_WITH_TYPE_BY_ID);
        String typeId = "";
//        JavascriptExecutor js = null;
//        if (driver instanceof JavascriptExecutor) {
//            js = (JavascriptExecutor) driver;
//        }

        for (int i = 0; i < filtersChecks.size(); i++) {
            typeId = filtersChecks.get(i).getAttribute("id");
            if (i != typeNumber - 1) {
                if (!"true".equals(filtersChecks.get(i).getAttribute("checked"))) filtersNames.get(i).click();
//                js.executeScript("document.getElementById('" + typeId + "').checked = true");
            } else {
                if ("true".equals(filtersChecks.get(i).getAttribute("checked"))) filtersNames.get(i).click();
//                js.executeScript("document.getElementById('" + typeId + "').checked = false");
            }
        }
    }

    @Override
    public void selectAllExceptOneFilter(String typeName) {

        List<WebElement> filtersNames = driver.findElements(BY_PROBLEM_TYPE_WITH_LABEL_FOR);
        List<WebElement> filtersChecks = driver.findElements(PROBLEM_TYPE_STARTS_WITH_TYPE_BY_ID);
        String typeId = "";
        for (int i = 0; i < filtersChecks.size(); i++) {
            typeId = filtersChecks.get(i).getAttribute("id");
            if (!typeName.equals(filtersNames.get(i).getText())) {
                if ("true".equals(filtersChecks.get(i).getAttribute("checked"))) filtersNames.get(i).click();
            } else {
                if (!"true".equals(filtersChecks.get(i).getAttribute("checked"))) filtersNames.get(i).click();
            }
        }
    }

    @Override
    public void selectOnlyOneFilter(int typeNumber) {

        List<WebElement> filtersNames = driver.findElements(BY_PROBLEM_TYPE_WITH_LABEL_FOR);
        List<WebElement> filtersChecks = driver.findElements(PROBLEM_TYPE_STARTS_WITH_TYPE_BY_ID);
        String typeId = "";
        for (int i = 0; i < filtersChecks.size(); i++) {
            typeId = filtersChecks.get(i).getAttribute("id");
            if (i != typeNumber - 1) {
                if ("true".equals(filtersChecks.get(i).getAttribute("checked"))) filtersNames.get(i).click();
            } else {
                if (!"true".equals(filtersChecks.get(i).getAttribute("checked"))) filtersNames.get(i).click();
            }
        }
    }

    @Override
    public void selectOnlyOneFilter(String typeName) {

        List<WebElement> filtersNames = driver.findElements(BY_PROBLEM_TYPE_WITH_LABEL_FOR);
        List<WebElement> filtersChecks = driver.findElements(PROBLEM_TYPE_STARTS_WITH_TYPE_BY_ID);
        String typeId = "";
        for (int i = 0; i < filtersChecks.size(); i++) {
            typeId = filtersChecks.get(i).getAttribute("id");
            if (!typeName.equals(filtersNames.get(i).getText())) {
                if ("true".equals(filtersChecks.get(i).getAttribute("checked"))) filtersNames.get(i).click();
            } else {
                if (!"true".equals(filtersChecks.get(i).getAttribute("checked"))) filtersNames.get(i).click();
            }
        }
    }

    @Override
    public void setAfterDate(String afterDate) {
        List<WebElement> dateFields = driver.findElements(INPUT_FIELD_FOR_DATE);
        WebElement dateField = dateFields.get(0);
        dateField.clear();
        dateField.sendKeys(afterDate);
    }

    @Override
    public void setBeforeDate(String beforeDate) {
        List<WebElement> dateFields = driver.findElements(INPUT_FIELD_FOR_DATE);
        WebElement dateField = dateFields.get(1);
        dateField.clear();
        dateField.sendKeys(beforeDate);
    }

    @Override
    public void datePickersButtons() {

        List<WebElement> datePickerButtons = driver.findElements(DATE_PICKER);

        for (WebElement datePickers : datePickerButtons) {
            WebElement buttonElement = datePickers.findElement(CALENDAR_ICON);

            buttonElement.click();
            WebElement todayButton = datePickers.findElement(TODAY_BUTTON);
            todayButton.click();

            buttonElement.click();
            WebElement clearButton = datePickers.findElement(CLEAR_BUTTON);
            clearButton.click();

            buttonElement.click();
            WebElement closeButton = datePickers.findElement(CLOSE_BUTTON);
            closeButton.click();
        }
    }

    @Override //not use
    public void selectDate(WebElement datePicker, String year, String month, String day) {

        StringBuilder yearXPath = new StringBuilder("");
        yearXPath.append(".//td/button/span[(text()='");
        yearXPath.append(year);
        yearXPath.append("')]");

        StringBuilder monthXPath = new StringBuilder("");
        monthXPath.append(".//td/button/span[(text()='");
        monthXPath.append(month);
        monthXPath.append("')]");

        StringBuilder dayXPath = new StringBuilder("");
        dayXPath.append(".//td/button/span[(text()='");
        dayXPath.append(day);
        dayXPath.append("')]/..");

        datePicker.findElement(By.xpath(yearXPath.toString())).click();
        datePicker.findElement(By.xpath(monthXPath.toString())).click();
        datePicker.findElement(By.xpath(dayXPath.toString())).click();
    }

    @Override //not use
    public void selectOneDayPeriod(String fullDate) {
        String[] splitDate;
        splitDate = fullDate.split("\\s+");

        List<WebElement> datePickers = driver.findElements(DATE_PICKER);

        for (WebElement datePicker : datePickers) {
            WebElement buttonElement = datePicker.findElement(CALENDAR_ICON);

            buttonElement.click();
            datePicker.findElement(TOP_BUTTON_FOR_UPPER_PERIOD).click();
            datePicker.findElement(TOP_BUTTON_FOR_UPPER_PERIOD).click();

            selectDate(datePicker, splitDate[0], splitDate[1], splitDate[2]);
        }
    }

    @Override
    public void setPosition() {    // not use
        JavascriptExecutor js = null;
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;
        }
        if (js != null) {
            js.executeScript("navigator.geolocation.getCurrentPosition = function(success) {" +
                    "success({coords: {latitude: 50.649460, longitude: 30.731506}}); }");
        }
    }


}

