package com.wallet.tests;

import com.wallet.base.DriverManager;
import com.wallet.pages.CreateNewWalletPage;
import com.wallet.pages.MainWalletPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.ByteArrayInputStream;

public class BaseTest {

    CreateNewWalletPage createNewWalletPage;
    MainWalletPage mainWalletPage;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        DriverManager.initializeDriver();
        createNewWalletPage = new CreateNewWalletPage();
        mainWalletPage =  new MainWalletPage();
    }

    @AfterMethod()
    public void takeScreenshotOnFailure(ITestResult result) {
        if(!result.isSuccess()) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot - " + result.getMethod().getMethodName(), new ByteArrayInputStream(screenshot));
        }
    }

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] attachScreenshot() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @AfterTest
    public void tearDown() {
        DriverManager.quitDriver();
//        DriverUtils.quitDriver();
    }
}
