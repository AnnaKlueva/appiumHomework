package anna.kliuieva.petproject.pages;

import anna.kliuieva.petproject.DriverProvider;
import anna.kliuieva.petproject.utils.Waiters;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage implements Page {

    @AndroidFindAll({
            @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Interstitial close button']"),
            @AndroidFindBy(xpath = "//[@resource-id = 'close_button']"),
            @AndroidFindBy(xpath = "//android.view.View[@content-desc='CLOSE']"),
            @AndroidFindBy(xpath = "//[@resource-Id = 'custom_close']")
    })
    private MobileElement closeCommercialButton;

    public AbstractPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public AndroidDriver getDriver() {
        return DriverProvider.INSTANCE.getDriverInstance();
    }

    protected void removeCommercialWindow() {
        if (Waiters.isElementDisplayed(closeCommercialButton)) {
            closeCommercialButton.click();
        }
    }
}
