package com.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by onikistc on 21.10.2014.
 */
public class ProblemPage extends AnyPage{

    private static final By PROBLEM_ICON_SRC = By.xpath("//img[@class='b-problem-deatiled-info-title__icon']");
    private static final By PROBLEM_TITLE = By.xpath("//h1");
    private static final By EDIT_PROBLEM_TITLE = By.xpath("//editproblemtitle/input");
    private static final By PROBLEM_DESCRIPTION = By.xpath("//div[contains(@class,'b-problem-deatiled-info-description__content')]/editproblemcontent[@value='problem.Content']/span");
    private static final By EDIT_PROBLEM_DESCRIPTION = By.xpath("//editproblemcontent[@value='problem.Content']/textarea");
    private static final By PROBLEM_PROPOSE = By.xpath("//div[contains(@class,'b-problem-deatiled-info-description__content')]/editproblemcontent[@value='problem.Proposal']/span");
    private static final By EDIT_PROBLEM_PROPOSE = By.xpath("//editproblemcontent[@value='problem.Proposal']/textarea");
    private static final By SAVE_CHANGES_BUTTON = By.xpath("//button[@class='btn btn-sm btn-warning']");

    private static final By IMAGES_EXISTED = By.xpath("//div[@class='b-problem-deatiled-info-description-photos']/div[contains(@class,'show_photo')]");
    private static final By THE_FIRST_ADDED_PHOTO = By.xpath("//div[@class='b-problem-deatiled-info-description-photos']/div[1]/img");
    private static final By NEXT_PHOTO_CONTROL = By.xpath("//div[@class='rn-carousel-controls ng-isolate-scope']/span[@ng-click='next()']");
    private static final By CLOSE_PHOTO_VIEWER_BUTTON = By.className("close");

    private static final By COMMENTS_BUTTON = By.xpath("//div[@class='b-problem-tab ng-isolate-scope']/ul/li[2]/a");
    private static final By ADD_COMMENT_TEXT_FIELD = By.xpath("//div[@class='form-group']/textarea");
    private static final By ADDED_COMMENTS = By.xpath("//div[contains(@class,'b-activity__comments-item')]/i[contains(@class,'fa-weixin')]");
    private static final By ADD_COMMENT_BUTTON = By.xpath("//div[@class='form-group']/a[contains(@class,'btn')]");
    private static final By DELETE_COMMENT_BUTTON = By.xpath(".//div[2]/span[2]/i");
    //private static final By ALL_ACTIVITIES = By.className("b-activity__comments-item");
    //private static final By CHECKER_THAT_ACTIVITY_IS_A_COMMENT = By.xpath(".//i[@class='fa fa-weixin b-activity__comments-item-image']");


    private WebDriver driver;

