package ND;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.*;

public class Waits {
    static WebDriver driver;

    public void invokeDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver");
        driver.manage().window().maximize();
    }

    @Test
    public void ClickAndWaitForAlert(){
        invokeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        driver.findElement(By.id("alert")).click();

       try {
           wait.until(ExpectedConditions.alertIsPresent());
       }catch (TimeoutException e){
           fail("Alert nepasirode per 2sek");
       }
        driver.switchTo().alert().accept();

        driver.quit();
    }


    @Test
    public void ClickAndWaitForTextChanges(){
        String expectedText = "Selenium Webdriver";
        By heading = By.id("h2");

        invokeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("populate-text")).click();

        wait.until(ExpectedConditions.textToBe(heading, expectedText));

        driver.quit();

    }

    @Test
    public void clickAndWaitTillButtonDiplayed(){
        By heading = By.id("hidden");

        invokeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("display-other-button")).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(heading)));
        //wait.until(ExpectedConditions.elementToBeClickable(heading));

        driver.findElement(heading).isDisplayed();
        assertTrue(driver.findElement(heading).isDisplayed(), "Button is not displayed");

        driver.quit();
    }


    @Test
    public void clickAndWaitTillButtoneWillBeEnable(){
        invokeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("enable-button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("disable")));
        assertTrue(driver.findElement(By.id("disable")).isEnabled(), "Button is unable");
        driver.quit();
    }

    @Test

    public void clickAndWaitTillCheckboxWillBeChecked(){
        invokeDriver();
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("checkbox")).click();

        wait.until(ExpectedConditions.elementToBeSelected(By.id("ch")));
        driver.quit();
    }

}
