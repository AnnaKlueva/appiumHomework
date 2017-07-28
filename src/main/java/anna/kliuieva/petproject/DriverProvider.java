package anna.kliuieva.petproject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverProvider {

    private static final Logger LOG = LoggerFactory.getLogger(DriverProvider.class);
    public static final DriverProvider INSTANCE = new DriverProvider();
    private ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<>();
    private final AppiumDriverLocalService serviceBuilder;

    private DriverProvider() {
        serviceBuilder = new AppiumServiceBuilder().withIPAddress(Constants.IP_ADDRESS)
                .usingPort(Constants.PORT)
                .build();
    }

    public AndroidDriver getDriverInstance() {
        if (DRIVER.get() == null) {
            AndroidDriver driver = new AndroidDriver(serviceBuilder, Configuration.getDesiredCapabilities());
            DRIVER.set(driver);
            LOG.info("WebDriver was created");
        }
        return DRIVER.get();
    }

    public void removeDriver() {
        DRIVER.get().quit();
        DRIVER.remove();
    }
}
