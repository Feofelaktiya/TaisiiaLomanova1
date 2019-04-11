package hw5;

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
import static enums.CheckboxTexts.WATER;
import static enums.CheckboxTexts.WIND;
import static enums.Configuration.*;
import static enums.DropdownColors.YELLOW;
import static enums.PageTitles.HOME_PAGE;
import static enums.RadiobuttonTexts.SELEN;
import static enums.ServiceOptions.DIFFERENT_ELEMENTS;

@Feature("Smoke tests")
@Story("Different Elements Page Testing")
@Listeners(AllureAttachmentListener.class)
public class ServicePageInterfaceCheckAllureReport extends SelenideTestBase {

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
        selenideHomePage.assertTitle(HOME_PAGE.title);

//        3 Perform login
        selenideHomePage.login(LOGIN.text, PASSWORD.text);

//        4 Assert User name in the left-top side of screen that user is loggined
        selenideHomePage.assertUserName(NAME.text);

//        5 Click on "Service" subcategory in the header and check that drop down contains options
        selenideHomePage.checkHeaderDropdown();

//        6 Click on Service subcategory in the left section and check that drop down contains options
        selenideHomePage.checkLeftSectionOptions();

//        7 Open through the header menu Service -> Different Elements Page
        selenideHomePage.openPageFromHeaderMenu(DIFFERENT_ELEMENTS.option);

//        8 Check interface on Different elements page, it contains all needed elements
        selenideDifferentElementsPage.checkPageContainsElements();

//        9 Assert that there is Right Section
        selenideDifferentElementsPage.checkRightSection();

//        10 Assert that there is Left Section
        selenideDifferentElementsPage.checkLeftSection();

//        11 Select checkboxes
        selenideDifferentElementsPage.selectCheckbox(WATER.text);
        selenideDifferentElementsPage.selectCheckbox(WIND.text);


//        12 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox. 
        selenideDifferentElementsPage.checkLogForCheckboxex(1, WIND.text, true);
        selenideDifferentElementsPage.checkLogForCheckboxex(2, WATER.text, true);

//        13 Select radio
        selenideDifferentElementsPage.selectRadiobutton(SELEN.text);

//        14 Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton. 
        selenideDifferentElementsPage.checkLogForRadiobuttons(1, SELEN.text);

//        15 Select in dropdown
        selenideDifferentElementsPage.selectDropdownOption(YELLOW.color);

//        16 Assert that for dropdown there is a log row and value is corresponded to the selected value. 
        selenideDifferentElementsPage.checkLogForDropdown(1, YELLOW.color);

//        17 Unselect and assert checkboxes
        selenideDifferentElementsPage.selectCheckbox(WATER.text);
        selenideDifferentElementsPage.selectCheckbox(WIND.text);

//        18 Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox. 
        selenideDifferentElementsPage.checkLogForCheckboxex(1, WIND.text, false);
        selenideDifferentElementsPage.checkLogForCheckboxex(2, WATER.text, false);
    }
}
