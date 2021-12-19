package ND;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;


public class Varle {

    public WebDriver driver;
    public int searchResults;
    static WebElement searchRow;

    public void invokeDriver() {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void openShop() {
        invokeDriver();
        driver.get("https://www.varle.lt/");

    }

    @Test(priority = 2)
    public void searchInputIsVisible() {
        boolean searchInput = driver.findElement(By.className("search-input")).isDisplayed();
        //searchInput = driver.findElement(By.cssSelector("form[action$='rch/'"))
        assertTrue(searchInput, "search not found");
    }

    @Test(priority = 3)
    public void searchInputIsWorking() throws InterruptedException {
        driver.findElement(By.name("q")).sendKeys("televizoriai");
        Thread.sleep(2000);
        driver.findElement(By.name("q")).clear();
    }

    @Test(priority = 4)
    public void BannerIsVisible() {
        boolean BannerIsVisible = driver.findElement(By.className("c-message")).isDisplayed();
        assertTrue(BannerIsVisible, "Banner not found");
    }

    @Test(priority = 5)
    public void ClickBanner() throws InterruptedException {
        driver.findElement(By.id("c-right")).click();

        WebElement cookieBanner = driver.findElement(By.cssSelector("div[name='cookiebanner']"));
        boolean cookieBannerIsShown = cookieBanner.isDisplayed();
        assertFalse(cookieBannerIsShown, "Cookie banner was still visible");

    }

    @Test(priority = 6)
    public void SelectLenovo() throws InterruptedException {
        driver.findElement(By.xpath("//img[@alt='Lenovo']")).click();
    }

    @Test(priority = 7)
    public void expectedResultsAreLenovo() {
        String heading = driver.findElement(By.xpath("//h1[@class='search-text']")).getText().toLowerCase();
        boolean headingContainsLenovo = heading.contains("lenovo");

        assertTrue(headingContainsLenovo, " Heading does not contain word 'Lenovo'");

    }

    @Test(priority = 8)
    public void checkAllGivenOptionRelatedToLenovo() {
        List<WebElement> allLenovoBuyingOptions = driver.findElements(By.xpath("//div[class='product-title']/a"));
        // List<WebElement>allLenovoBuyingOptions = driver.findElements(By.cssSelector("//div[class='product-title'] a"));
        // List<WebElement>allLenovoBuyingOptions = driver.findElements(By.cssSelector("product-title a"));

       /* for (WebElement option : allLenovoBuyingOptions) {
            if (option.getText().toLowerCase().contains("lenovo")) fail("Product did not have Lenovo word");
        }*/


        ArrayList<String> notLenovo = new ArrayList<>();
        for (WebElement option : allLenovoBuyingOptions) {
            if (!option.getText().toLowerCase().contains("lenovo")) notLenovo.add(option.getText());
        }
        System.out.println(notLenovo);
        assertEquals(notLenovo.size(), 0, "There were products without Lenovo");
    }


    @Test(priority = 9)
    public void FilterLaptop() throws InterruptedException {
        String countOfSearchResultsBeforeFilteringLaptops = driver.findElement(By.xpath("//h1[@class='search-text']/span")).getText();
        int countOfResultsBeforeFiltering = Integer.parseInt(countOfSearchResultsBeforeFilteringLaptops);

        WebElement laptop = driver.findElement(By.xpath("//li[contains(@class, 'range-filter-value')]/span[contains(text(), 'Nešiojami kompiuteriai')]"));
        laptop.click();
        Thread.sleep(2000);


        String countOfSearchResultsAfterFilteringLaptops = driver.findElement(By.xpath("//h1[@class='search-text']/span")).getText();
        int countOfResultsAfterFilteringLaptops = Integer.parseInt(countOfSearchResultsAfterFilteringLaptops);
    }

//    List<WebElement> filters = driver.findElements(By.className("selected-filter"));
//    boolean correctFilterIsDisplaied = false;
//         for(WebElement filter :filters){
//        if (filter.getText().equals("Nešiojami kompiuteriai")) {
//            correctFilterIsDisplaied = true;
//            break;
//        }
//    }


    @Test(priority = 10)
    public void SortFromBiggestToLowestPrice() throws InterruptedException {
        WebElement sortDropdown = driver.findElement(By.xpath(".for-desktop [name='filter-by']"));
        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Brangiausi viršuje");
        Thread.sleep(2000);

        
    }

    @Test(priority = 11)
    public void ChooseSort() throws InterruptedException {
        //driver.findElement(By.xpath("//*[text()='Brangiausi viršuje']")).click();
        //Thread.sleep(2000);
        Select sort = new Select(driver.findElement(By.className("for-desktop")));
        sort.selectByValue("-price");
        Thread.sleep(2000);
    }

}

