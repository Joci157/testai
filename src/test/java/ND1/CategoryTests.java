package ND1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CategoryTests {

    public WebDriver driver;

    public void invokeDriver(){
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void beforeMethod(){
        invokeDriver();
        driver.get("https://www.lemona.lt/");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("Padarysiu po to");
    }




}
