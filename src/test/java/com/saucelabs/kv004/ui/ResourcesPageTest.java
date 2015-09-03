package com.saucelabs.kv004.ui;

import com.saucelabs.pages.ResourcesPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.saucelabs.utility.Constant;
import com.saucelabs.utility.ExcelUtils;

import java.util.concurrent.TimeUnit;


/**
 * @Author  Olya on 10/31/14.
 * @Edited by Mariya on 02/09/15.
 */
public class ResourcesPageTest {
    static WebDriver driver = new FirefoxDriver();
    static ResourcesPage resourcesPage = new ResourcesPage(driver);

    /**
     * Creating static method beforeTest() that opens FireFox with waiting by 15 sec and login to the site as Admin.
     * @throws Exception
     */
    @BeforeSuite
    public static void beforeTest() throws Exception {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);  //Waiting 15 sec for browser opening
        driver.get(Constant.URLlocal); //Open page by URL (localhost:8090)
        resourcesPage.logIn("admin@.com", "admin"); // Authentication as Administrator with login "admin@.com and password "admin".
    }

    /**
     *
     * @return take parameters from exel table in /resources/TestData_os.xlsx on Sheet1.
     * @throws Exception
     */
    @DataProvider(name = "testData", parallel = false)
    public static Object[][] data() throws Exception {
        return ExcelUtils.getTableArray(Constant.Path_TestData + Constant.File_TestDataLocal, "Sheet1");

    }

    /**
     * Method createResource for creating resource and additing them to the resource list.
     * @param ResourceTitle - Title of new resource.
     * @param ResourceAlias - To indicate that a named resource is also known under another specified name.
     * @param ResourceBody  - Description of the resource.
     * @param PlaceToSave   - Section where the new resource would be save.
     * @param TextToAdd     - Not used in this method, add text to the end of resource title.
     * @throws Exception
     */
    @Test(dataProvider = "testData")
    public void createResource( String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception {

        resourcesPage.createResource(ResourceTitle, ResourceAlias, ResourceBody, PlaceToSave);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle), PlaceToSave);
    }

    /**
     * Method editResource - edit resources title with adding text to the end of the name.
     * @throws Exception
     */
    @Test(dataProvider = "testData", dependsOnMethods = {"createResource"})
    public void editResource( String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception {

        resourcesPage.editResource(ResourceTitle, TextToAdd);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle + TextToAdd), PlaceToSave);
    }

    /**
     * Method in which we delete resources.
     * @throws Exception
     */
    @Test(dataProvider = "testData", dependsOnMethods = {"editResource"})
    public void deleteResource( String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception {
        resourcesPage.deleteResource(ResourceTitle + TextToAdd);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle + TextToAdd), "");
    }

    /**
     * Method afterTest() -  logout in the end of testing.
     * @throws Exception
     */
    @AfterSuite
    public static void afterTest() throws Exception {
        resourcesPage.logOut();
        driver.close();
    }
}
