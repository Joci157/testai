package _2MainCommands;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class MainCommands {

    public WebDriver driver;

    public void invokeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void mainCommands() {
        invokeDriver();
        System.out.println("test begins");
        driver.get("https://www.kitm.lt");

        String title = driver.getTitle();
        if (title.equals("Kauno informacinių technologijų mokykla")) {
            System.out.println("All good");
        } else {
            System.out.println("Test has failed, tittle was: " + title);
        }

        String currentURL = driver.getCurrentUrl();
        if (currentURL.endsWith(".lt/")) {
            System.out.println("All good");
        } else {
            System.out.println("TLD was incorrect, expected: .lt/, got: " + currentURL);
        }

        driver.quit();

    }

    @Test
    public void navigationCommands() throws InterruptedException {
        invokeDriver();
        driver.get("https://www.kitm.lt");
        Thread.sleep(5000);
        driver.navigate().to("https://www.google.com");
        Thread.sleep(5000);
        driver.navigate().back();
        Thread.sleep(5000);
        driver.navigate().forward();
        Thread.sleep(2000);
        driver.navigate().refresh();
        System.out.println(driver.getTitle());
        driver.quit();
    }


    @Test

    public void click() throws InterruptedException {
        invokeDriver();
        driver.get("https://www.kitm.lt");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".circle")).click();
        Thread.sleep(3000);
        String title = driver.getTitle();
        String heading = driver.findElement(By.id("post-title")).getText();
        // String heading = driver.findElement(By.cssSelector("#post-title")).getText();

        if (heading.equals("PROGRAMINĖS ĮRANGOS TESTUOTOJAS")) {
            System.out.println("Title is correct");
        } else {
            System.out.println("Title was wrong");
        }
        driver.quit();
    }

    @Test

    public void testSearch() throws InterruptedException {
        invokeDriver();
        driver.get("https://www.kitm.lt");
        driver.findElement(By.id("s")).sendKeys("programuotojas");
        Thread.sleep(3000);

        driver.findElement(By.id("s")).clear();
        Thread.sleep(3000);
        driver.findElement(By.id("s")).sendKeys("testuotojas");
        Thread.sleep(3000);

        driver.findElement(By.id("searchsubmit")).click();
        Thread.sleep(2000);
        //TODO exchange with waits

        String firstArticle = driver.findElement(By.cssSelector(".post-title")).getText().toLowerCase();
        if (firstArticle.contains("testuotojas")) {
            System.out.println("Fisrt article was as expected");
        } else {
            System.out.println("First article was wrong");
        }

        driver.quit();
    }

    @Test

    public void menuIsDisplayedOnBigScreen() {
        invokeDriver();
        driver.get("https://www.kitm.lt");

        boolean menuIsVisible = driver.findElement(By.cssSelector("#menu-main-navigation")).isDisplayed();
        System.out.println(menuIsVisible);

        driver.quit();
    }

    @Test
    public void menuISNotDisplayedOnSmallScreens() {
        invokeDriver();
        driver.manage().window().setSize(new Dimension(768, 780));
        driver.get("https://www.kitm.lt");
        boolean menuIsVisible = driver.findElement(By.cssSelector("#menu-main-navigation")).isDisplayed();
        System.out.println(menuIsVisible);
        driver.quit();
    }

    @Test
    public void elementNotFound() {


        boolean menuIsVisible = false;

        try {
            menuIsVisible = driver.findElement(By.cssSelector("#menu-main-navigation-test")).isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("element was not found");
        }

        System.out.println(menuIsVisible);
        driver.quit();
    }


}
