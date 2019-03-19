package lesson2;

import DataProviders.DataProviders;
import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class SimpleTestDataProvider extends TestBase {

    @Test(dataProvider = "simpleDataProvider", dataProviderClass = DataProviders.class)
    public void simpleTest(String s, int i) {

        //1 Open BR
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //2 Navigate
        driver.navigate().to("https://epam.github.io/JDI/index.html");

        //3 Assert Title
        assertEquals(driver.getTitle(), "Home Page");

        //4 Login
        driver.findElement(By.cssSelector(".profile-photo")).click();
        driver.close();
        System.out.println("String: " + s);
        System.out.println("Integer: " + i);
    }
}
