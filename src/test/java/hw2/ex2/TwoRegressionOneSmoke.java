package hw2.ex2;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.setProperty;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TwoRegressionOneSmoke {

    @Test (groups = "Regression")

    public void checkMainPageElementsOne() {

        setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 1 Open test site by URL
        driver.navigate().to("https://epam.github.io/JDI/");

        // 2 Assert Browser title
        assertEquals(driver.getTitle(), "Home Page");

        // 3 Perform login
        driver.findElement(By.cssSelector(".profile-photo")).click();
        driver.findElement(By.cssSelector("[id = 'name']")).sendKeys("epam");
        driver.findElement(By.cssSelector("[id = 'password']")).sendKeys("1234");
        driver.findElement(By.id("login-button")).click();

        // 4 Assert User name in the left-top side of screen that user is loggined
        assertEquals(driver.findElement(By.id("user-name")).getText(),
                "PITER CHAILOVSKII");

        // 5 Assert Browser title
        assertEquals(driver.getTitle(), "Home Page");

        // 6 Assert that there are 4 items on the header section are displayed and they have proper texts
        List<String> expectedNames = new ArrayList<String>();
        expectedNames.add("HOME");
        expectedNames.add("CONTACT FORM");
        expectedNames.add("SERVICE");
        expectedNames.add("METALS & COLORS");
        List<WebElement> headerElements = driver.findElements(By.cssSelector("[role = 'navigation'] > ul.m-l8 > li"));
        List<String> names = new ArrayList<String>();
        for (WebElement element : headerElements) {
            String name = element.getText();
            names.add(name);
        }
        assertEquals(names, expectedNames);

        // 7 Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> icons = driver.findElements(By.cssSelector(".benefit-icon"));
        for (WebElement icon : icons) {
            assertTrue(icon.isDisplayed());
        }

        // 8 Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<String> expectedTexts = new ArrayList<String>();
        expectedTexts.add("To include good practices\nand ideas from successful\nEPAM project");
        expectedTexts.add("To be flexible and\ncustomizable");
        expectedTexts.add("To be multiplatform");
        expectedTexts.add("Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more...");
        List<WebElement> textElements = driver.findElements(By.cssSelector(".benefit-txt"));
        int size = textElements.size();
        assertEquals(size, 4);
        List<String> texts = new ArrayList<String>();
        for (WebElement element : textElements) {
            String text = element.getText();
            texts.add(text);
        }
        assertEquals(texts, expectedTexts);

        // 9 Assert a text of the main headers
        List<String> expectedHeaderTexts = new ArrayList<String>();
        expectedHeaderTexts.add("EPAM FRAMEWORK WISHES…");
        expectedHeaderTexts.add("LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD " +
                "EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");
        WebElement mainTitle = driver.findElement(By.cssSelector("h3[name=main-title]"));
        WebElement jdiText = driver.findElement(By.cssSelector("p[name=jdi-text]"));
        List<WebElement> mainHeadersTexts = new ArrayList<WebElement>();
        mainHeadersTexts.add(mainTitle);
        mainHeadersTexts.add(jdiText);
        List<String> headerTexts = new ArrayList<String>();
        for (WebElement element : mainHeadersTexts) {
            String text = element.getText();
            headerTexts.add(text);
        }
        assertEquals(headerTexts, expectedHeaderTexts);

        // 10 Assert that there is the iframe in the center of page
        driver.switchTo().frame("iframe");
        driver.findElement(By.cssSelector(".search")).click();
        driver.findElement(By.cssSelector(".search-field > input")).sendKeys("I am on the iframe");
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File screensFile = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screensFile, new File("C:/selenium/" + System.currentTimeMillis() + "onIFrame.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // 11 Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        WebElement iframeLogo = driver.findElement(By.cssSelector(".epam-logo"));
        assertTrue(iframeLogo.isDisplayed());

        // 12 Switch to original window back
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector(".search")).click();
        driver.findElement(By.cssSelector(".search-field > input")).sendKeys("I am back on the default frame");
        TakesScreenshot screenshot1 = (TakesScreenshot) driver;
        File screensFile1 = screenshot1.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screensFile1, new File("C:/selenium/" + System.currentTimeMillis() + "onDefaultPage.png"));
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }

        // 13 Assert a text of the sub header
        WebElement subHeader = driver.findElement(By.linkText("JDI GITHUB"));
        assertEquals(subHeader.getText(),
                "JDI GITHUB");

        // 14 Assert that JDI GITHUB is a link and has a proper URL
        assertEquals(subHeader.getAttribute("href"), "https://github.com/epam/JDI");

        // 15 Assert that there is Left Section
        WebElement leftSection = driver.findElement(By.cssSelector("div[name = navigation-sidebar]"));
        assertTrue(leftSection.isDisplayed());

        // 16 Assert that there is Footer
        WebElement footer = driver.findElement(By.cssSelector("footer"));
        assertTrue(footer.isDisplayed());

        // 17 Close browser
        driver.close();
    }

    @Test (groups = "Regression")

    public void checkMainPageElementsTwo() {

        setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 1 Open test site by URL
        driver.navigate().to("https://epam.github.io/JDI/");

        // 2 Assert Browser title
        assertEquals(driver.getTitle(), "Home Page");

        // 3 Perform login
        driver.findElement(By.cssSelector(".profile-photo")).click();
        driver.findElement(By.cssSelector("[id = 'name']")).sendKeys("epam");
        driver.findElement(By.cssSelector("[id = 'password']")).sendKeys("1234");
        driver.findElement(By.id("login-button")).click();

        // 4 Assert User name in the left-top side of screen that user is loggined
        assertEquals(driver.findElement(By.id("user-name")).getText(),
                "PITER CHAILOVSKII");

        // 5 Assert Browser title
        assertEquals(driver.getTitle(), "Home Page");

        // 6 Assert that there are 4 items on the header section are displayed and they have proper texts
        List<String> expectedNames = new ArrayList<String>();
        expectedNames.add("HOME");
        expectedNames.add("CONTACT FORM");
        expectedNames.add("SERVICE");
        expectedNames.add("METALS & COLORS");
        List<WebElement> headerElements = driver.findElements(By.cssSelector("[role = 'navigation'] > ul.m-l8 > li"));
        List<String> names = new ArrayList<String>();
        for (WebElement element : headerElements) {
            String name = element.getText();
            names.add(name);
        }
        assertEquals(names, expectedNames);

        // 7 Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> icons = driver.findElements(By.cssSelector(".benefit-icon"));
        for (WebElement icon : icons) {
            assertTrue(icon.isDisplayed());
        }

        // 8 Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<String> expectedTexts = new ArrayList<String>();
        expectedTexts.add("To include good practices\nand ideas from successful\nEPAM project");
        expectedTexts.add("To be flexible and\ncustomizable");
        expectedTexts.add("To be multiplatform");
        expectedTexts.add("Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");
        List<WebElement> textElements = driver.findElements(By.cssSelector(".benefit-txt"));
        int size = textElements.size();
        assertEquals(size, 4);
        List<String> texts = new ArrayList<String>();
        for (WebElement element : textElements) {
            String text = element.getText();
            texts.add(text);
        }
        assertEquals(texts, expectedTexts);

        // 9 Assert a text of the main headers
        List<String> expectedHeaderTexts = new ArrayList<String>();
        expectedHeaderTexts.add("EPAM FRAMEWORK WISHES…");
        expectedHeaderTexts.add("LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD " +
                "EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");
        WebElement mainTitle = driver.findElement(By.cssSelector("h3[name=main-title]"));
        WebElement jdiText = driver.findElement(By.cssSelector("p[name=jdi-text]"));
        List<WebElement> mainHeadersTexts = new ArrayList<WebElement>();
        mainHeadersTexts.add(mainTitle);
        mainHeadersTexts.add(jdiText);
        List<String> headerTexts = new ArrayList<String>();
        for (WebElement element : mainHeadersTexts) {
            String text = element.getText();
            headerTexts.add(text);
        }
        assertEquals(headerTexts, expectedHeaderTexts);

        // 10 Assert that there is the iframe in the center of page
        driver.switchTo().frame("iframe");
        driver.findElement(By.cssSelector(".search")).click();
        driver.findElement(By.cssSelector(".search-field > input")).sendKeys("I am on the iframe");
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File screensFile = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screensFile, new File("C:/selenium/" + System.currentTimeMillis() + "onIFrame.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // 11 Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        WebElement iframeLogo = driver.findElement(By.cssSelector(".epam-logo"));
        assertTrue(iframeLogo.isDisplayed());

        // 12 Switch to original window back
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector(".search")).click();
        driver.findElement(By.cssSelector(".search-field > input")).sendKeys("I am back on the default frame");
        TakesScreenshot screenshot1 = (TakesScreenshot) driver;
        File screensFile1 = screenshot1.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screensFile1, new File("C:/selenium/" + System.currentTimeMillis() + "onDefaultPage.png"));
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }

        // 13 Assert a text of the sub header
        WebElement subHeader = driver.findElement(By.linkText("JDI GITHUB"));
        assertEquals(subHeader.getText(),
                "JDI GITHUB");

        // 14 Assert that JDI GITHUB is a link and has a proper URL
        assertEquals(subHeader.getAttribute("href"), "https://github.com/epam/JDI");

        // 15 Assert that there is Left Section
        WebElement leftSection = driver.findElement(By.cssSelector("div[name = navigation-sidebar]"));
        assertTrue(leftSection.isDisplayed());

        // 16 Assert that there is Footer
        WebElement footer = driver.findElement(By.cssSelector("footer"));
        assertTrue(footer.isDisplayed());

        // 17 Close browser
        driver.close();
    }

    @Test (groups = "Smoke")

    public void checkMainPageElementsThree() {

        setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 1 Open test site by URL
        driver.navigate().to("https://epam.github.io/JDI/");

        // 2 Assert Browser title
        assertEquals(driver.getTitle(), "Home Page");

        // 3 Perform login
        driver.findElement(By.cssSelector(".profile-photo")).click();
        driver.findElement(By.cssSelector("[id = 'name']")).sendKeys("epam");
        driver.findElement(By.cssSelector("[id = 'password']")).sendKeys("1234");
        driver.findElement(By.id("login-button")).click();

        // 4 Assert User name in the left-top side of screen that user is loggined
        assertEquals(driver.findElement(By.id("user-name")).getText(),
                "PITER CHAILOVSKII");

        // 5 Assert Browser title
        assertEquals(driver.getTitle(), "Home Page");

        // 6 Assert that there are 4 items on the header section are displayed and they have proper texts
        List<String> expectedNames = new ArrayList<String>();
        expectedNames.add("HOME");
        expectedNames.add("CONTACT FORM");
        expectedNames.add("SERVICE");
        expectedNames.add("METALS & COLORS");
        List<WebElement> headerElements = driver.findElements(By.cssSelector("[role = 'navigation'] > ul.m-l8 > li"));
        List<String> names = new ArrayList<String>();
        for (WebElement element : headerElements) {
            String name = element.getText();
            names.add(name);
        }
        assertEquals(names, expectedNames);

        // 7 Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> icons = driver.findElements(By.cssSelector(".benefit-icon"));
        for (WebElement icon : icons) {
            assertTrue(icon.isDisplayed());
        }

        // 8 Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<String> expectedTexts = new ArrayList<String>();
        expectedTexts.add("To include good practices\nand ideas from successful\nEPAM project");
        expectedTexts.add("To be flexible and\ncustomizable");
        expectedTexts.add("To be multiplatform");
        expectedTexts.add("Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");
        List<WebElement> textElements = driver.findElements(By.cssSelector(".benefit-txt"));
        int size = textElements.size();
        assertEquals(size, 4);
        List<String> texts = new ArrayList<String>();
        for (WebElement element : textElements) {
            String text = element.getText();
            texts.add(text);
        }
        assertEquals(texts, expectedTexts);

        // 9 Assert a text of the main headers
        List<String> expectedHeaderTexts = new ArrayList<String>();
        expectedHeaderTexts.add("EPAM FRAMEWORK WISHES…");
        expectedHeaderTexts.add("LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD " +
                "EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.");
        WebElement mainTitle = driver.findElement(By.cssSelector("h3[name=main-title]"));
        WebElement jdiText = driver.findElement(By.cssSelector("p[name=jdi-text]"));
        List<WebElement> mainHeadersTexts = new ArrayList<WebElement>();
        mainHeadersTexts.add(mainTitle);
        mainHeadersTexts.add(jdiText);
        List<String> headerTexts = new ArrayList<String>();
        for (WebElement element : mainHeadersTexts) {
            String text = element.getText();
            headerTexts.add(text);
        }
        assertEquals(headerTexts, expectedHeaderTexts);

        // 10 Assert that there is the iframe in the center of page
        driver.switchTo().frame("iframe");
        driver.findElement(By.cssSelector(".search")).click();
        driver.findElement(By.cssSelector(".search-field > input")).sendKeys("I am on the iframe");
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File screensFile = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screensFile, new File("C:/selenium/" + System.currentTimeMillis() + "onIFrame.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // 11 Switch to the iframe and check that there is Epam logo in the left top conner of iframe
        WebElement iframeLogo = driver.findElement(By.cssSelector(".epam-logo"));
        assertTrue(iframeLogo.isDisplayed());

        // 12 Switch to original window back
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector(".search")).click();
        driver.findElement(By.cssSelector(".search-field > input")).sendKeys("I am back on the default frame");
        TakesScreenshot screenshot1 = (TakesScreenshot) driver;
        File screensFile1 = screenshot1.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screensFile1, new File("C:/selenium/" + System.currentTimeMillis() + "onDefaultPage.png"));
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }

        // 13 Assert a text of the sub header
        WebElement subHeader = driver.findElement(By.linkText("JDI GITHUB"));
        assertEquals(subHeader.getText(),
                "JDI GITHUB");

        // 14 Assert that JDI GITHUB is a link and has a proper URL
        assertEquals(subHeader.getAttribute("href"), "https://github.com/epam/JDI");

        // 15 Assert that there is Left Section
        WebElement leftSection = driver.findElement(By.cssSelector("div[name = navigation-sidebar]"));
        assertTrue(leftSection.isDisplayed());

        // 16 Assert that there is Footer
        WebElement footer = driver.findElement(By.cssSelector("footer"));
        assertTrue(footer.isDisplayed());

        // 17 Close browser
        driver.close();
    }
}