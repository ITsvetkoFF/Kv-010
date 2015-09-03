package com.saucelabs.kv004.db;

import com.saucelabs.kv004.dao.ResourcesDAO;
import com.saucelabs.kv004.entities.Resources;
import com.saucelabs.pages.AnyPage;
import com.saucelabs.pages.ResourcesPage;
import com.saucelabs.utility.Constant;
import com.saucelabs.utility.ExcelUtils;
import com.saucelabs.utility.SingletonWebDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

/**
 * Created by odeortc on 01.09.2015.
 */
public class ResourcesDBTest {

    WebDriver driver = SingletonWebDriver.getInstance();
    ResourcesPage resourcesPage = new ResourcesPage(driver);
    AnyPage anyPage = new AnyPage(driver);
    ResourcesDAO resoursesDAO = new ResourcesDAO();
    Resources actualResource = null;
    String newResource = "newResource";
    String placeToSave = "В розділі \"Ресурси\"";
    String addedText = "aaaaaaaaaaa";



    @Test(groups = {"ResourcesDBTest"})
    public void checkAddedResourceDBTest() throws Exception {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(Constant.URLlocal);
        anyPage.logOut();
        driver.navigate().refresh();
        resourcesPage.logIn(Constant.Username, Constant.Password);
        resourcesPage.createResource(newResource, newResource, newResource, placeToSave);
        actualResource = resoursesDAO.findResourceByTitle(newResource);
        Assert.assertEquals(actualResource.getTitle(), newResource);
        Assert.assertEquals(actualResource.getAlias(), newResource);
        Assert.assertTrue(actualResource.getContent().contains(newResource));
        //Assert.assertEquals(actualResource.isIsResource(), convertStringIsResourceToInteger(placeToSave));

    }

    @Test(sequential = true, dependsOnMethods = {"checkAddedResourceDBTest"}, groups = {"ResourcesDBTest"})
    public void checkEditedResourceDBTest() throws Exception {
        resourcesPage.editResource(newResource, addedText);
        String editedResource = newResource + addedText;
        Assert.assertEquals(resoursesDAO.findResourceByTitle(editedResource).getTitle(), editedResource);
    }

    @Test(sequential = true, dependsOnMethods = {"checkEditedResourceDBTest"})
    public void deleteResource() throws Exception {
        String editedResource = newResource + addedText;
        resourcesPage.deleteResource(editedResource);
        Assert.assertEquals(resoursesDAO.findResourceByTitle(editedResource), null);
    }

    /**
     * Method resolves where the resource is located by String parametr and return Boolean represantation of it.
     * true - in resources menu
     * false - in main menu
     * @param resourceLocation string name of resource location.
     * @return Integer code where resource is located (top menu or in Resources).
     */
    public boolean convertStringIsResourceToBoolean(String resourceLocation) {
        String isResource = "В розділі \"Ресурси\"";
        if (resourceLocation.equals(isResource)) {
            return true;
        }
        return false;
    }
}
