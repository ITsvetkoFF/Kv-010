package com.saucelabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by onikistc on 21.10.2014.
 * Refactoring by Vadym on 08/26/15.
 */
public class ProblemPage extends AnyPage{

    private static final By PROBLEM_ICON_SRC = By.xpath("//img[@class='b-problem-deatiled-info-title__icon']");
    private static final By PROBLEM_TITLE = By.xpath("//h1");
    private static final By EDIT_PROBLEM_TITLE = By.xpath("//editproblemtitle/input");
    private static final By EDIT_PROBLEM_SOLVED = By.xpath("//label[@class='onoffswitch-label onoffswitch-label-notAdmin_false']");
    private static final By PROBLEM_DESCRIPTION = By.xpath("//div[contains(@class,'b-problem-deatiled-info-description__content')]/editproblemcontent[@value='problem.Content']/span");
    private static final By EDIT_PROBLEM_DESCRIPTION = By.xpath("//editproblemcontent[@value='problem.Content']/textarea");
    private static final By PROBLEM_PROPOSE = By.xpath("//div[contains(@class,'b-problem-deatiled-info-description__content')]/editproblemcontent[@value='problem.Proposal']/span");
    private static final By EDIT_PROBLEM_PROPOSE = By.xpath("//editproblemcontent[@value='problem.Proposal']/textarea");
    private static final By SAVE_CHANGES_BUTTON = By.xpath("//button[@class='btn btn-sm btn-warning']");

    private static final By IMAGES_EXISTED = By.xpath("//div[@class='b-problem-deatiled-info-description-photos']/div[contains(@class,'show_photo')]");
    private static final By THE_FIRST_ADDED_PHOTO = By.xpath("//div[@class='b-problem-deatiled-info-description-photos']/div[1]/img");
    private static final By NEXT_PHOTO_CONTROL = By.xpath("//div[@class='rn-carousel-controls ng-isolate-scope']/span[@ng-click='next()']");
    private static final By CLOSE_PHOTO_VIEWER_BUTTON = By.className("close");
    private static final By ADD_NEXT_PHOTO = By.xpath("//div[@class='b-details-body-problem-photo_add ng-scope']");
    private static final By SUBMIT_NEW_PHOTO = By.xpath("//input[@value='Додати']");

    private static final By COMMENTS_BUTTON = By.xpath("//div[@class='b-problem-tab ng-isolate-scope']/ul/li[2]/a");
    private static final By ADD_COMMENT_TEXT_FIELD = By.xpath("//div[@class='form-group']/textarea");
    private static final By ADDED_COMMENTS = By.xpath("//div[contains(@class,'b-activity__comments-item')]/i[contains(@class,'fa-weixin')]");
    private static final By ADD_COMMENT_BUTTON = By.xpath("//div[@class='form-group']/a[contains(@class,'btn')]");
    private static final By DELETE_COMMENT_BUTTON = By.xpath("//span[(@class='ng-binding')]/i[contains(@class,'fa-close')]");
    //private static final By ALL_ACTIVITIES = By.className("b-activity__comments-item");
    //private static final By CHECKER_THAT_ACTIVITY_IS_A_COMMENT = By.xpath(".//i[@class='fa fa-weixin b-activity__comments-item-image']");


    private WebDriver driver;

    public ProblemPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * @return String with the name of problem type.
     */
    public String getProblemType() {
        String problemIconSRC = driver.findElement(PROBLEM_ICON_SRC).getAttribute("ng-src");
        String problemType = "";
        switch(problemIconSRC) {
            case "images/markers/1.png":
                problemType = "Проблеми лісів";
                break;
            case "images/markers/2.png":
                problemType = "Сміттєзвалища";
                break;
            case "images/markers/3.png":
                problemType = "Незаконна забудова";
                break;
            case "images/markers/4.png":
                problemType = "Проблеми водойм";
                break;
            case "images/markers/5.png":
                problemType = "Загрози біорізноманіттю";
                break;
            case "images/markers/6.png":
                problemType = "Браконьєрство";
                break;
            case "images/markers/7.png":
                problemType = "Інші проблеми";
                break;
            default:
                problemType = "Не знайдено";                // Правильно ли я сделал? добавил default
                break;
        }
        return problemType;
    }

