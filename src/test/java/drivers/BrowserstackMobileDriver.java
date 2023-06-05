package drivers;

import com.codeborne.selenide.WebDriverProvider;
import configs.BrowserstackConfig;
import configs.DeviceAndAppConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

    static Logger logger = LoggerFactory.getLogger(BrowserstackMobileDriver.class);

    static DeviceAndAppConfig deviceAndAppConfig = ConfigFactory.create(DeviceAndAppConfig.class);
    static BrowserstackConfig browserstackConfig = ConfigFactory.create(BrowserstackConfig.class);


    public static URL getAppiumServerUrl() {
        try {
            logger.info("AppiumServerUrl is: " + deviceAndAppConfig.getAppiumServerUrl());
            return new URL(deviceAndAppConfig.getAppiumServerUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        logger.info("System property device is: " + System.getProperty("device"));

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", deviceAndAppConfig.getDeviceName());
        logger.info("Browserstack device: " + deviceAndAppConfig.getDeviceName());
        mutableCapabilities.setCapability("os_version", deviceAndAppConfig.getPlatformVersion());
        logger.info("Browserstack platform version: " + deviceAndAppConfig.getPlatformVersion());

        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", browserstackConfig.getBSUser());
        mutableCapabilities.setCapability("browserstack.key", browserstackConfig.getBSKey());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", browserstackConfig.getBSApp());

        // Set other BrowserStack mutableCapabilities
        mutableCapabilities.setCapability("project", browserstackConfig.getBSProject());
        mutableCapabilities.setCapability("build", browserstackConfig.getBSBuildPrefix());
        mutableCapabilities.setCapability("name", browserstackConfig.getBSName());

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired mutableCapabilities defined above
        return new RemoteWebDriver(getAppiumServerUrl(), mutableCapabilities);
    }
}

