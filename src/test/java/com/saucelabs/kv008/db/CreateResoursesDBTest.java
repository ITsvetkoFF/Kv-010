package com.saucelabs.kv008.db;


import com.saucelabs.kv008.dao.PageDao;
import com.saucelabs.kv008.entities.PageEntity;
import com.saucelabs.pages.ResourcesPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import  org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


/**
 * Created by a
 */
public class CreateResoursesDBTest{

    static WebDriver driver = new FirefoxDriver();
    static ResourcesPage resourcesPage = new ResourcesPage(driver);
    private PageEntity pageEntityTopFromDB;
    private PageEntity pageEntityTop = new PageEntity();


    private PageEntity pageEntityResFromDB;
    private PageEntity pageEntityRes = new PageEntity();


    private final PageDao pageDao = new PageDao();

    @BeforeSuite
    public  void beforeTest() throws Exception {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);  //Waiting 15 sec for browser opening
        driver.get("http://localhost:8888/"); //Open page by URL (localhost:8888)
        resourcesPage.logIn("aaa@aaa.aaa", "aaa"); // Authentication as Administrator.
        //stub 1 init
        pageEntityTop.setAlias("ddd");
        pageEntityTop.setTitle("ddd");
        pageEntityTop.setContent("ddd");
        pageEntityTop.setResource(false);
        //
        pageEntityRes.setAlias("sss");
        pageEntityRes.setContent("sss");
        pageEntityRes.setTitle("sss");
        pageEntityRes.setResource(true);


    }

    @Test
    public void createResource(){
        resourcesPage.createResource(pageEntityTop.getTitle(),pageEntityTop.getAlias(),
                pageEntityTop.getContent(),"У верхньому меню");
        pageEntityTopFromDB = pageDao.readByAlias(pageEntityTop.getAlias());
        resourcesPage.createResource(pageEntityRes.getTitle(),pageEntityRes.getAlias(),
                pageEntityRes.getContent(),"В розділі \"Ресурси\"");
        pageEntityResFromDB = pageDao.readByAlias(pageEntityRes.getAlias());
        System.out.println(pageEntityTop);
        System.out.println(pageEntityTopFromDB);
        System.out.println(pageEntityTop.equals(pageEntityTopFromDB));
        System.out.println(pageEntityRes);
        System.out.println(pageEntityResFromDB);
        System.out.println(pageEntityRes.equals(pageEntityResFromDB));
        Assert.assertTrue(pageEntityTop.equals(pageEntityTopFromDB));
        Assert.assertTrue(pageEntityRes.equals(pageEntityResFromDB));
    }


    @AfterSuite
    public  void afterTest() throws Exception {
        pageDao.delete(pageEntityTopFromDB);
        pageDao.delete(pageEntityResFromDB);
        resourcesPage.logOut();
        driver.close();
    }
}
