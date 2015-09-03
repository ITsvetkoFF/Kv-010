package com.saucelabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by stako on 02.09.2015.
 */
public class StatisticPage implements IStatisticPage{

    private static final By BUTTON_STATISTICS = By.cssSelector("a[href=\"#/statistic\"]");
    private static final By LIKES_NUMBER = By.cssSelector("div[class=\"topProblems\"] div:nth-of-type(1) a:nth-of-type(1) p");
    private static final By FIELD_OF_FIRST_POP_PROBLEM = By.cssSelector("div[class=\"topProblems\"] div:nth-of-type(1) a:nth-of-type(1)");
    private static final By LIKE_PROBLEM = By.cssSelector("button.simple_like_img[ng-disabled=disableVoteButton]");
    private static final By SEVERITYS_NUMBER = By.cssSelector("div[class=\"topProblems\"] div:nth-of-type(2) a:nth-of-type(1) p");
    private static final By ADD_FIVE_SEVERITY = By.cssSelector("div[class=\"b-problem-deatiled-info__severity b-problem-notAdmin_false\"] span[class=\"ng-isolate-scope ng-pristine ng-valid\"] i:nth-of-type(5)");
    private static final By BUTTON_SAVE_CHANGES = By.cssSelector("button[class=\"btn btn-sm btn-warning\"]");
    private static final By TEXRAREA_FOR_COMMENT = By.cssSelector("div.form-group textarea[rows=\"4\"]");
    private static final By BUTTOM_ADD_COMMENT = By.cssSelector("a[ng-click=\"addComment(commentContent)\"]");
    private static final By FIRST_COMMENTS_NUMBER = By.cssSelector("div[class=\"topProblems\"] div:nth-of-type(3) a:nth-of-type(1) p");
    private static final By FIELD_OF_FIRST_SEVERITY_PROBLEM = By.cssSelector("div[class=\"topProblems\"] div:nth-of-type(2) a:nth-of-type(1)");
    private static final By TAB_ADD_COMMENT = By.cssSelector("li[class=\"b-problem-notAdmin_false ng-isolate-scope\"] a.ng-binding[ng-click=\"select()\"]");
    private static final By SECOND_COMMENTS_NUMBER = By.cssSelector("div[class=\"topProblems\"] div:nth-of-type(3) a:nth-of-type(2) p");
//    private static final By USER_PICTOGRAM = By.className("fa-user");
//    private static final By LOGOUT_LINK = By.className("fa-sign-out");
//    private static final By REGISTRATION_LINK = By.id("register-button");
//    private static final By FIRST_NAME_FIELD = By.id("first_name");
//    private static final By LAST_NAME_FIELD = By.id("last_name");
//    private static final By PASSWORD_REPEAT_FIELD = By.id("password_second");
//    private static final By REGISTRATION_SUBMIT_BUTTON = By.className("b-form__button");
//    private static final By CLOSE_CROSS_IN_ALERT_WINDOW = By.className("close");
//    private static final By LOGIN_LINK = By.cssSelector("li[ng-hide=\"isLoggedIn()\"]>a");
//    private static final By EMAIL_FIELD = By.name("email");
//    private static final By PASSWORD_FIELD = By.name("password");
//    private static final By LOGIN_BUTTON = By.id("login-button");

    private WebDriver driver;
    private String tempString;
    private String tempString2;
    private int number;

    public StatisticPage(WebDriver driver){
        this.driver = driver;
    }

    public void driverQuit(){
        driver.quit();
    }

    public void baseURL(){
        driver.get(IStatisticPage.baseStatisticUrl);
    }

    /**
     * This method does:
     * 1)Go to the page of first pop problem
     * 2)Add "like" to this problem
     * 3)Back to Statistic page
     */
    @Override
    public void likeFirstPopProblemAndBackToStatisticPage() {
        driver.findElement(FIELD_OF_FIRST_POP_PROBLEM).click();
        driver.findElement(LIKE_PROBLEM).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(BUTTON_STATISTICS).click();
    }

    /**
     * This method does:
     * 1)Go to the page of first severity problem
     * 2)Add severity to this problem
     * 3)Back to Statistic page
     */
    @Override
    public void upSeverityInFirstSeverityProblemAndBackToStatisticPage(){
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElement(FIELD_OF_FIRST_SEVERITY_PROBLEM).click();
        driver.findElement(ADD_FIVE_SEVERITY).click();
        driver.findElement(BUTTON_SAVE_CHANGES).click();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElement(BUTTON_STATISTICS).click();
    }

    /**
     *
     * @return number like of first pop problem.
     */
    @Override
    public int getLikesNumberFirstPopProblem() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tempString = driver.findElement(LIKES_NUMBER).getText();
        number = Integer.parseInt(tempString.substring(1));
        return number;
    }

    /**
     *
     * @return number severity of first severity problem.
     */
    @Override
    public int getSeverityNumberFirstProblem() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tempString = driver.findElement(SEVERITYS_NUMBER).getText();
        number = Integer.parseInt(tempString);
        return number;
    }

    /**
     * This method does:
     * 1)Go to the page of first pop problem
     * 2)Add comment to this problem
     * 3)Back to Statistic page
     *
     * @param problem is comment which will be add.
     */
    public void addCommentToFirstPopProblemAndBackToStatisticPage(String problem){
        driver.findElement(FIELD_OF_FIRST_POP_PROBLEM).click();
        driver.findElement(TAB_ADD_COMMENT).click();
        driver.findElement(TEXRAREA_FOR_COMMENT).sendKeys(problem);
        driver.findElement(BUTTOM_ADD_COMMENT).click();
        baseURL();
    }

    /**
     * This method does:
     * 1)Go to the page of first severity problem
     * 2)Add comment to this problem
     * 3)Back to Statistic page
     *
     * @param problem is comment which will be add.
     */
    public void addCommentToFirstSeverityProblemAndBackToStatisticPage(String problem){
        driver.findElement(FIELD_OF_FIRST_SEVERITY_PROBLEM).click();
        driver.findElement(TAB_ADD_COMMENT).click();
        driver.findElement(TEXRAREA_FOR_COMMENT).sendKeys(problem);
        driver.findElement(BUTTOM_ADD_COMMENT).click();
        baseURL();
    }

    /**
     *
     * @return numbers comments of first and second discussed problem.
     */
    @Override
    public int[] getFirstAndSecondCommentNumber(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tempString = driver.findElement(FIRST_COMMENTS_NUMBER).getText();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tempString2 = driver.findElement(SECOND_COMMENTS_NUMBER).getText();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new int[]{Integer.parseInt(tempString), Integer.parseInt(tempString2)};
    }

}