    /**
     * This method gets number id problem by coordinates;
     *
     * @param latitude is coordinate X where problem on the map.
     * @param longitude is coordinate Y where problem on the map.
     * @return problem id by parameters (latitude and longitude).
     * @throws NumberFormatException if parseInt does not contain a parsable integer
     */
    public int getProblemId(double latitude, double longitude) throws NumberFormatException {
        clickAtProblemByCoordinateVisible(latitude, longitude);
        List<String> url = Arrays.asList(driver.getCurrentUrl().split("/"));
        return Integer.parseInt(url.get(url.size() - 1));
    }

    /**
     * @return problem title in the current article.
     */
    public String getProblemTitle() {
        return driver.findElement(PROBLEM_TITLE).getText();
    }

    /**
     * Change state of problem to solved/unsolved
     */
    public void editProblemSolved(){
        driver.findElement(EDIT_PROBLEM_SOLVED).click();
    }

    /**
     * Edit title of problem.
     * @param newTitle New title for problem.
     */
    public void editProblemTitle(String newTitle) {
        driver.findElement(PROBLEM_TITLE).click();
        driver.findElement(EDIT_PROBLEM_TITLE).clear();
        driver.findElement(EDIT_PROBLEM_TITLE).sendKeys(newTitle);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        clickAtVisibleMapCenter(0);
    }

    /**
     * Edit description of problem.
     * @param newDescription New description for problem.
     */
    public void editProblemDescription(String newDescription) {
        driver.findElement(PROBLEM_DESCRIPTION).click();
        driver.findElement(EDIT_PROBLEM_DESCRIPTION).clear();
        driver.findElement(EDIT_PROBLEM_DESCRIPTION).sendKeys(newDescription);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        clickAtVisibleMapCenter(0);
    }

    /**
     * Edit propose of problem.
     * @param newPropose New propose for problem.
     */
    public void editProblemPropose(String newPropose) {
        driver.findElement(PROBLEM_PROPOSE).click();
        driver.findElement(EDIT_PROBLEM_PROPOSE).clear();
        driver.findElement(EDIT_PROBLEM_PROPOSE).sendKeys(newPropose);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        clickAtVisibleMapCenter(0);
    }

    /**
     * Change severity of problem.
     * @param severity (from 1 to 5)
     */
    public void editProblemSeverity(Byte severity) {
        By by = By.xpath("//div[@ng-init='problem.Severity']//i[" + severity + "]");
        driver.findElement(by).click();
    }

