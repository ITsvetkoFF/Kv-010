package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by Olya on 10/26/14.
 * Refactoring by Vadym on 08/25/15.
 */
public class ResourcesPage extends AnyPage implements IResourcesPage, IAnyPage, IMapPage {

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

    /**
     * This method creates new resource on the website.
     *
     * @param sTitle       The name of the new resource; may not be null.
     * @param sAlias       The alias of the new resource; may not be null.
     * @param sBody        The description of the new resource; may not be null.
     * @param sPlaceToSave Place to save.
     */
    public void createResource(String sTitle, String sAlias, String sBody, String sPlaceToSave) {
        driver.findElement(RESOURCE_BUTTON).click();
        driver.findElement(ADD_NEW_RESOURCE_BUTTON).click();
        driver.findElement(RESOURCE_TITLE).sendKeys(sTitle);
        driver.findElement(RESOURCE_ALIAS).sendKeys(sAlias);
        driver.findElement(RESOURCE_BODY).sendKeys(sBody);
        Select select = new Select(driver.findElement(RESOURCE_SAVE_PLACE));
        select.selectByVisibleText(sPlaceToSave);
        driver.findElement(SEND_BUTTON).click();
        driver.findElement(LOGO).click();
    }

    /**
     * Method searches where the resources are located.
     *
     * @param resource name of the resource.
     * @return text where resource is located (top menu or in Resources).
     */
    public String existResource(String resource) {
        String value = "";
        driver.findElement(LOGO).click();
        List<WebElement> resources1 = driver.findElements(HEADER_RESOURCES);
        for (WebElement listElement : resources1) {
            String searchText = listElement.getText();
            if (searchText.equalsIgnoreCase(resource)) {
                value = "У верхньому меню";
            }
        }
        driver.findElement(RESOURCE_BUTTON).click();
        List<WebElement> resources2 = driver.findElements(LIST_RESOURCES);
        for (WebElement listElement : resources2) {
            String searchText = listElement.getText();
            if (searchText.equalsIgnoreCase(resource)) {
                value = "В розділі \"Ресурси\"";
            }
        }
        driver.findElement(LOGO).click();
        return value;
    }

    /**
     * This method allows you to edit the header of the resource.
     *
     * @param sTitle     The name of the resource; may not be null.
     * @param sTextToAdd The new resource title; may not be null.
     */
    public void editResourceHeader(String sTitle, String sTextToAdd) {
        List<WebElement> resources = driver.findElements(HEADER_RESOURCES);
        for (WebElement listElement : resources) {
            String searchText = listElement.getText();
            if (searchText.equalsIgnoreCase(sTitle)) {
                listElement.findElement(EDIT_HEADER_RESOURCES).click();
                driver.findElement(RESOURCE_TITLE).sendKeys(sTextToAdd);
                driver.findElement(SEND_BUTTON).click();
                break;
            }
        }
        driver.findElement(LOGO).click();
    }

    /**
     * This method allows you to edit the list (drop-down menu) of the resources.
     *
     * @param sTitle     The name of the resource; may not be null.
     * @param sTextToAdd The new resource title; may not be null.
     */
    public void editResourceList(String sTitle, String sTextToAdd) {
        driver.findElement(RESOURCE_BUTTON).click();
        List<WebElement> resources = driver.findElements(LIST_RESOURCES);
        for (WebElement listElement : resources) {
            if (listElement.getText().equals(sTitle)) {
                listElement.findElement(EDIT_LIST_RESOURCES).click();
                driver.findElement(RESOURCE_TITLE).sendKeys(sTextToAdd);
                driver.findElement(SEND_BUTTON).click();
                break;
            }
        }
        driver.findElement(LOGO).click();
    }

    /**
     * This method allows you to edit the resource depends where it located (top menu or in Resources).
     *
     * @param sTitle     The name of the resource; may not be null.
     * @param sTextToAdd The resource title; may not be null.
     */
    public void editResource(String sTitle, String sTextToAdd) {
        String place = existResource(sTitle);
        if (place.equals("В розділі \"Ресурси\"")) {
            editResourceList(sTitle, sTextToAdd);
        } else if (place.equals("У верхньому меню")) {
            editResourceHeader(sTitle, sTextToAdd);
        }
    }

    /**
     * This method allows you to delete resource from header.
     *
     * @param deleteTitle Which resource need to delete.
     */
    public void deleteResourceFromHeader(String deleteTitle) {
        driver.findElement(LOGO).click();
        List<WebElement> resources = driver.findElements(HEADER_RESOURCES);
        for (WebElement listElement : resources) {
            String searchText = listElement.getText();
            if (searchText.equalsIgnoreCase(deleteTitle)) {
                listElement.findElement(DELETE_HEADER_RESOURCES).click();
                driver.findElement(SUBMIT_DELITING).click();
                break;
            }
        }
        driver.findElement(LOGO).click();

    }

    /**
     * This method allows you to delete resource from list (drop-down menu).
     *
     * @param deleteTitle Which resource need to delete.
     */
    public void deleteResourceFromList(String deleteTitle) {
        driver.findElement(LOGO).click();
        driver.findElement(RESOURCE_BUTTON).click();
        List<WebElement> resources = driver.findElements(LIST_RESOURCES);
        for (WebElement listElement : resources) {
            if (listElement.getText().equals(deleteTitle)) {
                listElement.findElement(DELETE_LIST_RESOURCES).click();
                driver.findElement(SUBMIT_DELITING).click();
                break;
            }
        }
    }

    /**
     * This method find where resource is located and choose correct next method for this operation
     *
     * @param deleteTitle Which resource need to delete.
     */
    public void deleteResource(String deleteTitle) {
        String place = existResource(deleteTitle);
        if (place.equals("В розділі \"Ресурси\"")) {
            deleteResourceFromList(deleteTitle);
        } else if (place.equals("У верхньому меню")) {
            deleteResourceFromHeader(deleteTitle);
        }
    }

}
