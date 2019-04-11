package pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.actions;

/* TODO Basically, this PO should has two methods:
1. setSlider(int, int)
2. checkSliderLog(int, int)
This improvement makes test quite readable and shorter, besides it helps you to avoid code duplication.
*/
public class SelenideDatesPage {

    @FindBy(css = "div[name=navigation-sidebar]")
    private SelenideElement navigationSidebar;

    @FindBy(css = "p > span")
    private ElementsCollection leftSectionElements;

    @FindBy(css = "div[name=log-sidebar]")
    private SelenideElement rightSection;

    @FindBy(css = "ul.panel-body-list.logs > li")
    private List<SelenideElement> logs;

    @FindBy(css = "a.ui-slider-handle")
    private ElementsCollection handles;

    @FindBy(css = ".ui-slider")
    private SelenideElement slider;

    @Step
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

    @Step
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

    // TODO In this case, the log rows order doesn't really matter,
    // anyway pay attention on arguments naming, logNumber represent the position of the log's row...
    @Step
    public void checkLogsForRange(int logNumber, String type, int expectedPosition) {
        logs.get(logNumber - 1).shouldHave(text("Range 2(" + type + "):" + expectedPosition + " link clicked"));
    }
}
