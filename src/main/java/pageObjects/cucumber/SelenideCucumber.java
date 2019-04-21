package pageObjects.cucumber;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.*;
import enums.cucumber.*;
import io.cucumber.datatable.DataTable;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.*;
import static enums.SectionTitles.SERVICE;
import static enums.ServiceOptions.*;

public class SelenideCucumber {

    public SelenideCucumber() {
        page(this);
    }

    @FindBy(css = ".profile-photo")
    private SelenideElement userIcon;

    @FindBy(css = "[id = 'name']")
    private SelenideElement loginInput;

    @FindBy(css = "[id = 'password']")
    private SelenideElement passwordInput;

    @FindBy(id = "login-button")
    private SelenideElement submitButton;

    @FindBy(id = "user-name")
    private SelenideElement userName;

    @FindBy(css = "li.menu-title[index = '3'] > ul > li")
    private ElementsCollection leftSectionElements;

    @FindBy(css = "p > span")
    private SelenideElement leftSectionSupport;

    @FindBy(css = ".dropdown-toggle")
    private SelenideElement serviceDropdown;

    @FindBy(css = ".dropdown-menu > li")
    private ElementsCollection dropdownOptions;

    @FindBy(css = ".dropdown-toggle")
    private SelenideElement dropdownName;

    @FindBy(css = "ul.panel-body-list.logs > li")
    private List<SelenideElement> logs;

    @FindBy(css = ".benefit-icon")
    private ElementsCollection images;

    @FindBy(css = ".benefit-txt")
    private ElementsCollection texts;

    @FindBy(css = "h3[name=main-title]")
    private SelenideElement mainHeader;

    @FindBy(css = "p[name=jdi-text]")
    private SelenideElement jdiHeader;

    @FindBy(css = "li.menu-title[index = '3']")
    private SelenideElement leftSectionServiceOption;

    @FindBy(css = "div[name=navigation-sidebar]")
    private SelenideElement navigationSidebar;

    @FindBy(css = ".label-checkbox")
    private ElementsCollection labelCheckboxes;

    @FindBy(css = ".label-radio")
    private ElementsCollection labelRadiobuttons;

    @FindBy(css = "select.uui-form-element > option")
    private ElementsCollection colorDropdown;

    @FindBy(css = "select.uui-form-element")
    private SelenideElement selectedColorField;

    @FindBy(css = ".main-content-hg > button.uui-button")
    private SelenideElement defaultButton;

    @FindBy(css = "input.uui-button")
    private SelenideElement button;

    @FindBy(css = "div[name=log-sidebar]")
    private SelenideElement rightSection;

    @Step("Open site")
    @Given("I am on the Home Page")
    public void openHomePage() {
        open("https://epam.github.io/JDI/");
    }

    @Step("Check home title")
    @Then("The browser title is Home Page")
    public void checkBrowserTitleIsHomePage() {
        $(byTitle(PageTitles.HOME_PAGE.toString())).isDisplayed();
    }

    @Step("Login")
    @When("I login as user (.+)")
    public void login(String piter) {
        userIcon.click();
        Users userConf = Users.valueOf(piter);
        loginInput.sendKeys(userConf.name);
        passwordInput.sendKeys(userConf.password);
        submitButton.click();
    }

    @Step("Check user name")
    @Then("The (.+) user name is displayed")
    public void checkUserName(String user) {
        Users userConf = Users.valueOf(user);
        userName.shouldHave(Condition.exactText(userConf.user));
    }

    @Step("Check interface on Home page, it contains all needed elements:")
    @Then("Home Page contains benefits")
    public void checkBenefits() {
        images.shouldHaveSize(4);
        texts.shouldHaveSize(4);
    }

    @Step("Check texts above benefits are presented")
    @Then("Home Page contains texts above benefits")
    public void checkTextsAboveBenefits() {
        mainHeader.shouldBe(Condition.visible);
        jdiHeader.shouldBe(Condition.visible);
    }

