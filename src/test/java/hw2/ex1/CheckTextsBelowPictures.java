package hw2.ex1;

import DataProviders.DataProviders;
import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class CheckTextsBelowPictures extends TestBase {

    @Test(dataProvider = "textsBelowPictures", dataProviderClass = DataProviders.class, threadPoolSize = 4, invocationCount = 1)
    public void simpleTest(String forXpath, String text) {

        //1 Open BR
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //2 Navigate
        driver.navigate().to("https://epam.github.io/JDI/index.html");

        //3 Assert Title
        assertEquals(driver.getTitle(), "Home Page");

        //4 Assert 4 texts below 4 pictures
        String actualText = driver.findElement(By.xpath("//span[contains(text()," + "\"" + forXpath + "\")]")).getText();
        assertEquals(actualText, text);

        //5 Close browser
        driver.close();
    }
}
