package Thomas;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class KFC {
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
    public void KFCfacebookReviews() throws IOException, InterruptedException {
        driver.get("https://www.facebook.com/kfc/reviews/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[text()='Allow All Cookies']")).click(); //accept cookies
        JavascriptExecutor js = (JavascriptExecutor) driver;
//        driver.findElement(By.name("email")).sendKeys("tomscattergood@hotmail.co.uk");
//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {
//        }
//        driver.findElement(By.name("pass")).sendKeys("Ir0nmaiden18");
//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {
//        }
//        driver.findElement(By.id("loginbutton")).click();



        //List<WebElement> reviews2 = driver.findElements(By.xpath("//div[@class='_1dwg _1w_m _q7o']")); //retrieves name of reviewer, date of review and comment
//        System.out.println(reviews2);

        List<String> list = new ArrayList<>();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        List<WebElement> reviews2 = null;
        try {
            long lastHeight=(long)js.executeScript("return document.body.scrollHeight");
            int scrollCount = 0;
            while (scrollCount<4) {
                reviews2 = driver.findElements(By.xpath("(//div[contains(@class,'userContentWrapper')])"));
                // System.out.println(reviews2);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(2000);
                long newHeight = (long)js.executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
                scrollCount++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < reviews2.size(); i++) {
            String date = reviews2.get(i).findElements(By.xpath("(//span[@class='timestampContent'])")).get(i).getText().replaceAll(",","");
            String review = reviews2.get(i).findElements(By.xpath("//div[@data-testid='post_message']")).get(i).getText().replaceAll(",","");

            String xpath = review.substring(0,9);

            List <WebElement> likes = driver.findElements(By.xpath("//div[@data-testid='post_message']//p[contains(text(),'"+xpath+"')]" + "/parent::div/../../../../parent::div//span[@aria-label='See who reacted to this']/span/a"));
            StringBuilder like = new StringBuilder();
            for(int j=0;j< likes.size();j++){
                like.append(likes.get(j).getAttribute("aria-label")).append(System.lineSeparator());
            }
            System.out.println(like);

            list.add(date);
            list.add(review);
            list.add(like.toString());
            System.out.println(list);
        }

        FileWriter writer = new FileWriter("KFCReviews.txt");
        for (String str : list) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();

        File file = new File("KFCReviews.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Date, Review, Reactions");
        bw.newLine();
        for(int i=0;i<(list.size());i++)
        {
            bw.write(list.get(i) + ", ");
            //bw.newLine();
        }
        bw.close();
        fw.close();
    }
}