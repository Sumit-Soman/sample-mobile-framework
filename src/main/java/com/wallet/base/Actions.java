package com.wallet.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Actions {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(Actions.class);

    public Actions() {

        this.driver = DriverManager.getDriver();
        this.wait =  DriverManager.getWait();
    }
    public boolean elementIsDisplayed(WebElement locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            logger.info("Clicked on element: {}", element);
        } catch (Exception e) {
            logger.error("Failed to click on element: {}", element, e);
            throw new CustomExceptions("Click failed", e);
        }
    }

    public boolean isDisplayed(WebElement element) {
        try {
            boolean visible = wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
            logger.info("Element visibility: {}", visible);
            return visible;
        } catch (Exception e) {
            logger.warn("Element not visible: {}", element);
            return false;
        }
    }

    public String getText(WebElement element) {
        try {
            String text = wait.until(ExpectedConditions.visibilityOf(element)).getText();
            logger.info("Text fetched from element: {}", text);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from element: {}", element, e);
            throw new CustomExceptions("Text fetch failed", e);
        }
    }

    public void sendKeys(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
            logger.info("Sent keys '{}' to element: {}", text, element);
        } catch (Exception e) {
            logger.error("Failed to send keys to element: {}", element, e);
            throw new CustomExceptions("sendKeys failed", e);
        }
    }
}
