package _3select;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Scanner;

public class Selects {

    public WebDriver driver;
    WebElement dropdown;


    public void invokeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void selectDropDownByValue() throws InterruptedException {
        invokeDriver();
        driver.get("https://demoqa.com/select-menu");

        WebElement dropdown = driver.findElement(By.id("oldSelectMenu"));

        Select select = new Select(dropdown);
        select.selectByValue("3");
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void selectDropDownByIndex() throws InterruptedException {
        invokeDriver();
        driver.get("https://demoqa.com/select-menu");

        WebElement dropdown = driver.findElement(By.id("oldSelectMenu"));

        Select select = new Select(dropdown);
        select.selectByIndex(2);
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void selectDropdownByText() throws InterruptedException {

        invokeDriver();
        driver.get("https://demoqa.com/select-menu");
        WebElement dropdown = driver.findElement(By.id("oldSelectMenu"));

        Select select = new Select(dropdown);
        select.selectByVisibleText("Aqua");

        Thread.sleep(5000);
        driver.quit();
    }

    @Test

    public void selecWhenMultipleOptions() throws InterruptedException {
        invokeDriver();
        driver.get("https://demoqa.com/select-menu");
        WebElement multiSelectInput = driver.findElement(By.id("cars"));

        Select carsInput = new Select(multiSelectInput);

        if (carsInput.isMultiple()) {
            carsInput.selectByVisibleText("Opel");
            carsInput.selectByVisibleText("Audi");
            carsInput.selectByVisibleText("Volvo");
        } else {
            System.out.println("This element does not allow to select multiple choices");
        }

        Thread.sleep(3000);

        carsInput.deselectByVisibleText("Opel");
        Thread.sleep(3000);

        carsInput.getAllSelectedOptions().forEach(option -> System.out.println(option.getText()));

        driver.quit();
    }


    @Test
    public void selectDropdownWhenOpeningIt() throws InterruptedException {

        invokeDriver();
        driver.get("https://demoqa.com/select-menu");

        WebElement select = driver.findElement(By.id("oldSelectMenu"));

        select.click();
        List<WebElement> options = select.findElements(By.tagName("option"));
        System.out.println(options.size());

        for (WebElement option : options) {
            if (option.getText().equals("Voilet")) {
                option.click();
                break;
            }
        }
        Thread.sleep(3000);

        driver.quit();

    }

    @Test
    public void selectWhenTwoDropdowns() throws InterruptedException {
        invokeDriver();
        driver.get("https://demoqa.com/select-menu");

        WebElement colorsInput = driver.findElement(By.id("oldselectMenu"));
        WebElement carsInput = driver.findElement(By.id("cars"));

        Select colors = new Select(colorsInput);
        Select cars = new Select(carsInput);

        colors.selectByVisibleText("Black");
        cars.selectByVisibleText("Volvo");
        cars.selectByVisibleText("Audi");
        cars.selectByVisibleText("Opel");
        cars.selectByVisibleText("Saab");

        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void openWantedCareerPosition() throws InterruptedException {
        invokeDriver();
        driver.get("https://www.kitm.lt");

        Thread.sleep(5000);
        List<WebElement> careerOptions = driver.findElements(By.cssSelector(".feature"));
        for (WebElement careerOption : careerOptions) {
            String option = careerOption.getText();
            if (option.startsWith("JAVA")) {
                careerOption.click();
                break;
            }
        }

        String heading = driver.findElement(By.id("post-title")).getText();
        if (heading.equals("JAVA PROGRAMUOTOJAS")) {
            System.out.println("click works");
        } else {
            System.out.println("click does not work");
        }
    }

    @Test
    public void openWantedCareerPositionWithStream() throws InterruptedException {
        invokeDriver();
        driver.get("https://www.kitm.lt");

        Thread.sleep(5000);
        List<WebElement> careerOptions = driver.findElements(By.cssSelector(".feature"));
        careerOptions.stream().filter(careerOption -> careerOption.getText().startsWith("JAVA"))
                .findFirst().get()
                .click();

        String heading = driver.findElement(By.id("post-title")).getText();
        if (heading.equals("JAVA PROGRAMUOTOJAS")) {
            System.out.println("click works");
        } else {
            System.out.println("click does not work");
        }
    }

    @Test
    public void clickOnRadioButton(){
        invokeDriver();
        driver.get("https://www.zkoss.org/zkdemo/input/radio_nutton");

        driver.findElement(By.id("u0yZk-real")).click();
        driver.findElement(By.id("u0yZu-real")).click();
        driver.findElement(By.id("u0yZ_8-real")).click();

        String feature = driver.findElement(By.id("u0yZ20")).getText();
        String website = driver.findElement(By.id("u0yZ50")).getText();
        String documentation = driver.findElement(By.id("u0yZ80")).getText();

        String fullText = feature + website + documentation;
        if(fullText.contains("Bug fixing")){
            System.out.println("All good");
        }else{
            System.out.println();
        }

    }
}
