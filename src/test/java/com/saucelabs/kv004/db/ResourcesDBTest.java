package com.saucelabs.kv004.db;

import com.saucelabs.kv004.dao.ResourcesDAO;
import com.saucelabs.kv004.entities.Resources;
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
    ResourcesDAO resoursesDAO = new ResourcesDAO();
    Resources actualResource = null;

    @DataProvider(name = "resourceTestDB", parallel = false)
    public static Object[][] data() throws Exception {
        return ExcelUtils.getTableArray(Constant.Path_TestData + Constant.File_ResourceTestDB, "Sheet1");
    }

    @Test(dataProvider = "resourceTestDB", groups = {"ResourcesDBTest"})
    public void checkAddedResourceDBTest(String UserName, String Password, String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(Constant.URLlocal);
        resourcesPage.logIn(Constant.Username, Constant.Password);
        resourcesPage.createResource(ResourceTitle, ResourceAlias, ResourceBody, PlaceToSave);
        actualResource = resoursesDAO.findResourceByTitle(ResourceTitle);
        Assert.assertEquals(actualResource.getTitle(), ResourceTitle);
        Assert.assertEquals(actualResource.getAlias(), ResourceAlias);
        Assert.assertTrue(actualResource.getContent().contains(ResourceBody));
        //Assert.assertEquals(actualResource.isIsResource(), convertStringIsResourceToInteger(PlaceToSave));

    }

    @Test(dataProvider = "resourceTestDB", dependsOnMethods = {"checkAddedResourceDBTest"}, groups = {"ResourcesDBTest"})
    public void checkEditedResourceDBTest(String UserName, String Password, String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception {
        resourcesPage.editResource(ResourceTitle, TextToAdd);
        String newResourceTitle = ResourceTitle + TextToAdd;
        Assert.assertEquals(actualResource = resoursesDAO.findResourceByTitle(newResourceTitle), newResourceTitle);
    }

    @Test(dataProvider = "resourceTestDB", dependsOnMethods = {"checkEditedResourceDBTest"})
    public void deleteResource(String UserName, String Password, String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception {
        String newResourceTitle = ResourceTitle + TextToAdd;
        Assert.assertEquals(resoursesDAO.findResourceByTitle(newResourceTitle), null);
    }

    /**
     * Method resolves where the resource is located by String parametr and return Boolean represantation of it.
     * true - in resources menu
     * false - in main menu
     * @param resourceLocation string name of resource location.
     * @return Integer code where resource is located (top menu or in Resources).
     */
    public boolean convertStringIsResourceToInteger(String resourceLocation) {
        String isResource = "В розділі \"Ресурси\"";
        if (resourceLocation.equals(isResource)) {
            return true;
        }
        return false;
    }
}
