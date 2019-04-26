package pageObjects.cucumber;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.SectionTitles;
import enums.cucumber.DifferentElementsCucumber;
import enums.cucumber.UserTableCucumber;
import io.cucumber.datatable.DataTable;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.id;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static enums.Configuration.NAME;
import static enums.LeftSectionServiceOptions.USER_TABLE;
import static enums.SectionTitles.SERVICE;
import static enums.UsersInTable.ROMAN;
import static enums.UsersInTable.SERGEY_IVAN;

public class SelenideUserTableCucumber {

    public SelenideUserTableCucumber() {
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

    @FindBy(css = "ul.panel-body-list.logs > li")
    private List<SelenideElement> logs;

    @FindBy(xpath = "//tbody/tr//td[1]")
    private List<WebElement> numbers;

    @FindBy(xpath = "//tbody/tr//td[3]/a")
    private List<WebElement> users;

    @FindBy(css = ".user-descr span")
    private List<WebElement> descriptions;

    @FindBy(css = "input[type=checkbox]")
    private ElementsCollection vipCheckboxes;

    @FindBy(xpath = "//a[contains(text(), 'Roman')]/ancestor::tr//select/option")
    private List<WebElement> options;

    @FindBy(css = ".dropdown-toggle")
    private SelenideElement serviceDropdown;

    @FindBy(css = ".dropdown-menu > li")
    private ElementsCollection dropdownOptions;

    @FindBy(css = ".dropdown-toggle")
    private SelenideElement dropdownName;

    List<String> numbersList = new LinkedList<>();
    List<String> usersList = new LinkedList<>();
    List<String> descList = new LinkedList<>();
    List<String> dropdownList = new LinkedList<>();

    @Step("Check number and user columns have valid values")
    @And("I check Number and User columns of Users table")
    public void getNamesAndNumbers() {
        for (WebElement number : numbers) {
            numbersList.add(number.getText());
        }
        for (WebElement user : users) {
            usersList.add(user.getText());
        }
    }

    @Then("User table contain correct values for numbers and users")
    public void checkNumberAndNames(DataTable usersWithNumbers) {
        List<String> expectedNumber = new LinkedList<>();
        List<String> expectedUser = new LinkedList<>();
        Map<String, String> userRow = usersWithNumbers.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : userRow.entrySet()) {
            expectedNumber.add(entry.getKey());
            expectedUser.add(entry.getValue());
        }
        Assert.assertEquals(numbersList, expectedNumber);
        Assert.assertEquals(usersList, expectedUser);
    }

    @Step("Check description column has valid values")
    @When("I check Description column of Users table")
    public void getDescription() {
        for (WebElement description : descriptions
                ) {
            descList.add(description.getText());
        }
    }

    @Then("All cells of 'Description' column contains text")
    public void checkDescriptionContainsTexts(DataTable descWithNumbers) {
        List<String> expectedNumber = new LinkedList<>();
        List<String> expectedDescription = new LinkedList<>();
        Map<String, String> userRow = descWithNumbers.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : userRow.entrySet()) {
            expectedNumber.add(entry.getKey());
            expectedDescription.add(entry.getValue());
        }
    }

    @Step("Set Vip status to a user")
    @When("I set 'vip' status to (.+)")
    public void setStatus(String name) {
        if (name.equals(SERGEY_IVAN.user)) {
            vipCheckboxes.findBy(id("ivan")).click();
        }
    }

    @Step("Check logs for vip status")
    @Then("'Log' section shows a (\\d+) log row in format: (.+): condition changed to (.+)")
    public void checkLogForVipStatus(int logRow, String log, String condition) {
        logs.get(logRow - 1).shouldHave(text(log + ": condition changed to " + condition));
    }

    @Step("Check dropdown options have valid values")
    @When("I click on dropdown in column Type for user (.+)")
    public void getDropdownValues(String user) {
        // TODO What are you going to do in case if you have lots of users ?
        // TODO This method should be parametrised by enum as well
        if (user.equals(ROMAN.user)) {
            $(By.xpath("//a[contains(text(), 'Roman')]/ancestor::tr//select")).click();
            for (WebElement option : options) {
                dropdownList.add(option.getText());
            }
        }
    }

    @Then("Droplist contains values")
    public void droplistContainsValues(DataTable options) {
        List<String> expectedOptions = options.asList();
        Assert.assertEquals(dropdownList, expectedOptions);
    }
}