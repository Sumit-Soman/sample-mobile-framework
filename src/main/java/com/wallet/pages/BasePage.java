package com.wallet.pages;

import com.wallet.base.Actions;
import com.wallet.base.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected AndroidDriver androidDriver;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    private static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage() {
        this.driver = DriverManager.getDriver();
        if (driver instanceof AndroidDriver) {
            this.androidDriver = (AndroidDriver) driver;
        }
        this.actions = new Actions();
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }
}