    @Step("Click on Service subcategory in the header and check that drop down contains options")
    @Then("Home Page navigation Service dropdown contains options")
    public void checkHeaderDropdown() {
        dropdownName.shouldHave(text(SERVICE.title)).click();
        dropdownOptions.shouldHaveSize(9);
        dropdownOptions.shouldHave(CollectionCondition.texts(SUPPORT.option, ServiceOptions.DATES.option, SEARCH.option,
                COMPLEX_TABLE.option, SIMPLE_TABLE.option, USER_TABLE.option,
                TABLE_WITH_PAGES.option, DIFFERENT_ELEMENTS.option, PERFORMANCE.option));
    }

    @Step("Click on Service subcategory in the left section and check that drop down contains options")
    @Then("Home Page side bar dropdown contains options")
    public void checkLeftSectionOptions() {
        leftSectionSupport.isDisplayed();
        leftSectionServiceOption.click();
        leftSectionElements.shouldHaveSize(9);
        leftSectionElements.shouldHave(CollectionCondition.texts(LeftSectionServiceOptions.SUPPORT.option,
                LeftSectionServiceOptions.DATES.option, LeftSectionServiceOptions.COMPLEX_TABLE.option,
                LeftSectionServiceOptions.SIMPLE_TABLE.option, LeftSectionServiceOptions.SEARCH.option,
                LeftSectionServiceOptions.USER_TABLE.option, LeftSectionServiceOptions.TABLE_WITH_PAGES.option,
                LeftSectionServiceOptions.DIIFFERENT_ELEMENTS.option, LeftSectionServiceOptions.PERFORMANCE.option));
    }

    @Step("Open Different Elements page")
    @Then("I'm on (.+) page")
    public void openDifferentElementsPage(String value) {
        DifferentElementsCucumber difEl = DifferentElementsCucumber.valueOf(value);
        dropdownName.shouldHave(text(SectionTitles.SERVICE.toString())).click();
        dropdownOptions.findBy(text(difEl.differentElements)).click();
        $(byTitle(DifferentElementsCucumber.DIFFERENT_ELEMENTS.toString())).isDisplayed();
    }

    @Step("Open User Table page")
    @And("I open User Table Page through the header menu Service -> (.+)")
    public void openUserTablePage(String value) {
        UserTableCucumber usT = UserTableCucumber.valueOf(value);
        dropdownName.shouldHave(text(SectionTitles.SERVICE.toString())).click();
        dropdownOptions.findBy(text(usT.userTable)).click();
        $(byTitle(UserTableCucumber.USER_TABLE.toString())).isDisplayed();
    }

    @Step("Check interface on Different elements page, it contains all needed elements")
    @Then("Different Elements page contains checkboxes, radiobuttons, dropdown menu, buttons, left and right sections")
    public void checkElementsOnPage() {
        labelCheckboxes.shouldHaveSize(4);
        labelRadiobuttons.shouldHaveSize(4);
        selectedColorField.isDisplayed();
        defaultButton.isDisplayed();
        button.isDisplayed();
    }

    @Step("Assert that there is Right Section")
    @Then("Different Elements page contains right Section")
    public void checkRightSection() {
        rightSection.isDisplayed();
    }

    @Step("Assert that there is Left Section")
    @Then("Different Elements page contains left Section")
    public void checkLeftSection() {
        navigationSidebar.isDisplayed();
    }

    @Step("Select checkboxes")
    @When("Checkbox is clicked")
    public void selectCheckbox(DataTable checkboxes) {
        List<String> checkboxesToSelect = checkboxes.asList();
        labelCheckboxes.findBy(Condition.text(checkboxesToSelect.get(0))).click();
        labelCheckboxes.findBy(Condition.text(checkboxesToSelect.get(1))).click();
    }

    @Step("Assert that for checkbox there is an individual log row and value is corresponded to the status of checkbox")
    @Then("I check log is (.+) for the (.+)")
    public void checkLogForCheckboxex(String status, String element) {
        ElementsCucumber el = ElementsCucumber.valueOf(element);
        logs.get(1).shouldHave(text(el.water + ": condition changed to " + status));
        logs.get(0).shouldHave(text(el.wind + ": condition changed to " + status));
    }

    @Step("Select radiobuttons")
    @When("Radiobutton (.+) is clicked")
    public void selectRadiobutton(String radio) {
        MetalsCucumber metal = MetalsCucumber.valueOf(radio);
        labelRadiobuttons.findBy(Condition.text(metal.selen)).click();
    }

