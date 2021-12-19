package _3select;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class xPathSelectors {

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
    public void xPath() {
        invokeDriver();

        driver.findElement(By.xpath("//div[@id='menu']"));
        driver.findElement(By.xpath("//div@widget='Menu'"));
        driver.findElement(By.xpath("//*[@widget='Menu']"));
        driver.findElement(By.xpath("//div[contains(@class, '-menu-block')]"));
    }
}