    /**
     * Press SaveButtton after edit Problem.
     */
    public void pressSaveButton() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(SAVE_CHANGES_BUTTON).click();
    }

    /**
     * @return problem description. This is text content from article.
     */
    public String getProblemDescription() {
        return driver.findElement(PROBLEM_DESCRIPTION).getAttribute("textContent");
    }

    /**
     * @return How would you have solved this problem? Return this text description.
     */
    public String getProblemPropose() {
        return driver.findElements(PROBLEM_PROPOSE).get(0).getAttribute("textContent");
    }

    /**
     * @return the current host name of a URL with the protocol name.
     */
    public String getHostURL(){
        String currentUrlHost = driver.getCurrentUrl().split("/")[2];
        return "http://" + currentUrlHost + "/";
    }

    /**
     * @return all urls to existed images on the web-site.
     */
    public List<String> getAllImagesURLs(){
        int imagesAmount = driver.findElements(IMAGES_EXISTED).size();
        List<String> imageUrls = new ArrayList<>();
        String currentUrlHost = getHostURL();
        for(int i = 1; i <= imagesAmount; i++) {
            imageUrls.add(currentUrlHost + driver.findElement(getImageSRCByItsOrderNumber(i)).getAttribute("ng-src"));
        }
        return imageUrls;
    }

    /**
     * @param i order number image.
     * @return image SRC by order number.
     */
    private By getImageSRCByItsOrderNumber(int i) {
        return By.xpath("//div[@class='b-problem-deatiled-info-description-photos']/div[" + i + "]/img");
    }

    /**
     * @return all images comments to existed images on the web-site.
     */
    public List<String> getImagesComments() {
        List<String> comments = new ArrayList<>();
        int commentsAmount = driver.findElements(IMAGES_EXISTED).size();
        if (commentsAmount == 0) {
            return comments;
        }
        driver.findElement(THE_FIRST_ADDED_PHOTO).click();
        for(int i = 1; i <= commentsAmount; i++){
            comments.add(driver.findElement(By.xpath(".//div[@class='container slider']/div/ul/li[" + i + "]/div"))
                    .getAttribute("textContent"));
            if (i < commentsAmount) {
                driver.findElement(NEXT_PHOTO_CONTROL).click();
            }
        }
        driver.findElement(CLOSE_PHOTO_VIEWER_BUTTON).click();
        return comments;
    }

    /**
     * This method adds comments to the problem by coordinates.
     *
     * @param latitude is coordinate X where problem on the map.
     * @param longitude is coordinate Y where problem on the map.
     * @param comments which need to add to the problem.
     */
    public void addComments(double latitude, double longitude, List<String> comments) {
        clickAtProblemByCoordinateVisible(latitude, longitude);
        driver.findElement(COMMENTS_BUTTON).click();
        for (String comment : comments) {
            driver.findElement(ADD_COMMENT_TEXT_FIELD).sendKeys(comment);
            driver.findElement(ADD_COMMENT_BUTTON).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
    }

    /**
     * This method deletes all comments to the problem by coordinates.
     *
     * @param latitude is coordinate X where problem on the map.
     * @param longitude is coordinate Y where problem on the map.
     */
    public void deleteComments(double latitude, double longitude) {
        clickAtProblemByCoordinateVisible(latitude, longitude);
        driver.findElement(COMMENTS_BUTTON).click();
        int commentsAmount = driver.findElements(ADDED_COMMENTS).size();
        for(int i = 0; i < commentsAmount; i++) {
            driver.findElement(DELETE_COMMENT_BUTTON).click();
        }
    }

    /**
     * This method adds comments to the list and return all existed comments on the web-site.
     *
     * @return List (Strings) with comments to the problems.
     */
    public List<String> getComments() {
        driver.findElement(COMMENTS_BUTTON).click();
        List<String> comments = new ArrayList<>();
        int commentsAmount = driver.findElements(ADDED_COMMENTS).size();
        for(int i = 1; i <= commentsAmount; i++) {
            By xpathExpression = By.xpath("//div[@class='b-activity__comments']/div[" + i + "]/div[2]/span[2]");
            comments.add(0, driver.findElement(xpathExpression).getAttribute("textContent"));
        }
        return comments;
    }

    /**
     * This method opens problem by id.
     *
     * @param id Number id of the problem.
     */
    public void openProblemById(int id) {
        driver.get(getHostURL() + "#/problem/showProblem/" + id);
    }

    /**
     * @return number of votes for the problem
     */
    public String getVote() {
        By xpathExpression = By.xpath("//div[@class='b-problem-deatiled-info-votes ng-binding']");
        return driver.findElement(xpathExpression).getText().substring(1).trim();
    }

    /**
     * This method adds vote to the problem (click button 'like')
     */
    public void addVoteToProblem() {
        driver.findElement(By.xpath("//button[@class='simple_like_img']")).click();
    }

    /**
     * This method just closes the problem.
     */
    public void closeProblem() {
        driver.findElement(By.xpath("//a[@class='close']")).click();
    }

    /**
     * This method deletes opened problems;
     */
    public void deleteOpenedProblem() {
        driver.findElement(By.xpath("//button[@class='btn btn-danger btn-sm']")).click();       //Нужно ли проверять, открыта ли проблема?
        driver.findElement(By.xpath("//button[@class='btn btn-warning ng-binding']")).click();
    }

    /**
     * This method adds new photos to problem.
     * @param imagePaths List of paths to new photos.
     */
    public void addNewPhotos(List<String> imagePaths, double latitude, double longitude) {
        clickAtProblemByCoordinateVisible(latitude, longitude);
        for (String imageUrl : imagePaths) {
            if (imageUrl == null || imageUrl.isEmpty()) {
                continue;
            }
            Thread thread = new ClipboardUploadThread(imageUrl);
            thread.start();
            driver.findElement(ADD_NEXT_PHOTO).click();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(SUBMIT_NEW_PHOTO).click();
            thread.interrupt();
        }
    }

    /**
     * This method returns number of added photos.
     */
    public int getNumberOfPhotos() {
        return driver.findElements(IMAGES_EXISTED).size();
    }
}