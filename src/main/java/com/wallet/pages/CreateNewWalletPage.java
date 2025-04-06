package com.wallet.pages;

import com.wallet.base.CustomExceptions;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.List;


public class CreateNewWalletPage extends BasePage{

    private static final Logger logger = LogManager.getLogger(CreateNewWalletPage.class);
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(1)")
    private WebElement securityCheckBtn;
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(0)")
    private WebElement createNewWalletBtn;

    @AndroidFindBy(className = "android.widget.TextView")
    private List<WebElement> numberButtons;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id=\"infoDialogContent\"]/android.widget.TextView\n")
    private WebElement closeNotificationDialog;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"secretPhraseCreateButton\")")
    private WebElement createSecretPhaseBtn;

    public void createNewWallet() {
        try{
            checkForSecurityBtn();
            createNewWalletBtn.click();
            enterPasscode("980000");
            enterPasscode("980000");

            closeNotificationDialog.click();
            createSecretPhrase();
            Thread.sleep(5000);

        } catch (Exception e) {
            logger.error("Failed to create new wallet", e);
            throw new CustomExceptions("Failed to create new wallet", e);
        }

    }

    public void createSecretPhrase() {
        createSecretPhaseBtn.click();
    }

    public void enterPasscode(String passcode) {
        try {
            for (char digit : passcode.toCharArray()) {
                WebElement button = null;
                    button = numberButtons.stream()
                            .filter(element -> digit == element.getText().charAt(0))
                            .findFirst()
                            .orElseThrow(() -> new Exception("Button for digit " + digit + " not found"));

                button.click();
            }
        } catch(Exception e) {
            logger.error("Failed to enter passcode: {}", passcode, e);
            throw new CustomExceptions("Failed to enter passcode", e);
        }
    }

    public void checkForSecurityBtn() {
        if(actions.elementIsDisplayed(securityCheckBtn)) {
            securityCheckBtn.click();
        }
    }
}
