package com.wallet.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class MainWalletPage extends BasePage {

    WalletsPage walletsPage = new WalletsPage();
    CreateNewWalletPage createNewWalletPage = new CreateNewWalletPage();

    private static final Logger logger = LogManager.getLogger(CreateNewWalletPage.class);
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Buy Crypto\")")
    private WebElement buyCryptoBtn;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(1)")
    private WebElement depositCrypto;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\")")
    private WebElement searchPanel;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"asset_row\").instance(0)")
    private WebElement searchResult1;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"buyProviderButton\")")
    private WebElement buyWithCreditCardBtn;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Back\")")
    private WebElement backBtn;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"HomeSellButton\")")
    private WebElement sellBtn;


    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"cryptoAmountInput\")")
    private WebElement sellAmountTxt;


    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"errorMessage\")")
    private WebElement errorMsg;


    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"itemIcon\")")
    private WebElement walletDropDown;

    public boolean isWallet1Created() {
        return actions.elementIsDisplayed(buyCryptoBtn);
    }

    public boolean buyCrypto(String currency, String amount) {
        buyCryptoBtn.click();
        searchCrypto(currency);
        return actions.elementIsDisplayed(buyWithCreditCardBtn);
    }

    public String sellCrypto(String currency, String amount) {
        checkAndMoveToHomePage();
        sellBtn.click();
        searchCrypto(currency);

        sellAmountTxt.sendKeys(amount);
        return errorMsg.getText();
    }

    public void searchCrypto(String crypto) {
        searchPanel.sendKeys(crypto);
        searchResult1.click();
    }

    public boolean addNewWallet(String crypto, String amount) {
        checkAndMoveToHomePage();
        walletDropDown.click();
        walletsPage.addNewWallet();
        createNewWalletPage.createSecretPhrase();
        return buyCrypto(crypto, amount);
    }

    public void checkAndMoveToHomePage() {
        if(actions.elementIsDisplayed(backBtn)) {
            backBtn.click();
            backBtn.click();
        }
    }

}
