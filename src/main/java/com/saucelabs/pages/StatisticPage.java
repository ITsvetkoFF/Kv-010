package com.saucelabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    private WebDriver driver;
    private String tempString;
    private int number;
    private WebDriverWait wait;

    public StatisticPage(WebDriver driver){
        this.driver = driver;
        wait  = new WebDriverWait(driver, 2);
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
        waitSecond(3);
        driver.findElement(LIKE_PROBLEM).click();
        waitSecond(1);
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
        wait.until(ExpectedConditions.elementToBeClickable(FIELD_OF_FIRST_SEVERITY_PROBLEM));
        driver.findElement(FIELD_OF_FIRST_SEVERITY_PROBLEM).click();
        driver.findElement(ADD_FIVE_SEVERITY).click();
        driver.findElement(BUTTON_SAVE_CHANGES).click();
        waitSecond(1);
        driver.findElement(BUTTON_STATISTICS).click();
    }

    /**
     *
     * @return number like of first pop problem.
     */
    @Override
    public int getLikesNumberFirstPopProblem() {
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
        wait.until(ExpectedConditions.elementToBeClickable(SEVERITYS_NUMBER));
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
     *
     * @return numbers comments of first and second discussed problem.
     */
    @Override
    public int getCommentNumberFromFirstDiscussedProblem(){
        wait.until(ExpectedConditions.elementToBeClickable(FIRST_COMMENTS_NUMBER));
        tempString = driver.findElement(FIRST_COMMENTS_NUMBER).getText();
        return Integer.parseInt(tempString);
    }

    /**
     * This method pauses execution of code.
     *
     * @param sec number of seconds
     */
    public void waitSecond(int sec){
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
