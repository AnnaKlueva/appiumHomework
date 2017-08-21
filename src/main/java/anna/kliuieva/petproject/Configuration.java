package anna.kliuieva.petproject;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class Configuration {

    private static final String DEVICE_PROPERTY_NAME = "test.device";
    private static final Logger LOG = LoggerFactory.getLogger(Configuration.class);
    static Properties properties;

    private Configuration() {
    }

    public static String getDeviceName() {
        return getFileProperty(DEVICE_PROPERTY_NAME);
    }

    public static String getAppPath() {
        File app = new File(Constants.APP_PATH);
        return app.getAbsolutePath();
    }

    private static String getFileProperty(final String key) {
        return properties.getProperty(key);
    }

    public static DesiredCapabilities getDesiredCapabilities() {
        properties = new Properties();
        loadProperties();

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getDeviceName());
        capabilities.setCapability(MobileCapabilityType.APP, getAppPath());
        return capabilities;
    }

    private static void loadProperties() {
        try (InputStream in = Configuration.class.getClassLoader().getResourceAsStream(Constants.PROPERTY_FILE)) {
            properties.load(in);
        } catch (final IOException e) {
            LOG.error("Failed to loadProperties build properties file.", e);
            throw new RuntimeException("Failed to loadProperties build properties file.");
        }
    }
}
