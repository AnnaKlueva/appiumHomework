package anna.kliuieva.petproject.pages;

import anna.kliuieva.petproject.utils.Waiters;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LeftMenuPage extends AbstractPage {
    @AndroidFindBy(id = "action_bar_title")
    private WebElement actionBar;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Temperature']")
    private WebElement temperatureService;

    public LeftMenuPage() {
        super();
        waitForLoad();
    }

    @Override
    public void waitForLoad() {
        if (!isLoaded()) {
            Waiters.waitForAppear(getLoadableElement());
            removeCommercialWindow();
        }
    }

    @Override
    public boolean isLoaded() {
        return Waiters.isElementDisplayed(getLoadableElement());
    }

    @Override
    public WebElement getLoadableElement() {
        return actionBar;
    }

    public String getActionBarTitle() {
        return actionBar.getText();
    }

    public TemperatureServicePage selectTemperatureService() {
        temperatureService.click();
        return new TemperatureServicePage();
    }
}
