package StepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
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


public class Ratings {
    WebDriver driver;
    Map<String, Object> vars;
    JavascriptExecutor js;
    List<String> ReviewsRatings = new ArrayList<>();

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


    @Given("^: I find all reviews rating$")
    public void i_find_all_reviews_rating()  {
        ReviewsRatings.add("hello");
        // Write code here that turns the phrase above into concrete actions
        driver.get("https://smartmoneypeople.com/lloyds-bank-reviews");
        driver.manage().window().setSize(new Dimension(1050, 660));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> reviews = driver.findElements(By.className("review"));

        for (WebElement webElement : reviews) {
            // get all review ratings and find and size of ratings
            int reviewRating = webElement.findElements(By.cssSelector(".rating .full")).size();
            ReviewsRatings.add(String.valueOf(reviewRating));
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @When("^: I have more than one (\\d+)star review$")
    public void i_have_more_than_one_star_review(int arg1)  {
        // Write code here that turns the phrase above into concrete actions
        driver.get("https://smartmoneypeople.com/lloyds-bank-reviews");
        driver.manage().window().setSize(new Dimension(1050, 660));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> reviews = driver.findElements(By.className("review"));

        for (WebElement webElement : reviews) {
            // get all review ratings and find and size of ratings
             int reviewRating = webElement.findElements(By.cssSelector(".rating .full")).size();
            ReviewsRatings.add(String.valueOf(reviewRating));
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Then("^: Show only the (\\d+)stars reviews$")
    public void show_only_the_stars_reviews(int arg1)  {
        // Write code here that turns the phrase above into concrete actions
        driver.get("https://smartmoneypeople.com/lloyds-bank-reviews");
        driver.manage().window().setSize(new Dimension(1050, 660));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> reviews = driver.findElements(By.className("review"));

        for (WebElement webElement : reviews) {
            // get all review ratings and find and size of ratings
            int reviewRating = webElement.findElements(By.cssSelector(".rating .full")).size();
            ReviewsRatings.add(String.valueOf(reviewRating));
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
}
