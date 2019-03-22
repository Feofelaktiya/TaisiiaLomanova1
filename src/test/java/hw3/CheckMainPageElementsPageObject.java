package hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import pageObjects.HomePage;

import java.util.concurrent.TimeUnit;

import static java.lang.System.setProperty;

public class CheckMainPageElementsPageObject {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeSuite
    public void beforeSuite() {
        setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

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
    public void checkMainPageElements() {

        // 1 Open test site by URL
        homePage.navigation(driver);

        // 2 Assert Browser title
        homePage.assertTitle(driver);

        // 3 Perform login
        homePage.login("epam", "1234");

        // 4 Assert User name in the left-top side of screen that user is loggined
        homePage.assertUserName();

        // 5 Assert Browser title
        homePage.assertTitle(driver);

        // 6 Assert that there are 4 items on the header section are displayed and they have proper texts
        homePage.assertItemsAndTexts();

        // 7 Assert that there are 4 images on the Index Page and they are displayed
        homePage.assertImages();

        // 8 Assert that there are 4 texts on the Index Page under icons and they have proper text
        homePage.assertTexts();

        // 9 Assert a text of the main headers
        homePage.assertMainHeaderTexts();

        // 10 Assert that there is the iframe in the center of page
        homePage.goToIframe(driver);
        homePage.takeScreenshot(driver, "onIFrame.png");

        // 11 Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        homePage.checkEpamLogoOnIFrame();

        // 12 Switch to original window back
        homePage.goBackToDefaultFrame(driver);
        homePage.takeScreenshot(driver, "onDefaultPage.png");

        // 13 Assert a text of the sub header
        homePage.assertSubHeaderText();

        // 14 Assert that JDI GITHUB is a link and has a proper URL
        homePage.assertJDILinkURL();

        // 15 Assert that there is Left Section
        homePage.assertLeftSectoinIsDisplayed();

        // 16 Assert that there is Footer
        homePage.assertFooterIsDisplayed();

    }
}