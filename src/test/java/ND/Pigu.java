package ND;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class Pigu {

    public WebDriver driver;
    public List<WebElement> searchResults;

    public void invokeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void searchIsVisible(){
        invokeDriver();
        driver.get("https://pigu.lt/lt/");

        boolean searchIsVisible = driver.findElement(By.id("searchInput")).isDisplayed();
        assertTrue(searchIsVisible, "SEARCH NOT FOUND");
    }

    @Test(priority = 2)
    public void URLIsCorrectAfterSearch(){
        driver.findElement(By.id("searchInput")).sendKeys("televizoriai");
        //SURANDA IR PASPAUDZIA PAIESKOS MYGTUKA
        driver.findElement(By.cssSelector(".search-form button")).click();

        String currentURL = driver.getCurrentUrl();
        boolean urlContainsExpected = currentURL.contains("televizoriai");
        boolean urlDoesNotContainExpected = currentURL.contains("washing");
        assertTrue(urlContainsExpected, "url did not contain expected text");
        assertFalse(urlDoesNotContainExpected, "URL contained washing");
    }

    @Test(priority = 3)
    public void titleIsCorrect(){
        String actualTitle = driver.getTitle();
        String expectedTitle = "Televizoriai pigiau internetu | pigu.lt";
        assertEquals(actualTitle, expectedTitle, "Expected title was different than expected");

    }

    @Test(priority  = 4)
    public void correctNumberOfProductIsDisplayed(){
        List<WebElement> searchResults = driver.findElements(By.cssSelector(".product-name a"));
        int expectedNumberOfProducts = 59;
        assertEquals(searchResults.size(), expectedNumberOfProducts, "Number of products was different than expected");

    }

    @Test(priority = 5)
    public void philipsIsAmongTheSearchResultsAndOpensUp() throws InterruptedException {
        WebElement philipsTV =  searchResults.stream()
                .filter(searchResult -> searchResult.getText().contains("Philips"))
                .findFirst().get();

        Actions action = new Actions(driver);
        action.moveToElement(philipsTV).perform();
        Thread.sleep(5000);
        philipsTV.click();

        String heading = driver.findElement(By.tagName("h1")).getText();
       boolean headingHasPhilips = heading.contains("Philips");
       assertTrue(headingHasPhilips, "Heading does not contain Philips, " );
    }


  /*  @Test
    public void testSearch2() throws InterruptedException {


        //PATIKRINA AR HEADINGAS TURI ZODI "PHILIPS"
      /*  String heading = driver.findElement(By.tagName("h1")).getText();
        if(heading.contains("Philips")){
            System.out.println("HEADING IS AS EXPECTED");
        }else{
            System.out.println("HEADING WAS WRONG");
        }
        Thread.sleep(2000);

        //PASPAUSTI MYGTUKA I KREPSELI
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,100");

        if(driver.findElement(By.cssSelector("#stickyCarButton")).isDisplayed()){
            System.out.println("\"I krepseli\"mygtukas pasirode");
        }

        driver.findElement(By.cssSelector(".addtocart-btn-block")).click();
        Thread.sleep(2000);

        driver.quit();
    }*/
}

