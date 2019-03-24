package hw4;

import base.SelenideTestBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.SelenideDatesPage;
import pageObjects.SelenideHomePage;

import static com.codeborne.selenide.Selenide.page;
import static enums.Configuration.userOne.USER_ONE;
import static enums.Texts.pageTitles.PAGE_TITLES;
import static enums.Texts.rangeLogs.RANGE_LOGS;
import static enums.Texts.serviceOptions.SERVICE_OPTIONS;

public class DataPageSlidersCheck extends SelenideTestBase {

    private SelenideHomePage selenideHomePage;
    private SelenideDatesPage selenideDatesPage;


    @BeforeClass
    public void beforeClass() {
        selenideHomePage = page(SelenideHomePage.class);
        selenideDatesPage = page(SelenideDatesPage.class);
    }

    @Test
    public void dataPageSlidersCheck() {

//        1 Open test site by URL
        selenideHomePage.openBrowser();

//        2 Assert Browser title
        selenideHomePage.assertTitle(PAGE_TITLES.homePage);
//        3Perform login

        selenideHomePage.login(USER_ONE.login, USER_ONE.password);

//        4 Assert User name in the left-top side of screen that user is loggined
        selenideHomePage.assertUserName();

//        5 Open through the header menu Service -> Dates Page
        selenideHomePage.openPageFromHeaderMenu(SERVICE_OPTIONS.dates);

//        6 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most rigth position
        selenideDatesPage.moveLeftPoint(0);
        selenideDatesPage.moveRightPoint(100);

//        7 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        selenideDatesPage.checkLogsForRange(2, RANGE_LOGS.from, 0);
        selenideDatesPage.checkLogsForRange(1, RANGE_LOGS.to, 100);

//        8 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most left position.
        selenideDatesPage.moveLeftPoint(0);
        selenideDatesPage.moveRightPoint(0);

//        9 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        selenideDatesPage.checkLogsForRange(2, RANGE_LOGS.from, 0);
        selenideDatesPage.checkLogsForRange(1, RANGE_LOGS.to, 0);

//        10 Using drag-and-drop set Range sliders. left sliders - the most rigth position, right slider - the most rigth position.
        selenideDatesPage.moveRightPoint(101);
        selenideDatesPage.moveLeftPoint(101);

//        11 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        selenideDatesPage.checkLogsForRange(2, RANGE_LOGS.to, 100);
        selenideDatesPage.checkLogsForRange(1, RANGE_LOGS.from, 100);

//        12 Using drag-and-drop set Range sliders.
        selenideDatesPage.moveLeftPoint(30);
        selenideDatesPage.moveRightPoint(70);

//        13 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        selenideDatesPage.checkLogsForRange(2, RANGE_LOGS.from, 30);
        selenideDatesPage.checkLogsForRange(1, RANGE_LOGS.to, 70);
    }
}
