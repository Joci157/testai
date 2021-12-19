package _3select;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.List;

public class Locators {

    public WebDriver driver;

    public void invokeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://pigu.lt/lt/televizoriai");

    }

    @Test
    public void locators() {
        invokeDriver();

        //id
        driver.findElement(By.id("searchInput"));
        //klasems
        driver.findElement(By.className("main-our-logo"));
        //klase tik naudonojant pries pavadinima reikia .
        driver.findElement(By.cssSelector(".product-name a"));
        //a
        driver.findElement(By.linkText("Televizoriai"));
        driver.findElement(By.partialLinkText("televizoriai"));

        driver.findElement(By.name("q"));
        //div, h1
        driver.findElement(By.tagName("h1"));
        driver.findElement(By.xpath(""));


        //select navigation(visi sitie budai paselektins ta pati elementa
        driver.findElement(By.cssSelector("#menu"));
        driver.findElement(By.id("menu"));
        driver.findElement(By.className("main-menu-block"));
        driver.findElement(By.xpath("//div[@id='menuu']"));
    }

}
