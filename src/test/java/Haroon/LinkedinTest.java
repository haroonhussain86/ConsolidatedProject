package Haroon;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringEscapeUtils;
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

public class LinkedinTest {
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
    public void example3() throws IOException {
        driver.get("https://www.linkedin.com/posts/bbc-news_walkers-apologises-for-crisp-shortages-activity-6864473611646881792-say2/");
        driver.manage().window().setSize(new Dimension(1050, 660));
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//        driver.findElement(By.xpath("//html/body/div[1]/div/section/div/div[2]/button[2]")).click();
//        JavascriptExecutor js = (JavascriptExecutor)driver;
        List<WebElement> reviews = driver.findElements(By.className("comment"));
        List<String> list = new ArrayList<>();
        String header = "Comment, Commentator,Time\n";
        System.out.println(header);
        list.add(header);
        for (WebElement webElement : reviews) {
            // get comment
            String comment = webElement.findElement(By.cssSelector(".comment__message > span")).getText();
            String commentActor = webElement.findElement(By.cssSelector(".comment__actor-name-wrapper > a")).getText();
            String commentTime = webElement.findElement(By.cssSelector(".comment__time")).getText();
//            String likes = webElement.findElements(By.className(".comment__likes-count")).size() > 0 ? webElement.findElement(By.className(".comment__likes-count")).getText() : "N/A";
            String commentLine =  cleanString(comment) + "," + commentActor + ","+ commentTime;
//
            System.out.println(commentLine  + "\n");
            list.add(commentLine);

        }
        FileWriter writer = new FileWriter("linkedin.csv");
        for(String str: list) {
            writer.write(str + System.lineSeparator() + System.lineSeparator());
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