package pageObjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static enums.Configuration.userOne.USER_ONE;
import static enums.Texts.leftSectionServiceOptions.LEFT_SECTION_SERVICE_OPTIONS;
import static enums.Texts.sectionTitles.SECTION_TITLES;
import static enums.Texts.serviceOptions.SERVICE_OPTIONS;

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

    public void openBrowser() {
        open("https://epam.github.io/JDI/");
    }

    public void assertTitle() {
        $(byTitle("Home Page")).isDisplayed();
    }

    public void login(String name, String pass) {
        profileButton.click();
        login.sendKeys(name);
        password.sendKeys(pass);
        loginButton.click();
    }

    public void assertUserName() {
        userName.shouldBe(Condition.visible);
        userName.shouldBe(text(USER_ONE.name));
    }

    public void checkHeaderDropdown() {
        dropdownName.shouldHave(text(SECTION_TITLES.service)).click();
        dropdownOptions.shouldHaveSize(8);
        dropdownOptions.shouldHave(CollectionCondition.texts(SERVICE_OPTIONS.support, SERVICE_OPTIONS.dates,
                SERVICE_OPTIONS.complexTable, SERVICE_OPTIONS.simpleTable, SERVICE_OPTIONS.userTable,
                SERVICE_OPTIONS.tableWithWages, SERVICE_OPTIONS.differentElements, SERVICE_OPTIONS.performance));
    }

    public void checkLeftSectionOptions() {
        leftSectionSupport.isDisplayed();
        leftSectionServiceOption.click();
        leftSectionElements.shouldHaveSize(8);
        leftSectionElements.shouldHave(CollectionCondition.texts(LEFT_SECTION_SERVICE_OPTIONS.support, LEFT_SECTION_SERVICE_OPTIONS.dates,
                LEFT_SECTION_SERVICE_OPTIONS.complexTable, LEFT_SECTION_SERVICE_OPTIONS.simpleTable, LEFT_SECTION_SERVICE_OPTIONS.userTable,
                LEFT_SECTION_SERVICE_OPTIONS.tableWithWages, LEFT_SECTION_SERVICE_OPTIONS.differentElements, LEFT_SECTION_SERVICE_OPTIONS.performance));
    }

    public void openPageFromHeaderMenu(String pageName) {
        dropdownName.shouldHave(text(SECTION_TITLES.service)).click();
        dropdownOptions.findBy(text(pageName)).click();
        $(byTitle("Different Elements")).isDisplayed();
    }
}
