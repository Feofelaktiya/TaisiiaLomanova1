
package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Condition.text;

public class SelenideDifferentElementsPage {

    @FindBy(css = "div[name=navigation-sidebar]")
    private SelenideElement navigationSidebar;

    @FindBy(css = "p > span")
    private ElementsCollection leftSectionElements;

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

    @FindBy(css = "ul.panel-body-list.logs > li")
    private List<SelenideElement> logs;

    public void checkPageContainsElements() {
        labelCheckboxes.shouldHaveSize(4);
        labelRadiobuttons.shouldHaveSize(4);
        selectedColorField.isDisplayed();
        defaultButton.isDisplayed();
        button.isDisplayed();
    }

    public void checkRightSection() {
        rightSection.isDisplayed();
    }

    public void checkLeftSection() {
        navigationSidebar.isDisplayed();
    }

    public void selectCheckbox(String checkbox) {
        labelCheckboxes.findBy(text(checkbox)).click();
    }

    public void checkLogForCheckboxex(int logNumber, String type, boolean checkedStatus) {
        logs.get(logNumber - 1).shouldHave(text(type + ": condition changed to " + checkedStatus));
    }


    public void selectRadiobutton(String radiobutton) {
        labelRadiobuttons.findBy(text(radiobutton)).click();

    }
    public void checkLogForRadiobuttons(int logNumber, String type) {
        logs.get(logNumber - 1).shouldHave(text("metal: value changed to " + type));
    }
    public void selectDropdownOption(String color) {
        selectedColorField.click();
        colorDropdown.findBy(text(color)).click();
    }

    public void checkLogForDropdown(int logNumber, String color) {
        logs.get(logNumber - 1).shouldHave(text("Colors: value changed to " + color));
    }



}