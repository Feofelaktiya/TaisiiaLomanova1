package base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeSuite;

public class SelenideTestBase {
    @BeforeSuite (alwaysRun = true)
    public void beforeSuite() {
        Configuration.browser = "chrome";
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        Configuration.startMaximized = true;
    }
}
