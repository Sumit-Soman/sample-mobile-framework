package com.wallet.base;

import com.wallet.utils.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {

    private static AppiumDriver driver;
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
//    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    public static void initializeDriver() {
        UiAutomator2Options capabilities = new UiAutomator2Options()
            .setPlatformName(ConfigReader.get("platform.name"))
            .setAutomationName(ConfigReader.get("automation.name"))
            .setDeviceName(ConfigReader.get("device.name"))
            .setApp(System.getProperty("user.dir") +"/"+ ConfigReader.get("app.path"))
            .setFullReset(false)
            .setNoReset(true)
            .setAutoGrantPermissions(true);

        try {
            driver = new AppiumDriver(new URL(ConfigReader.get("appium.server.host")+ ":" + ConfigReader.get("appium.server.port") + "/"), capabilities);
            driver.manage().timeouts().implicitlyWait(
                    Duration.ofSeconds(Long.parseLong(ConfigReader.get("implicit.wait"))));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Appium server URL is invalid", e);
        }
    }

    public static AppiumDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Please call initializeDriver first.");
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
