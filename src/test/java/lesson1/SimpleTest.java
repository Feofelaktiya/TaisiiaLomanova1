package lesson1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.System.setProperty;
import static org.testng.Assert.*;

public class SimpleTest {

    @Test
    public void simpleTest() {
        setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");


        // Open browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //Navigate
        driver.navigate().to("https://epam.github.io/JDI/index.html");

        assertEquals(driver.getTitle(), "Home Page");

        //Login
        driver.findElement(By.cssSelector(".profile-photo")).click();

        driver.close();
    }
}
