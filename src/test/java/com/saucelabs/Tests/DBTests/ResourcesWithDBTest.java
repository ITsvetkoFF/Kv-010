package com.saucelabs.Tests.DBTests;

import com.saucelabs.ResourcesPage;
import com.saucelabs.Tests.DAO.ResourcesDAO;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.ExcelUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.sql.DriverManager.getConnection;

/**
 * Created by nklimotc on 11.11.2014.
 */
public class ResourcesWithDBTest extends SingleWebdriver{
    ResourcesPage resourcesPage;

    public static final String Path_TestData = ".\\resources\\";
    public static final String File_TestDataForResourceAddingToDB = "TestDataForResourceAddingToDB.xlsx";
    String resourcePlace;
    String value0 = "У верхньому меню";
    String value1 = "В розділі \"Ресурси\"";

    @DataProvider(name = "TestDataForResourceAddingToDB", parallel = false)
    public static Object[][] data() throws Exception{
        return ExcelUtils.getTableArray(Path_TestData + File_TestDataForResourceAddingToDB, "Sheet1");
    }

    @Test (sequential = true, dataProvider = "TestDataForResourceAddingToDB", singleThreaded = true, groups = {"resources"})
    public void addResourceToDB(String alias, String title, String content, String isResource, String addTextToTitle, String addTextToContent) throws Exception {
        checkDriver();
        driver.get("http://127.0.0.1:8090/#/map");
        this.resourcesPage = new ResourcesPage(driver);
        resourcesPage.logIn("admin@.com", "admin");

        ResourcesDAO dao = new ResourcesDAO();
        List<String> Aliases = new ArrayList<>();
        //Aliases = dao.getAllAliases();
        int resourceInDB = Integer.parseInt(isResource);
        dao.addResourceToDB(alias, title, content, resourceInDB);
        driver.navigate().refresh();
        if (resourceInDB == 0) {
            resourcePlace = value0; //"У верхньому меню";
        }
        else {
            resourcePlace = value1; //"В розділі \"Ресурси\"";
        }
            Assert.assertEquals(resourcesPage.existResource(title), resourcePlace);
        resourcesPage.logOut();
        }

    @Test (sequential = true, dataProvider = "TestDataForResourceAddingToDB", singleThreaded = true, dependsOnMethods = {"addResourceToDB"}, groups = {"resources"}) //dependsOnMethods = {"addResourceToDB"}
    public void editResource(String alias, String title, String content, String isResource, String addTextToTitle, String addTextToContent) throws Exception {
        ResourcesDAO dao = new ResourcesDAO();
        resourcesPage.logIn("admin@.com", "admin");

        String id = dao.getResourceIdByTitle(title);

        resourcesPage.editResource(title, addTextToTitle);


        driver.navigate().refresh();
        Assert.assertEquals(title+addTextToTitle, dao.getResourceTitleById(id));
        resourcesPage.logOut();
    }

    @Test (sequential = true, dataProvider = "TestDataForResourceAddingToDB", singleThreaded = true, dependsOnMethods = {"editResource"}, groups = {"resources"})
    public void deleteResourceFromDB(String alias, String title, String content, String isResource, String addTextToTitle, String addTextToContent) throws Exception {
        ResourcesDAO dao = new ResourcesDAO();
        resourcesPage.logIn("admin@.com", "admin");

        dao.deleteResourceFromDB(title+addTextToTitle);
        driver.navigate().refresh();
        Assert.assertEquals(resourcesPage.existResource(title+addTextToTitle), null);
        resourcesPage.logOut();
    }
}
