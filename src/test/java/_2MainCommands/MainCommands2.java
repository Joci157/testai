package _2MainCommands;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class MainCommands2 {

    public WebDriver driver;

    public void invokeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void elementDisabled() {
        invokeDriver();
        driver.get("https://html.com/attributes/input-disabled/");
        boolean isEnabled = driver.findElement(By.cssSelector("input[name='disabled']")).isEnabled();
        System.out.println(isEnabled);

        driver.quit();

    }

    @Test
    public  void enterTextOnlyIfInputEnabled() throws InterruptedException {
        invokeDriver();
        driver.get("https://www.kitm.lt");

        boolean isEnabled = driver.findElement(By.id("s")).isEnabled();
        boolean isDisplayed = driver.findElement(By.id("s")).isDisplayed();

        if (isDisplayed && isEnabled){
            driver.findElement(By.id("s")).sendKeys("programuotojas");
            driver.findElement(By.id("searchsubmit")).click();
        }else{
            System.out.println("input field was not visible or not enabled");
        }
        Thread.sleep(2000);
        driver.quit();

    }
}
