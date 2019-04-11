package pageObjects.cucumber;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.PageTitles;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.*;
import static enums.Configuration.NAME;
import static enums.LeftSectionServiceOptions.DATES;
import static enums.RangeLogs.FROM;
import static enums.RangeLogs.TO;
import static enums.SectionTitles.SERVICE;

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

    @FindBy(css = "a.ui-slider-handle")
    private ElementsCollection handles;

    @FindBy(css = ".ui-slider")
    private SelenideElement slider;

    @Step("Open site")
    @Given("I am on the Home Page")
    public void openHomePage() {
        open("https://epam.github.io/JDI/");
    }

    @Step("Check home title")
    @Then("The browser title is Home Page")
    public void checkBrowserTitleIsHomePage() {
        $(byTitle("Home Page")).isDisplayed();
    }

    @Step("Login")
    @When("I login as user (.+) with password (.+)")
    public void login(String login, String password) {
        userIcon.click();
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        submitButton.click();
    }

    @Step ("Check user name")
    @Then("The user name is displayed")
    public void checkUserName() {
        userName.isDisplayed();
        userName.shouldHave(text(NAME.text));
    }

    @Step("Open Data Page")
    @When("I open the Data Page")
    public void openPageFromHeaderMenu() {
        dropdownName.shouldHave(text(SERVICE.title)).click();
        dropdownOptions.findBy(text(DATES.option)).click();
        $(byTitle(PageTitles.DATES.title)).isDisplayed();
    }

    @Step ("Check browser title is Data Page")
    @Then("The browser title is Data Page")
    public void checkBrowserTitleIsDataPage() {
        $(byTitle("Data Page".toLowerCase())).isDisplayed();
    }

    @Step ("Move points: left to left side, right to right side")
    @When("I move left point to (\\d+) position")
    public void moveLeftPoint(int expectedPositionLeft) {
        SelenideElement leftPoint = handles.get(0);
        int size = slider.getSize().width;
        int actualPosition = Integer.parseInt(leftPoint.getText()) + 1;
        if (actualPosition < expectedPositionLeft) {
            actions().dragAndDropBy(leftPoint, ((expectedPositionLeft - actualPosition) * size) / 100, 0).perform();
        } else {
            actions().dragAndDropBy(leftPoint, (((actualPosition - expectedPositionLeft) * size) * -1) / 100, 0).perform();
        }
    }

    @And("I move right point to (\\d+) position")
    public void moveRightPoint(int expectedPositionRight) {
        SelenideElement rightPoint = handles.get(1);
        int size = slider.getSize().width;
        int actualPosition = Integer.parseInt(rightPoint.getText()) + 1;
        if (actualPosition < expectedPositionRight) {
            actions().dragAndDropBy(rightPoint, ((expectedPositionRight - actualPosition) * size) / 100, 0).perform();
        } else {
            actions().dragAndDropBy(rightPoint, (((actualPosition - expectedPositionRight) * size) * -1) / 100, 0).perform();
        }
    }

    @Step ("Check logs have appropriate message rows")
    @Then("I check log for left point: (\\d+) log with (\\d+) value")
    public void checkLogsForLeftPoint(int logNumber, int expectedPosition) {
        logs.get(logNumber - 1).shouldHave(text("Range 2(" + FROM.log + "):" + expectedPosition + " link clicked"));
    }

    @And ("I check log for right point: (\\d+) log with (\\d+) value")
    public void checkLogsForRightPoint(int logNumber, int expectedPosition) {
        logs.get(logNumber - 1).shouldHave(text("Range 2(" + TO.log + "):" + expectedPosition + " link clicked"));
    }
}