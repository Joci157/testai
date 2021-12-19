package _1pamoka.chrome;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTestJUnit {

    @Test
    public void invokeChrome() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\webDrivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("http://www.kitm.lt");
        Thread.sleep(10000);
    }

}
