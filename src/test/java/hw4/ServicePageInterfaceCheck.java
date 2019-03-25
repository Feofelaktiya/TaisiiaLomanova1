package hw4;

import base.SelenideTestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.AllureAttachmentListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.SelenideDifferentElementsPage;
import pageObjects.SelenideHomePage;

import static com.codeborne.selenide.Selenide.page;
import static enums.Configuration.userOne.USER_ONE;
import static enums.Texts.checkboxTexts.CHECKBOX_TEXTS;
import static enums.Texts.dropdownColors.DROPDOWN_COLORS;
import static enums.Texts.pageTitles.PAGE_TITLES;
import static enums.Texts.radiobuttonTexts.RADIOBUTTON_TEXTS;
import static enums.Texts.serviceOptions.SERVICE_OPTIONS;

@Feature("Smoke tests")
@Story("Different Elements Page Testing")
@Listeners(AllureAttachmentListener.class)
public class ServicePageInterfaceCheck extends SelenideTestBase {

    private SelenideHomePage selenideHomePage;
    private SelenideDifferentElementsPage selenideDifferentElementsPage;


    @BeforeClass
    public void beforeClass() {
        selenideHomePage = page(SelenideHomePage.class);
        selenideDifferentElementsPage = page(SelenideDifferentElementsPage.class);
    }

    @Test
    public void servicePageInterfaceCheck() {

//        1 Open test site by URL
        selenideHomePage.openBrowser();

//        2 Assert Browser title
        selenideHomePage.assertTitle(PAGE_TITLES.homePage);

//        3 Perform login
        selenideHomePage.login(USER_ONE.login, USER_ONE.password);

//        4 Assert User name in the left-top side of screen that user is loggined
        selenideHomePage.assertUserName();

//        5 Click on "Service" subcategory in the header and check that drop down contains options
        selenideHomePage.checkHeaderDropdown();

//        6 Click on Service subcategory in the left section and check that drop down contains options
        selenideHomePage.checkLeftSectionOptions();

//        7 Open through the header menu Service -> Different Elements Page
        selenideHomePage.openPageFromHeaderMenu(SERVICE_OPTIONS.differentElements);

//        8 Check interface on Different elements page, it contains all needed elements
        selenideDifferentElementsPage.checkPageContainsElements();

//        9 Assert that there is Right Section
        selenideDifferentElementsPage.checkRightSection();

//        10 Assert that there is Left Section
        selenideDifferentElementsPage.checkLeftSection();

//        11 Select checkboxes
        selenideDifferentElementsPage.selectCheckbox(CHECKBOX_TEXTS.water);
        selenideDifferentElementsPage.selectCheckbox(CHECKBOX_TEXTS.wind);


//        12 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox. 
        selenideDifferentElementsPage.checkLogForCheckboxex(1, CHECKBOX_TEXTS.wind, true);
        selenideDifferentElementsPage.checkLogForCheckboxex(2, CHECKBOX_TEXTS.water, true);

//        13 Select radio
        selenideDifferentElementsPage.selectRadiobutton(RADIOBUTTON_TEXTS.selen);

//        14 Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton. 
        selenideDifferentElementsPage.checkLogForRadiobuttons(1, RADIOBUTTON_TEXTS.selen);

//        15 Select in dropdown
        selenideDifferentElementsPage.selectDropdownOption(DROPDOWN_COLORS.yellow);

//        16 Assert that for dropdown there is a log row and value is corresponded to the selected value. 
        selenideDifferentElementsPage.checkLogForDropdown(1, DROPDOWN_COLORS.yellow);

//        17 Unselect and assert checkboxes
        selenideDifferentElementsPage.selectCheckbox(CHECKBOX_TEXTS.water);
        selenideDifferentElementsPage.selectCheckbox(CHECKBOX_TEXTS.wind);

//        18 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox. 
        selenideDifferentElementsPage.checkLogForCheckboxex(1, CHECKBOX_TEXTS.wind, false);
        selenideDifferentElementsPage.checkLogForCheckboxex(2, CHECKBOX_TEXTS.water, false);
    }
}
