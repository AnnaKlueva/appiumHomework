import anna.kliuieva.petproject.pages.LeftMenuPage;
import anna.kliuieva.petproject.pages.TemperatureServicePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonTest extends TestNGRunner {
    public static final String DEFAULT_BAR_TITLE = "Area";
    public static final String SELECTED_SERVICE_NAME = "Temperature";

    @Test(groups = "common")
    public void verifyThatActionBarTitleIsAreaByDefault() {
        String actualBarTitle = menuPage.getActionBarTitle();
        Assert.assertEquals(actualBarTitle, DEFAULT_BAR_TITLE, "Action bar title isn't the same as default");
    }

    @Test(groups = "common")
    public void verifyThatOnServicePageActionBarTitleHasTheSameNameAsService() {
        TemperatureServicePage temperatureServicePage = menuPage.selectTemperatureService();
        String actualBarTitle = temperatureServicePage.getActionBarTitle();
        Assert.assertEquals(actualBarTitle, SELECTED_SERVICE_NAME, "Action bar title has NOT the same as name as service name");
    }

    @Test(groups = "common")
    public void verifyThatActionBarTitleHasServiceNameAfterClickingHomeButtonOnServicePage() {
        TemperatureServicePage temperatureServicePage = menuPage.selectTemperatureService();
        LeftMenuPage menuPage = temperatureServicePage.clickHomeButton();
        String actualBarTitle = menuPage.getActionBarTitle();
        Assert.assertEquals(actualBarTitle, SELECTED_SERVICE_NAME, "After going back from service name " +
                "the action bar title has NOT the same as name as service name on menu page ");
    }
}
