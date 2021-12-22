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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class BankSelTest {
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
        driver.get("https://smartmoneypeople.com/lloyds-bank-reviews");
        driver.manage().window().setSize(new Dimension(1050, 660));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<WebElement> reviews = driver.findElements(By.className("review"));
        List<String> list = new ArrayList<>();
        String header = "Title,Comment,Commentator,Time,Rating\n";
        System.out.println(header);
        list.add(header);

        for (WebElement webElement : reviews) {

            // get review comment by classname
            String reviewComment = webElement.findElement(By.className("review-content")).getText();
            //get review title by css selector
            String reviewTitle = webElement.findElement(By.cssSelector(".panel-body > blockquote > h3")).getText();
            // get review time  by css selector
            String reviewTime = webElement.findElement(By.cssSelector(".panel-body > blockquote > div > small")).getText();
            // replace unneccesary text - Reviewed on - with empty space
            reviewTime = reviewTime.replace("Reviewed on ", "");
            // get review commentator name by css selector
            String reviewCommentator = webElement.findElement(By.cssSelector(".panel-body > blockquote > cite > span > a > strong")).getText();
            // get all review ratings and find and size of ratings
            int reviewRating = webElement.findElements(By.cssSelector(".rating .full")).size();

            String commentLine =  cleanString(reviewTitle) + "," + cleanString(reviewComment) + "," + reviewCommentator + "," + reviewTime + "," +  cleanString(reviewRating + "/5");

            System.out.println(commentLine  + "\n");
            list.add(commentLine);
        }
        File file = new File("bank.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Title,Comment,Commentator,Time,Rating");
        bw.newLine();
//        for(int i=0;i<(commentLine));
//            {
//                Parameterized reviewsTitle;
//                bw.write(reviewsTitle.get(i) + ", "+ reviewComment.get(i) + ", " + reviewCommentator.get(i));
//                bw.newLine();
//            }
//        bw.close();
//        fw.close();
        FileWriter writer = new FileWriter("bank.csv");
        for(String str: list) {
            writer.write(str);
        }
        writer.close();
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