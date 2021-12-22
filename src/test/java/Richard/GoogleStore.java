package Richard;

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

public class GoogleStore {
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
        driver.get("https://play.google.com/store/apps/details?id=app.spidy.spidy&gl=GB&showAllReviews=true");
        driver.manage().window().setSize(new Dimension(1050, 660));
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        String test = "hello";
        List<String> fileContent = new ArrayList<>();

        String reviewText, starRating, date;

        List<WebElement> reviews = driver.findElements(By.xpath("//div[@jscontroller=\"H6eOGe\"]"));
        for (WebElement review : reviews) {
            String reviewId = review.getAttribute("jsdata");
            System.out.println(reviewId);
            reviewText = driver.findElement(By.xpath("//div[@jsdata = \"" + reviewId + "\"]/div/div[2]/div[2]/span[1]\n")).getText();
            System.out.println(reviewText);
            starRating = driver.findElement(By.xpath("//div[@jsdata = \"" + reviewId + "\"]/div/div[2]/div[1]/div[1]/div/span[1]/div/div\n")).getAttribute("aria-label");
            System.out.println(starRating);
            date = driver.findElement(By.xpath("//div[@jsdata = \"" + reviewId + "\"]/div/div[2]/div[1]/div[1]/div/span[2]\n")).getText();
            fileContent.add("\"" + reviewText + "\"" + "," + "\"" + starRating + "\"" + "," + "\"" + date + "\"");
        }

        File file = new File("test3.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Review,Star Rating,Date");
        bw.newLine();
        for (String s : fileContent) {
            bw.write(s);
            bw.newLine();
        }
        bw.close();
        fw.close();
    }
}