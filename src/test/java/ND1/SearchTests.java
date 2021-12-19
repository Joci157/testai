package ND1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertEquals;


public class SearchTests {

    WebDriver driver;
    String inputText;
    String expectedTitleText;
    String firstProductTitleInStock;

    @Factory(dataProvider = "searchFunctionality")
    public SearchTests(String inputText, String titleText){
        this.inputText = inputText;
        this.expectedTitleText = titleText;
    }

    @DataProvider(name = "searchFunctionality")
    public static Object[][] createData(){
        return new Object[][]{
                new Object[]{("Atminties raktas USB3.0"), ("Ieškota: Atminties raktas USB3.0 | LEMONA")},
                new Object[]{("HDD"), ("Ieškota: HDD | LEMONA")}
        };
    }

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.lemona.lt");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @AfterMethod
    public void makeScreenshotInCaseOfFailure(ITestResult result) throws IOException {
        if (!result.isSuccess()){
            String testName = result.getName();
            SimpleDateFormat time = new SimpleDateFormat("MM_dd_HH_mm_ss");
            time.setTimeZone(TimeZone.getTimeZone("Europe/Vilnius"));
            long failTime = System.currentTimeMillis();

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File("/Users/Jovita/Desktop/failurePhotos/" +
                    "screenshot_" + testName + "_" + time.format(failTime) + ".png"));

            System.out.println("Klaidos laikas: " + time.format(failTime));
        }

    }


    @Test(priority = 1)
    public void inputTextToSearchInput(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.name("search_query")));

        WebElement searchInput = driver.findElement(By.name("search_query"));
        searchInput.sendKeys(inputText);

        WebElement searchButton = driver.findElement(By.xpath("//button[contains(@class, 'search-button')]"));
        searchButton.click();

        wait.until(ExpectedConditions.titleContains(inputText));

        assertEquals(expectedTitleText, driver.getTitle());

    }

    @Test(priority = 2)
    public void addToCartFirstAvailableItem() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".product-list-product")));

        List<WebElement> allDisplayedProducts = driver.findElements(By.cssSelector(".product-list-product"));

        if (!allDisplayedProducts.isEmpty()) {
           /* WebElement firstProductInStock = allDisplayedProducts.stream().
                    filter(product -> product.findElement(By.cssSelector(".product-list-product-stock-status img"))
                            .getAttribute("alt").equals("In stock"))
                    .findFirst().orElse(null);
            assertNotNull(firstProductInStock, "No products were in stock");

            firstProductTitleInStock = firstProductInStock.findElement(By.tagName("h2")).getText();

            firstProductInStock.findElement(By.xpath(".//button[contains(@class,'btn-add-cart')]")).click();*/


            for (WebElement product : allDisplayedProducts) {
                boolean productInStock = product.findElement(By.cssSelector(".product-list-product-stock-status img"))
                        .getAttribute("alt").equals("In stock");
                String productTitle = product.findElement(By.xpath(".//h2[@class='product-title']")).getText();
                if (productInStock) {
                    firstProductTitleInStock = productTitle;
                    WebElement addToCartButton = product.findElement(By.xpath(".//button[contains(@class,'btn-add-cart')]"));
                    addToCartButton.click();
                    break;
                } else {
                    System.out.println(productTitle + " was not in stock.");
                    allDisplayedProducts.remove(product);
                }
                if (allDisplayedProducts.isEmpty()) {
                    fail("There were no inStock items to add to the cart");
                }
            }
        } else {
            fail("There was no products shown");
        }
    }

    @Test(priority = 3)
    public void checkIfFirstItemWasAdded() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dropdown-cart-products']")));

        String productInCartTitle = driver.findElement(By.cssSelector(".dropdown-cart-products .product-title a ")).getText();
        boolean matches = Pattern.matches(firstProductTitleInStock + " \\(\\d+\\)", productInCartTitle);

        assertTrue(matches);
    }


}
