package _4pamoka;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Waits {

    static WebDriver driver;

    public void invokeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void kitmWithNoWait() {
        invokeDriver();

        driver.get("https://www.kitm.lt");
        String nonExistingElement = driver.findElement(By.id("s1")).getAttribute("type");
        System.out.println(nonExistingElement);
        driver.quit();
    }

    @Test
    public void kitmWithImplicitWait() {
        invokeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.kitm.lt");
        String nonExistingElement = driver.findElement(By.id("s1")).getAttribute("type");
        System.out.println(nonExistingElement);
        driver.quit();

    }

    @Test
    public void explicitWait(){
        invokeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        driver.get("https://www.f-1.lt");

        WebElement loginModal = driver.findElement(By.id("login"));
        System.out.println("is modal visible" + loginModal.isDisplayed());

        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Prisijungti")));
        driver.findElement(By.partialLinkText("Prisijungti")).click();



        wait.until(ExpectedConditions.visibilityOf(loginModal));
        driver.findElement(By.id("login-username")).sendKeys("username");
        driver.findElement(By.id("login-password")).sendKeys("fknhaf");
        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("login-error")), "ivesti neteisingi duomenys"));
    }
}
