package StepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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


public class Ratings {
//    WebDriver driver = new ChromeDriver();
    public static WebDriver driver;
    List<WebElement> reviews = new ArrayList<>();

    @Given("^: I opened the llyods review page$")
    public void iOpenedTheLlyodsReviewPage() {
//        driver = new ChromeDriver();
//        driver.get("https://smartmoneypeople.com/lloyds-bank-reviews");
////        driver.manage().window().setSize(new Dimension(1050, 660));
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        List<WebElement> reviews = driver.findElements(By.className("review"));
        driver = new ChromeDriver(); //
        driver.get("https://learn.letskodeit.com/p/practice");
        driver.manage().window().maximize();
    }

    @When("^: I find more than one review$")
    public void iFindMoreThanOneReview() {
        // find all reviews in page
        reviews = driver.findElements(By.className("review"));
    }

    @Then("^: Show all reviews$")
    public void showAllReviews() {
        // print all reviews
        System.out.println(reviews);
    }

    @When("^: I find all (\\d+) stars reviews$")
    public void iFindAllStarsReviews(int arg0) {
        //find only stars reviews
    }

    @Then("^: I show all found reviews$")
    public void iShowAllFoundReviews() {
        // print only 5 stars reviews
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
