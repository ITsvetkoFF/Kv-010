package com.saucelabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yermek on 20.10.2014.
 * Refactoring by Vadym on 08/25/15.
 */
public class AdminPage extends AnyPage implements IAdminPage, IAnyPage, IMapPage {

    public static final By LIST_OF_PROBLEMS_UNDER_MODERATION = By.className("b-new-items__item");
    public static final By PROBLEM_UNDER_MODERATION_TITLE = By.xpath("//editproblemtitle/span");
    public static final By APPROVE_PROBLEM_TICK_MARK = By.xpath("..//ul/li");
    public static final By DELETE_PROBLEM_BUTTON = By.xpath("//button[contains(@ng-click,'deleteProblemFromDb()')]");
    public static final By APPROVE_DELETE_PROBLEM_BUTTON = By.className("btn-warning");
    public static final By DELETE_PHOTO_CROSS = By.xpath("//i[@class='fa fa-close show_photo_delete_lavel_true']");
    private WebDriver driver;

    public AdminPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * @return List with problems names that need to be moderated.
     */
    public List<String> getAllProblemForModerationNames() {
        List<WebElement> problems = getAllProblemsForModeration();
        List<String> results = new ArrayList<String>(problems.size());
        for (WebElement problem : problems) {
            results.add(problem.getText());
        }
        return results;
    }

    /**
     * @return List with problems that need to be confirmed.
     */
    public List<WebElement> getAllProblemsForModeration() {
        return driver.findElements(LIST_OF_PROBLEMS_UNDER_MODERATION);
    }

    /**
     * @return String name problem under moderation.
     */
    public String getProblemTitle() {
        return driver.findElement(PROBLEM_UNDER_MODERATION_TITLE).getText();
    }

    /**
     * Click on the button problem for validate it.
     *
     * @param problem Which problem need validate, approve.
     */
    public void clickProblemCheck(WebElement problem) {
        problem.findElement(APPROVE_PROBLEM_TICK_MARK).click();
    }

    /**
     * Click on the button problem for validate it.
     *
     * @param problemName Which problem need validate, approve by name (String).
     */
    public void approveProblem(String problemName) {
        List<WebElement> problems = getAllProblemsForModeration();
        for (WebElement problem : problems) {
            if (problemName.equals(problem.getText())) {
                clickProblemCheck(problem);
            }
        }
    }

    /**
     * This problem is under moderation? You can check it with this method.
     *
     * @param problemName String name problem.
     * @return true if this problem is under moderation and return false if not.
     */
    public boolean checkProblemIsUnderModeration(String problemName) {
        List<WebElement> problems = getAllProblemsForModeration();
        for (WebElement problem : problems) {
            if (problemName.equals(problem.getText())) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method presses on the delete problem button.
     */
    public void pressDeleteProblemButton() {
        WebDriverWait wait = new WebDriverWait(driver, 300);
        driver.findElement(DELETE_PROBLEM_BUTTON).click();
        WebElement approve = wait.until(ExpectedConditions.visibilityOfElementLocated(APPROVE_DELETE_PROBLEM_BUTTON));
        approve.click();
    }

    /**
     * This method deletes all the photos.
     */
    public void deleteAllPhotos(double latitude, double longitude) {
        clickAtProblemByCoordinateVisible(latitude, longitude);
        int imagesAmount = driver.findElements(DELETE_PHOTO_CROSS).size();
        for (int i = 0; i < imagesAmount; i++) {
            driver.findElement(DELETE_PHOTO_CROSS).click();
        }
    }

}