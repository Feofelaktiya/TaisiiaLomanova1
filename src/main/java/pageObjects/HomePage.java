package pageObjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePage {

    @FindBy(css = ".profile-photo")
    private WebElement profileButton;

    @FindBy(css = "[id = 'name']")
    private WebElement login;

    @FindBy(css = "[id = 'password']")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(id = "user-name")
    private WebElement userName;

    @FindBy(css = "[role = 'navigation'] > ul.m-l8 > li")
    private List<WebElement> elements;

    @FindBy(css = ".benefit-icon")
    private List<WebElement> images;

    @FindBy(css = ".benefit-txt")
    private List<WebElement> texts;

    @FindBy(css = "h3[name=main-title]")
    private WebElement mainHeader;

    @FindBy(css = "p[name=jdi-text]")
    private WebElement jdiHeader;

    @FindBy(css = ".search")
    private WebElement search;

    @FindBy(css = ".search-field > input")
    private WebElement searchInput;

    @FindBy(css = ".epam-logo")
    private WebElement epamLogo;

    @FindBy(linkText = "JDI GITHUB")
    private WebElement subHeader;

    @FindBy(css = "div[name = navigation-sidebar]")
    private WebElement leftSection;

    @FindBy(css = "footer")
    private WebElement footer;


    public void navigation(WebDriver driver) {
        driver.navigate().to("https://epam.github.io/JDI/");
    }

    public void assertTitle(WebDriver driver) {
        assertEquals(driver.getTitle(), "Home Page");
    }

    public void assertUserName() {
        assertEquals(userName.getText(), "PITER CHAILOVSKII");
    }

    public void login(String name, String passwd) {
        profileButton.click();
        login.sendKeys(name);
        password.sendKeys(passwd);
        loginButton.click();
    }

    public void assertItemsAndTexts() {
        List<String> expectedNames = new ArrayList<String>();
        expectedNames.add("HOME");
        expectedNames.add("CONTACT FORM");
        expectedNames.add("SERVICE");
        expectedNames.add("METALS & COLORS");
        List<WebElement> headerElements = elements;
        List<String> names = new ArrayList<String>();
        for (WebElement element : headerElements) {
            String name = element.getText();
            names.add(name);
        }
        assertEquals(names, expectedNames);
    }

    public void assertImages() {
        List<WebElement> icons = images;
        for (WebElement icon : icons) {
            assertTrue(icon.isDisplayed());
        }
    }

    public void assertTexts() {
        List<String> expectedTexts = new ArrayList<String>();
        expectedTexts.add("To include good practices\nand ideas from successful\nEPAM project");
        expectedTexts.add("To be flexible and\ncustomizable");
        expectedTexts.add("To be multiplatform");
        expectedTexts.add("Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more" + "…");
        List<WebElement> textElements = texts;
        int size = textElements.size();
        assertEquals(size, 4);
        List<String> texts = new ArrayList<String>();
        for (WebElement element : textElements) {
            String text = element.getText();
            texts.add(text);
        }
        assertEquals(texts, expectedTexts);
    }

    public void assertMainHeaderTexts() {

        List<String> expectedHeaderTexts = new ArrayList<String>();
        expectedHeaderTexts.add("EPAM FRAMEWORK WISHES…");
        expectedHeaderTexts.add("LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD " +
                "EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");
        WebElement mainTitle = mainHeader;
        WebElement jdiText = jdiHeader;
        List<WebElement> mainHeadersTexts = new ArrayList<WebElement>();
        mainHeadersTexts.add(mainTitle);
        mainHeadersTexts.add(jdiText);
        List<String> headerTexts = new ArrayList<String>();
        for (WebElement element : mainHeadersTexts) {
            String text = element.getText();
            headerTexts.add(text);
        }
        assertEquals(headerTexts, expectedHeaderTexts);
    }

    public void goToIframe(WebDriver driver) {
        driver.switchTo().frame("iframe");
        search.click();
        searchInput.sendKeys("I am on the iframe");

    }

    public void goBackToDefaultFrame(WebDriver driver) {
        driver.switchTo().defaultContent();
        search.click();
        searchInput.sendKeys("I am back on the default frame");
    }

    public void checkEpamLogoOnIFrame() {
        WebElement iframeLogo = epamLogo;
        assertTrue(iframeLogo.isDisplayed());
    }

    public void assertSubHeaderText() {
        assertEquals(subHeader.getText(), "JDI GITHUB");

    }

    public void assertJDILinkURL() {
        assertEquals(subHeader.getAttribute("href"), "https://github.com/epam/JDI");
    }

    public void assertLeftSectoinIsDisplayed() {
        assertTrue(leftSection.isDisplayed());
    }

    public void assertFooterIsDisplayed() {
        assertTrue(footer.isDisplayed());
    }

    public void takeScreenshot(WebDriver driver, String fileName) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File screensFile = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screensFile, new File("C:/selenium/" + System.currentTimeMillis() + fileName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
