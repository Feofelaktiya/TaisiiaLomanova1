package lesson4;

import base.SelenideTestBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.getWebDriverLogs;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.System.setProperty;
import static org.testng.Assert.assertEquals;

public class SimpleTestSelenide extends SelenideTestBase {

    @Test
    public void simpleTest() {
        setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        //Navigate
        open("https://epam.github.io/JDI/index.html");

        assertEquals(getWebDriver().getTitle(), "Home Page");

        //Login
        $(".profile-photo").click();
        $("[id = 'name']").sendKeys("epam");
        $("[id = 'password']").sendKeys("1234");
        $("[type = 'submit']").click();

        SelenideElement mainTitle = $("h3.main-title");
        mainTitle.shouldHave(Condition.text("EPAM FRAMEWORK WISHES…"));

//        driver.close();

    }
}
