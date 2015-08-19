package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yermek on 20.10.2014.
 */
public class AdminPage extends AnyPage implements IAdminPage {
    public static final By LIST_OF_PROBLEMS_UNDER_MODERATION = By.className("b-new-items__item");
    public static final By PROBLEM_UNDER_MODERATION_TITLE = By.xpath("//editproblemtitle/span");
    public static final By APPROVE_PROBLEM_TICK_MARK = By.xpath("..//ul/li");
    public static final By DELETE_PROBLEM_BUTTON = By.xpath("//button[contains(@ng-click,'deleteProblemFromDb()')]");
    public static final By APPROVE_DELETE_PROBLEM_BUTTON = By.className("btn-warning");
    private WebDriver driver;

    public AdminPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public List<String> getAllProblemForModerationNames() {
        List<WebElement> problems = getAllProblemsForModeration();
        List<String> results = new ArrayList<String>(problems.size());
        for (WebElement problem: problems) {
            results.add(problem.getText());
        }
        return results;
    }

    public List<WebElement> getAllProblemsForModeration() {
        List<WebElement> results = driver.findElements(LIST_OF_PROBLEMS_UNDER_MODERATION);
        return results;
    }

    public String getProblemTitle() {
        return driver.findElement(PROBLEM_UNDER_MODERATION_TITLE).getText();
    }

    public void clickProblemCheck(WebElement problem) {
        problem.findElement(APPROVE_PROBLEM_TICK_MARK).click();
        return;
    }

    public void approveProblem(String problemName) {
        List<WebElement> problems = getAllProblemsForModeration();
        for (WebElement problem: problems) {
            if (problemName.equals(problem.getText())) {
                clickProblemCheck(problem);
            }
        }
    }

    public boolean checkProblemIsUnderModeration(String problemName) {
        List<WebElement> problems = getAllProblemsForModeration();
        for (WebElement problem: problems) {
            if (problemName.equals(problem.getText())) {
                return true;
            }
        }
        return false;
    }

    public void pressDeleteProblemButton() {
        WebDriverWait wait = new WebDriverWait(driver, 300);
        driver.findElement(DELETE_PROBLEM_BUTTON).click();
        WebElement approve = wait.until(ExpectedConditions.visibilityOfElementLocated(APPROVE_DELETE_PROBLEM_BUTTON));
        approve.click();
    }
}