package anna.kliuieva.petproject;

import io.appium.java_client.android.AndroidDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverProvider {

    private static final Logger LOG = LoggerFactory.getLogger(DriverProvider.class);
    public static final DriverProvider INSTANCE = new DriverProvider();
    private ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<>();

    public AndroidDriver getDriverInstance() {
        if (DRIVER.get() == null) {
            AndroidDriver driver = null;
            try {
                if(System.getProperty("test.config") == "android-kobiton"){
                    driver = new AndroidDriver(new URL(Constants.APPIUM_URL_KOBITON), Configuration.getDesiredCapabilities());
                } else{
                    driver = new AndroidDriver( Configuration.getDesiredCapabilities());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
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