    public ProblemPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getProblemType() {
        String problemIconSRC;
        problemIconSRC = driver.findElement(PROBLEM_ICON_SRC).getAttribute("ng-src");
        String problemType = null;
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
        }
        return problemType;
    }

    public int getProblemId(double latitude, double longitude) {
        List<String> url;
        clickAtProblemByCoordinateVisible(latitude, longitude);
        url = Arrays.asList(driver.getCurrentUrl().split("/"));
        return Integer.parseInt(url.get(url.size() - 1));
    }

    public String getProblemTitle() {
        return driver.findElement(PROBLEM_TITLE).getText();
    }


    public void editProblemTitle(String newTitle) {
//        editProblemSeverity((byte)5);
        driver.findElement(PROBLEM_TITLE).click();
        driver.findElement(EDIT_PROBLEM_TITLE).clear();
        driver.findElement(EDIT_PROBLEM_TITLE).sendKeys(newTitle);
        clickAtVisibleMapCenter(0);
       // driver.findElement(SAVE_CHANGES_BUTTON).click();
    }

    public void editProblemDescription(String newDescription) {
        driver.findElement(PROBLEM_DESCRIPTION).click();
        driver.findElement(EDIT_PROBLEM_DESCRIPTION).clear();
        driver.findElement(EDIT_PROBLEM_DESCRIPTION).sendKeys(newDescription);
        clickAtVisibleMapCenter(0);
        //driver.findElement(SAVE_CHANGES_BUTTON).click();
        System.out.println("ddddddddddddddddddddddddddddddddddddd");
    }

    public void editProblemPropose(String newPropose) {
        driver.findElement(PROBLEM_PROPOSE).click();
        driver.findElement(EDIT_PROBLEM_PROPOSE).clear();
        driver.findElement(EDIT_PROBLEM_PROPOSE).sendKeys(newPropose);
        clickAtVisibleMapCenter(0);
        //driver.findElement(SAVE_CHANGES_BUTTON).click();
        System.out.println("ggggggggggggggggggggggggggggggggggggggggg");
    }

    public void editProblemSeverity(Byte severity) {
        By by = By.xpath("//i[" + severity + "]");
        driver.findElement(by).click();
    }
    public void pressSaveButton() {
        driver.findElement(SAVE_CHANGES_BUTTON).click();
    }

    public String getProblemDescription() {
        return driver.findElement(PROBLEM_DESCRIPTION).getAttribute("textContent");
    }

    public String getProblemPropose() {
        return driver.findElements(PROBLEM_PROPOSE).get(1).getAttribute("textContent");
    }

    public String getHostURL(){
        String currentUrlHost = driver.getCurrentUrl().split("/")[2];
        return "http://" + currentUrlHost + "/";
    }

    public List<String> getAllImagesURLs(){
        int imagesAmount = driver.findElements(IMAGES_EXISTED).size();
        List<String> imageUrls = new ArrayList<>();
        String currentUrlHost = getHostURL();
        for(int i = 1; i <= imagesAmount; i++) {
            imageUrls.add(currentUrlHost + driver.findElement(getImageSRCByItsOrderNumber(i)).getAttribute("ng-src"));
        }
        return imageUrls;
    }

    private By getImageSRCByItsOrderNumber(int i) {
        return By.xpath("//div[@class='b-problem-deatiled-info-description-photos']/div[" + i + "]/img");
    }


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

    public void addComments(double latitude, double longitude, List<String> comments) {
        clickAtProblemByCoordinateVisible(latitude, longitude);

        driver.findElement(COMMENTS_BUTTON).click();
        for(String comment : comments) {
            driver.findElement(ADD_COMMENT_TEXT_FIELD).sendKeys(comment);
            driver.findElement(ADD_COMMENT_BUTTON).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
    }

    public void deleteComments(double latitude, double longitude) {
        clickAtProblemByCoordinateVisible(latitude, longitude);
        driver.findElement(COMMENTS_BUTTON).click();
        int commentsAmount = driver.findElements(ADDED_COMMENTS).size();
        for(int i = 0; i < commentsAmount; i++) {
            driver.findElement(DELETE_COMMENT_BUTTON).click();
        }
    }

    public List<String> getComments() {
        driver.findElement(COMMENTS_BUTTON).click();
        List<String> comments = new ArrayList<>();
        int commentsAmount = driver.findElements(ADDED_COMMENTS).size();
        for(int i = 1; i <= commentsAmount; i++) {
            comments.add(0, driver.findElement(By.xpath("//div[@class='b-activity__comments']/div[" + i + "]/div[2]/span[2]")).getAttribute("textContent"));
        }
        return comments;
    }

    public void openProblemById(int id) {
        driver.get(getHostURL() + "#/problem/showProblem/" + id);
    }

    public String getVote() {
        String vote = driver.findElement(By.xpath("//div[@class='b-problem-deatiled-info-votes ng-binding']")).getText().substring(1).trim();
        return vote;
    }

    public void addVoteToProblem() {
        driver.findElement(By.xpath("//button[@class='simple_like_img']")).click();
    }

    public void closeProblem() {
        driver.findElement(By.xpath("//a[@class='close']")).click();
    }

    public void deleteOpenedProblem() {
        driver.findElement(By.xpath("//button[@class='btn btn-danger btn-sm']")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-warning ng-binding']")).click();
    }
}