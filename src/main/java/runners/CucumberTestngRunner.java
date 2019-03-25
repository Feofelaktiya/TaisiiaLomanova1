
package runners;

import com.codeborne.selenide.Configuration;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.close;

@CucumberOptions(
//        features = "src/test/java/hw6/CheckHomePageAndDatesPageUsingSelenideCucumber.feature",
        features = {"src/test/java/hw6/CheckHomePageAndDatesPageUsingSelenideCucumber.feature", "src/test/java/hw6/CheckHomePageAndUserTablePageUsingSelenideCucumber.feature"},
        glue = {"pageObjects.cucumber"})
public class CucumberTestngRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite (alwaysRun = true)
    public void beforeSuite() {
        Configuration.browser = "chrome";
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        Configuration.startMaximized = true;
    }
    @AfterMethod (alwaysRun = true)
    public void afterMethod() {
        close();
    }
}