    @Step("Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton")
    @Then("I check log for radiobutton (.+)")
    public void checkLogRadioButton(String radio) {
        MetalsCucumber metal = MetalsCucumber.valueOf(radio);
        logs.get(0).shouldHave(text("metal: value changed to " + metal.selen));
    }

    @Step("Select in dropdown and check log row")
    @When("(.+) color is selected from the dropdown list")
    public void selectDropdownOption(String color) {
        ColorsCucumber clr = ColorsCucumber.valueOf(color);
        selectedColorField.click();
        colorDropdown.findBy(text(clr.yellow)).click();
    }

    @Step("Assert log row")
    @Then("I check log for dropdown color (.+)")
    public void checkLogForDropdown(String color) {
        ColorsCucumber clr = ColorsCucumber.valueOf(color);
        logs.get(0).shouldHave(text("Colors: value changed to " + clr.yellow));
    }


    //    @Step ("Check logs have appropriate message rows")
//    @Then("I check log for left point: (\\d+) log with (\\d+) value")
//    public void checkLogsForLeftPoint(int logNumber, int expectedPosition) {
//        logs.get(logNumber - 1).shouldHave(text("Range 2(" + FROM.log + "):" + expectedPosition + " link clicked"));
//    }
//
//    @And ("I check log for right point: (\\d+) log with (\\d+) value")
//    public void checkLogsForRightPoint(int logNumber, int expectedPosition) {
//        logs.get(logNumber - 1).shouldHave(text("Range 2(" + TO.log + "):" + expectedPosition + " link clicked"));
//    }


}


//    @Step("Open Data Page")
//@When("I open the Data Page")
//public void openPageFromHeaderMenu() {
//    dropdownName.shouldHave(text(SERVICE.title)).click();
//    dropdownOptions.findBy(text(DATES.option)).click();
//    $(byTitle(PageTitles.DATES.title)).isDisplayed();
//}
//
//    @Step ("Check browser title is Data Page")
//    @Then("The browser title is Data Page")
//    public void checkBrowserTitleIsDataPage() {
//        $(byTitle("Data Page".toLowerCase())).isDisplayed();
//    }
//
//    @Step ("Move points: left to left side, right to right side")
//    @When("I move left point to (\\d+) position")
//    public void moveLeftPoint(int expectedPositionLeft) {
//        SelenideElement leftPoint = handles.get(0);
//        int size = slider.getSize().width;
//        int actualPosition = Integer.parseInt(leftPoint.getText()) + 1;
//        if (actualPosition < expectedPositionLeft) {
//            actions().dragAndDropBy(leftPoint, ((expectedPositionLeft - actualPosition) * size) / 100, 0).perform();
//        } else {
//            actions().dragAndDropBy(leftPoint, (((actualPosition - expectedPositionLeft) * size) * -1) / 100, 0).perform();
//        }
//    }
//
//    @And("I move right point to (\\d+) position")
//    public void moveRightPoint(int expectedPositionRight) {
//        SelenideElement rightPoint = handles.get(1);
//        int size = slider.getSize().width;
//        int actualPosition = Integer.parseInt(rightPoint.getText()) + 1;
//        if (actualPosition < expectedPositionRight) {
//            actions().dragAndDropBy(rightPoint, ((expectedPositionRight - actualPosition) * size) / 100, 0).perform();
//        } else {
//            actions().dragAndDropBy(rightPoint, (((actualPosition - expectedPositionRight) * size) * -1) / 100, 0).perform();
//        }
//    }
//
//    @Step ("Check logs have appropriate message rows")
//    @Then("I check log for left point: (\\d+) log with (\\d+) value")
//    public void checkLogsForLeftPoint(int logNumber, int expectedPosition) {
//        logs.get(logNumber - 1).shouldHave(text("Range 2(" + FROM.log + "):" + expectedPosition + " link clicked"));
//    }
//
//    @And ("I check log for right point: (\\d+) log with (\\d+) value")
//    public void checkLogsForRightPoint(int logNumber, int expectedPosition) {
//        logs.get(logNumber - 1).shouldHave(text("Range 2(" + TO.log + "):" + expectedPosition + " link clicked"));
//    }
