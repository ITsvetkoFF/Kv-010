package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Constant;
import utility.ExcelUtils;

import java.net.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Olya on 10/26/14.
 * Refactoring by Vadym on 08/25/15.
 */
public class ResourcesPage extends AnyPage implements IResourcesPage{
    public static final By RESOURCE_BUTTON = By.linkText("РЕСУРСИ");
    public static final By ADD_NEW_RESOURCE_BUTTON = By.linkText("ДОДАТИ НОВИЙ РЕСУРС");
    public static final By RESOURCE_TITLE = By.name("Title");
    public static final By RESOURCE_ALIAS = By.name("Alias");
    public static final By RESOURCE_BODY = By.cssSelector("div[id^='taTextElement']");
    public static final By RESOURCE_SAVE_PLACE = By.name("IsResource");
    public static final By SEND_BUTTON = By.cssSelector(".b-form__button.editor_button");
    public static final By LOGO = By.className("b-header__logo");
    public static final By HEADER_RESOURCES = By.cssSelector(".b-menu__button.ng-scope");
    public static final By LIST_RESOURCES = By.cssSelector("#b-header__resources li");
    public static final By EDIT_HEADER_RESOURCES = By.cssSelector(".fa.fa-pencil.fa-xs.ng-scope");
    public static final By EDIT_LIST_RESOURCES = By.cssSelector(".fa.fa-pencil.ng-scope");
    public static final By DELETE_HEADER_RESOURCES = By.cssSelector(".fa.fa-trash.fa-xs.ng-scope");
    public static final By SUBMIT_DELITING = By.cssSelector(".btn.btn-warning.ng-binding");
    public static final By DELETE_LIST_RESOURCES = By.cssSelector(".fa.fa-trash.ng-scope");
    private WebDriver driver;

    public ResourcesPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void createResource(String sTitle, String sAlias, String sBody, String sPlaceToSave) throws Exception{

        driver.findElement(RESOURCE_BUTTON).click();
        driver.findElement(ADD_NEW_RESOURCE_BUTTON).click();
        driver.findElement(RESOURCE_TITLE).sendKeys(sTitle);
        driver.findElement(RESOURCE_ALIAS).sendKeys(sAlias);
        driver.findElement(RESOURCE_BODY).sendKeys(sBody);
        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(driver.findElement(RESOURCE_SAVE_PLACE));
        select.selectByVisibleText(sPlaceToSave);
        driver.findElement(SEND_BUTTON).click();
        driver.findElement(LOGO).click();

    }

    public String existResource(String resource) throws  Exception{
        String value = null;
        driver.findElement(LOGO).click();
        //try {
        //WebDriverWait wait = new WebDriverWait(driver, 3);
        //WebElement elements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".b-menu__button.ng-scope")));
        List<WebElement> resources1 = driver.findElements(HEADER_RESOURCES);
        for (WebElement listElement : resources1){
            String searchText = listElement.getText();
            if (searchText.equals(resource.toUpperCase())){
                value = "У верхньому меню";
            }
        }
        //}
        //catch (Exception e) {};
        driver.findElement(RESOURCE_BUTTON).click();
        List<WebElement> resources2 = driver.findElements(LIST_RESOURCES);
        for (WebElement listElement : resources2){
            //System.out.println(listElement.getText());
            if (listElement.getText().equals(resource)){
                value = "В розділі \"Ресурси\"";
            }
        }
        driver.findElement(LOGO).click();
        return value;
    }

    public void editResourceHeader(String sTitle, String sTextToAdd) throws Exception{

        List<WebElement> resources = driver.findElements(HEADER_RESOURCES);
        for (WebElement listElement : resources){
            String searchText = listElement.getText();
            if (searchText.equals(sTitle.toUpperCase())){
                listElement.findElement(EDIT_HEADER_RESOURCES).click();
                driver.findElement(RESOURCE_TITLE).sendKeys(sTextToAdd);
                driver.findElement(SEND_BUTTON).click();
                break;
            }
        }
        driver.findElement(LOGO).click();
    }

    public void editResourceList(String sTitle, String sTextToAdd) throws  Exception{

        driver.findElement(RESOURCE_BUTTON).click();
        List<WebElement> resources = driver.findElements(LIST_RESOURCES);
        for (WebElement listElement : resources){
            if (listElement.getText().equals(sTitle)){
                listElement.findElement(EDIT_LIST_RESOURCES).click();
                driver.findElement(RESOURCE_TITLE).sendKeys(sTextToAdd);
                driver.findElement(SEND_BUTTON).click();
                break;
            }
        }
        driver.findElement(LOGO).click();
    }

    public void editResource(String sTitle, String sTextToAdd) throws  Exception{
        String place = existResource(sTitle);
        if (place.equals("В розділі \"Ресурси\"")){
            editResourceList(sTitle, sTextToAdd);
        }
        else if (place.equals("У верхньому меню")){
            editResourceHeader(sTitle, sTextToAdd);
        }
    }

    public void deleteResourceFromHeader(String deleteTitle) throws Exception{

        driver.findElement(LOGO).click();
        List<WebElement> resources = driver.findElements(HEADER_RESOURCES);
        for (WebElement listElement : resources){
            String searchText = listElement.getText();
            if (searchText.equals(deleteTitle.toUpperCase())){
                listElement.findElement(DELETE_HEADER_RESOURCES).click();
                //driver.findElement(By.partialLinkText("Видалити ресурс")).click();
                driver.findElement(SUBMIT_DELITING).click();
                break;
            }
        }
        driver.findElement(LOGO).click();

    }

    public void deleteResourceFromList(String deleteTitle) throws Exception{

        driver.findElement(LOGO).click();
        driver.findElement(RESOURCE_BUTTON).click();
        List<WebElement> resources = driver.findElements(LIST_RESOURCES);
        for (WebElement listElement : resources){
            if (listElement.getText().equals(deleteTitle)){
                listElement.findElement(DELETE_LIST_RESOURCES).click();
                //driver.findElement(By.partialLinkText("Видалити ресурс")).click();
                driver.findElement(SUBMIT_DELITING).click();
                break;
            }
        }
    }

    public void deleteResource(String deleteTitle) throws  Exception{
        String place = existResource(deleteTitle);
        if (place.equals("В розділі \"Ресурси\"")){
            deleteResourceFromList(deleteTitle);
        }
        else if (place.equals("У верхньому меню")){
            deleteResourceFromHeader(deleteTitle);
        }
    }

}
