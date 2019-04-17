package pageObjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import enums.Configuration;
import enums.LeftSectionServiceOptions;
import enums.PageTitles;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static enums.SectionTitles.SERVICE;
import static enums.ServiceOptions.*;

public class SelenideHomePage {

    @FindBy(css = ".profile-photo")
    private SelenideElement profileButton;

    @FindBy(css = "[id = 'name']")
    private SelenideElement login;

    @FindBy(css = "[id = 'password']")
    private SelenideElement password;

    @FindBy(id = "login-button")
    private SelenideElement loginButton;

    @FindBy(id = "user-name")
    private SelenideElement userName;

    @FindBy(css = "[role = 'navigation'] > ul.m-l8 > li")
    private ElementsCollection elements;

    @FindBy(css = ".benefit-icon")
    private ElementsCollection images;

    @FindBy(css = ".benefit-txt")
    private ElementsCollection texts;

    @FindBy(css = "h3[name=main-title]")
    private SelenideElement mainHeader;

    @FindBy(css = "p[name=jdi-text]")
    private SelenideElement jdiHeader;

    @FindBy(css = ".search")
    private SelenideElement search;

    @FindBy(css = ".search-field > input")
    private SelenideElement searchInput;

    @FindBy(css = ".epam-logo")
    private SelenideElement epamLogo;

    @FindBy(linkText = "JDI GITHUB")
    private SelenideElement subHeader;

    @FindBy(css = "div[name = navigation-sidebar]")
    private SelenideElement leftSection;

    @FindBy(css = "footer")
    private SelenideElement footer;

    @FindBy(css = ".dropdown-toggle")
    private SelenideElement dropdownName;

    @FindBy(css = ".dropdown-toggle")
    private SelenideElement serviceDropdown;

    @FindBy(css = ".dropdown-menu > li")
    private ElementsCollection dropdownOptions;

    @FindBy(css = "li.menu-title[index = '3']")
    private SelenideElement leftSectionServiceOption;

    @FindBy(css = "li.menu-title[index = '3'] > ul > li")
    private ElementsCollection leftSectionElements;

    @FindBy(css = "p > span")
    private SelenideElement leftSectionSupport;

    @Step("Open test site by URL")
    public void openBrowser() {
        open("https://epam.github.io/JDI/");
    }

    @Step("Assert Browser title")
    public void assertTitle(Configuration title) {
        $(byTitle(title.toString())).isDisplayed();
    }

    @Step("Perform login")
    public void login(Configuration name, Configuration pass) {
        profileButton.click();
        login.sendKeys(name.toString());
        password.sendKeys(pass.toString());
        loginButton.click();
    }

    @Step
    public void assertUserName(String name) {
        userName.shouldBe(Condition.visible);
        userName.shouldBe(text(name));
    }

    @Step
    public void checkHeaderDropdown() {
        dropdownName.shouldHave(text(SERVICE.title)).click();
        dropdownOptions.shouldHaveSize(9);
        dropdownOptions.shouldHave(CollectionCondition.texts(SUPPORT.option, DATES.option, SEARCH.option,
                COMPLEX_TABLE.option, SIMPLE_TABLE.option, USER_TABLE.option,
                TABLE_WITH_PAGES.option, DIFFERENT_ELEMENTS.option, PERFORMANCE.option));
    }

    @Step
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

    @Step
    public void openPageFromHeaderMenu(PageTitles pageName) {
        dropdownName.shouldHave(text(SERVICE.title)).click();
        dropdownOptions.findBy(text(pageName.toString())).click();
        $(byTitle(pageName.toString())).isDisplayed();
    }
}
