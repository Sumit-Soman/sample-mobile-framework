package com.wallet.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.NORMAL;

@Epic("Wallet App Testing")
@Feature("Create Wallet")
public class CreateWalletTest extends BaseTest {

    @Test(description = "Create a new wallet and buy BTC")
    @Description("Create a new wallet and buy BTC")
    @Severity(CRITICAL)
    public void CreateAndBuyBTC() {
        createNewWalletPage.createNewWallet();
        Assert.assertTrue(mainWalletPage.buyCrypto("BTC", "100"));
    }

    @Test(description = "Show error msg when selling amount of BTC exceeds the Wallet")
    @Description("Show error msg when selling amount of BTC exceeds the Wallet")
    @Severity(CRITICAL)
    public void SellBTC() {
        Assert.assertEquals(mainWalletPage.sellCrypto("BTC", "100"), "Exceeds balance");
    }

    @Test(description = "Successfully create new wallet with ETH")
    @Description("Successfully create new wallet with ETH")
    @Severity(NORMAL)
    public void CreateNewWallet() {
        Assert.assertTrue(mainWalletPage.addNewWallet("ETH", "100"));
    }
}