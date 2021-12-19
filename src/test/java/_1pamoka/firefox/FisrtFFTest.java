package _1pamoka.firefox;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class FisrtFFTest {

    @Test

    public void firstTestFF(){
        System.setProperty("webdriver.gecko.driver", "C:\\webDrivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.kitm.lt");
    }
}
