package hw4;

import base.SelenideTestBase;
import enums.PageTitles;
import enums.ServiceOptions;
import enums.Users;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.AllureAttachmentListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.SelenideDatesPage;
import pageObjects.SelenideHomePage;

import static com.codeborne.selenide.Selenide.page;
import static enums.Configuration.*;
import static enums.PageTitles.HOME_PAGE;
import static enums.RangeLogs.FROM;
import static enums.RangeLogs.TO;
import static enums.ServiceOptions.DATES;

@Feature("Smoke tests")
@Story("Different Elements Page Testing")
@Listeners(AllureAttachmentListener.class)
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
        selenideHomePage.assertTitle(PageTitles.HOME_PAGE);
//        3Perform login

        selenideHomePage.login(Users.PITER_CHAILOVSKII);

//        4 Assert User name in the left-top side of screen that user is loggined
        selenideHomePage.assertUserName(Users.PITER_CHAILOVSKII);

//        5 Open through the header menu Service -> Dates Page
        selenideHomePage.openPageFromHeaderMenu(ServiceOptions.DATES);

//        6 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most rigth position
        selenideDatesPage.moveLeftPoint(0);
        selenideDatesPage.moveRightPoint(100);

//        7 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        selenideDatesPage.checkLogsForRange(2, FROM.log, 0);
        selenideDatesPage.checkLogsForRange(1, TO.log, 100);

//        8 Using drag-and-drop set Range sliders. left sliders - the most left position, right slider - the most left position.
        selenideDatesPage.moveLeftPoint(0);
        selenideDatesPage.moveRightPoint(0);

//        9 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        selenideDatesPage.checkLogsForRange(2, FROM.log, 0);
        selenideDatesPage.checkLogsForRange(1, TO.log, 0);

//        10 Using drag-and-drop set Range sliders. left sliders - the most rigth position, right slider - the most rigth position.
        selenideDatesPage.moveRightPoint(101);
        selenideDatesPage.moveLeftPoint(101);

//        11 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        selenideDatesPage.checkLogsForRange(2, TO.log, 100);
        selenideDatesPage.checkLogsForRange(1, FROM.log, 100);

//        12 Using drag-and-drop set Range sliders.
        selenideDatesPage.moveLeftPoint(30);
        selenideDatesPage.moveRightPoint(70);

//        13 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        selenideDatesPage.checkLogsForRange(2, FROM.log, 30);
        selenideDatesPage.checkLogsForRange(1, TO.log, 70);
    }
}
