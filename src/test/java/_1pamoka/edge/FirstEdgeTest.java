package _1pamoka.edge;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class FirstEdgeTest {

    @Test

    public void firstEdgeTest(){
        System.setProperty("webdriver.edge.driver", "C:\\webDrivers\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.get("http://www.kitm.lt");
    }
}
