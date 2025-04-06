# Sample Mobile Framework

## Initial Setup

### Prerequisites
1. Install **Java 11 or higher** on your system.
2. Install **Appium**
3. Install **Android Studio** and start the emulator

### Starting Appium Server
Start the Appium server using the following command:
```bash
appium
```
This will start the server on the default port (4723). Ensure the server is running before executing tests.

### Starting the Emulator
To start the Android emulator, use the following command:
```bash
emulator -avd <emulator_name>
```
Replace `<emulator_name>` with the name of your configured emulator. Ensure that the emulator is running before executing tests.


### Adding the TrustWallet App

Before starting the tests, ensure that the **TrustWallet** app is added to the `apps` folder. The path to the `apps` folder is configured in the `config.properties` file.

To add the app:
1. Download the TrustWallet APK file.
2. Place the APK file in the folder specified by the `appPath` property in `config.properties`.

Ensure the `appPath` points to the correct location of the TrustWallet APK before running the tests.

## Running Tests

### Using Maven
To execute tests using Maven, run the following command:
```bash
mvn clean test
```

### Using TestNG
The project uses `testng.xml` for test execution. To run tests defined in `testng.xml`, use:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Generating Allure Reports

The project supports **Allure** for generating detailed and interactive test reports. To generate the Allure report after running the tests, follow these steps:

1. Ensure that Allure is installed on your system. You can install it using:
    ```bash
    npm install -g allure-commandline --save-dev
    ```

2. After executing the tests, generate the Allure report using the following command:
    ```bash
    allure serve allure-results
    ```
    This will start a local server and open the Allure report in your default browser.

### Cleanup Command

To clean up the project and remove temporary files, use the following Maven command:
```bash
mvn clean
```
This will delete all files generated during the build and test execution, ensuring a fresh start for subsequent runs.

---

## Configuration

The project includes a `config.properties` file that contains all emulator and device details. Update this file with the required configurations, such as:
- Emulator name
- Platform version
- App path

Example:
```properties
# Android Device Configuration
platform.name=Android
automation.name=UiAutomator2
device.name=emulator-5554
platform.version=12.0
app.path=apps/TrustWallet.apk
app.package=com.wallet.crypto.trustapp
```

---

## Project Structure

```
sample-mobile-framework/
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       ├── java/
│       │   ├── tests/
│       │   ├── utils/
│       │   └── base/
│       └── resources/
│           └── config.properties
├── pom.xml
└── testng.xml
```

### Key Components
1. **Base Package**: Contains base classes for initializing drivers and managing test setup.
2. **Tests Package**: Includes all test cases.
3. **Utils Package**: Contains utility classes for common operations.
4. **Resources**: Holds configuration files like `config.properties`.

---

## Test Automation Design

The project follows the **Page Object Model (POM)** design pattern to enhance test maintainability and readability. Each page of the application is represented by a class, and test cases interact with these classes.

### Example Test Code
```java
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
}
```

This design ensures a clear separation between test logic and application-specific details.
