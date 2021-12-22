package Haroon;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class FeefoTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void example() throws IOException {
        driver.get("https://www.feefo.com/en-GB/reviews/mercedes-benz-south-west?displayFeedbackType=BOTH&timeFrame=YEAR");
        driver.manage().window().setSize(new Dimension(1050, 660));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<WebElement> reviews = driver.findElements(By.cssSelector("all-mode-review"));
        List<String> list = new ArrayList<>();
        String header = "Comment, Commentator,Time,Rating\n";
        System.out.println(header);
//        driver.findElement(By.cssSelector("div.global-load-more-link-wrapper > a")).click();
        list.add(header);
        list.addAll(getReview(reviews));

        FileWriter writer = new FileWriter("feefo.csv");
        for(String str: list) {
            writer.write(str);
        }
        writer.close();
    }


    public List getReview(List<WebElement> reviews ) {
        List<String> list = new ArrayList<>();
        for (WebElement webElement : reviews) {

            // get review comment by classname
            String reviewComment = webElement.findElement(By.cssSelector("read-more-text")).getText();
            // get review time  by css selector
            String reviewTime = webElement.findElement(By.className("all-mode-review__customer-date")).getAttribute("title");
            // get review commentator name by css selector
            String reviewCommentator = webElement.findElement(By.cssSelector("h4.all-mode-review__info-text.all-mode-review__customer-name.ng-scope > strong")).getText();
            // get raw review rating by fetching the width style used to set the stars
            String webReviewRating = webElement.findElement(By.className("all-mode-review__header-stars-inner")).getAttribute("style");
            //remove unneccesary text
            webReviewRating = webReviewRating.replace("width: ", "");
            //convert raw rating to int and remove percentage sign
            int percentageReviewRating = Integer.parseInt(webReviewRating.replace("%;", ""));
            //calculate the rating using the percentage
            int reviewRating = percentageReviewRating/100*5;
//
            String commentLine =  cleanString(reviewComment) + "," + reviewCommentator + "," + reviewTime + "," + cleanString(reviewRating + "/5");

            System.out.println(commentLine  + "\n");
            list.add(commentLine);
        }
        return list;
    }

    public static String cleanString(
            String pValue) {
        if (pValue == null) {
            return null;
        } else {
            if (pValue.contains("\"")) {
                pValue = pValue.replace("\"", "\"\"");
            }
            if (pValue.contains(",")
                    || pValue.contains("\n")
                    || pValue.contains("'")
                    || pValue.contains("\\")
                    || pValue.contains("\"")) {
                return "\"" + pValue + "\"";
            }
        }
        return pValue;
    }
}