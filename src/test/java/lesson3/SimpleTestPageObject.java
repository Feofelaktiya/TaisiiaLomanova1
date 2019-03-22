package lesson3;

import base.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class SimpleTestPageObject extends TestBase {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        homePage = PageFactory.initElements(driver, HomePage.class);
    }


    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.close();
    }
    @Test
    public void simpleTest() {
        //Navigate
        driver.navigate().to("https://epam.github.io/JDI/index.html");

        assertEquals(driver.getTitle(), "Home Page");

        //Login
        homePage.login("epam", "1234");
    }
}
