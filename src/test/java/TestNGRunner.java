
import anna.kliuieva.petproject.DriverProvider;
import anna.kliuieva.petproject.pages.LeftMenuPage;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class TestNGRunner {

    protected LeftMenuPage menuPage;

    @BeforeMethod
    public void openApp() {
        menuPage = new LeftMenuPage();
    }

    @AfterMethod(alwaysRun = true)
    public void closeApp(ITestResult result) {
        if (!result.isSuccess()) {
            makeScreenshot();
        }
        DriverProvider.INSTANCE.getDriverInstance().resetApp();
        DriverProvider.INSTANCE.removeDriver();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] makeScreenshot() {
        return (DriverProvider.INSTANCE.getDriverInstance()).getScreenshotAs(OutputType.BYTES);
    }

}
