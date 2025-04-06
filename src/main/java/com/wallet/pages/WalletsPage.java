package com.wallet.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class WalletsPage extends BasePage{

    private static final Logger logger = LogManager.getLogger(CreateNewWalletPage.class);

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"addWalletIconButton\")")
    private WebElement addNewWalletBtn;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"CreateNewWallet\")")
    private WebElement createNewWalletBtn;
    public void addNewWallet() {
        addNewWalletBtn.click();
        createNewWalletBtn.click();
    }
}
