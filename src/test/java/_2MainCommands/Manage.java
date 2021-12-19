package _2MainCommands;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.Set;

public class Manage {

    public WebDriver driver;

    public void invokeWebDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors",
                "--start-maximized"
                //"--window-size=360,800"
        );


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.kitm.lt");
    }

    @Test
    public void manageCommands(){
        invokeWebDriver();
        driver.quit();
    }

    @Test
    public void cookies(){
        invokeWebDriver();

        Set<Cookie> cookies = driver.manage().getCookies();
        cookies.forEach(cookie -> System.out.println(cookie.getValue()));
    }

}
