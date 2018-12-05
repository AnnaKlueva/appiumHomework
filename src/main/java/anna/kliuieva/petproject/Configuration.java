package anna.kliuieva.petproject;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class Configuration {

    private static final String DEVICE_PROPERTY_NAME = "test.device";
    private static final String DEVICE_PLATFORM_VERSION = "platformVersion";
    private static final String DEVICE_PLATFORM_NAME = "test.profile";
    private static final Logger LOG = LoggerFactory.getLogger(Configuration.class);
    private static ThreadLocal<Properties> PROPERTIES = new ThreadLocal<>();

    private Configuration() {
    }

    public static String getDeviceName() {
        return getFileProperty(DEVICE_PROPERTY_NAME);
    }

    public static String getAppPath() {
        String pathToRemoteApp;
        if(System.getProperty("test.config") == "kobiton"){
            pathToRemoteApp = System.getProperty("kobitonRemotePath");
        }else{
            File app = new File(Constants.APP_PATH);
            pathToRemoteApp = app.getAbsolutePath();
        }
        return pathToRemoteApp;
    }

    public static DesiredCapabilities getDesiredCapabilities() {
        loadProperties();

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APP, getAppPath());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, getPlatformName());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getDeviceName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, getPlatformVersion());

        return capabilities;
    }

    static Properties loadProperties() {
        if (PROPERTIES.get() == null) {
            Properties properties = new Properties();
            try (InputStream in = Configuration.class.getClassLoader().getResourceAsStream(Constants.PROPERTY_FILE)) {
                properties.load(in);
            } catch (final IOException e) {
                LOG.error("Failed to loadProperties build properties file.", e);
                throw new RuntimeException("Failed to loadProperties build properties file.");
            }
            PROPERTIES.set(properties);
        }
        return PROPERTIES.get();
    }

    public static String getPlatformVersion() {
       return getFileProperty(DEVICE_PLATFORM_VERSION);
    }

    private static String getPlatformName() {
        return getFileProperty(DEVICE_PLATFORM_NAME);
    }

    static String getFileProperty(final String key) {
        return loadProperties().getProperty(key);
    }

}
