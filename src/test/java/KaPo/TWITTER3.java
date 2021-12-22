package KaPo;// Generated by Selenium IDE
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TWITTER3 {
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

        String URL1 = "https://twitter.com/search?q=hsbc%20mobile%20banking&src=typed_query&f=live";
        driver.get(URL1);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<String> list = new ArrayList<>();
        List<String> usernameList = new ArrayList<>();
        List<WebElement> tweetList = driver.findElements(By.xpath("//*[@data-testid='tweet']"));



            List<WebElement> tweetBody = driver.findElements(By.xpath("//div[@class=\"css-901oao r-18jsvk2 r-37j5jr r-a023e6 r-16dba41 r-rjixqe r-bcqeeo r-bnwqim r-qvutc0\" and @dir=\"auto\"]"));
            for (WebElement tweets : tweetBody) {
                String tweetBody1 = tweets.getText();
                System.out.println(tweetBody1 + System.lineSeparator());
                list.add(tweetBody1);
            }


            List<WebElement> username = driver.findElements(By.xpath("//*[@data-testid='tweet']//div[@class=\"css-901oao css-bfa6kz r-14j79pv r-18u37iz r-37j5jr r-a023e6 r-16dba41 r-rjixqe r-bcqeeo r-qvutc0\"]//span[@class = \"css-901oao css-16my406 r-poiln3 r-bcqeeo r-qvutc0\"]"));
            for (WebElement usernameGrab : username) {
                String usernameAdd = usernameGrab.getText();
                System.out.println(usernameAdd + System.lineSeparator());
                usernameList.add(usernameAdd);
            }


        }
    }

