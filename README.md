# Sample Mobile Framework

## Initial Setup

### Prerequisites
1. Install **Java 11 or higher** on your system.
2. Install **Maven** for dependency management.
3. Install **Appium** globally using npm:

### Starting Appium Server
Start the Appium server using the following command:
```bash
appium
```
This will start the server on the default port (4723). Ensure the server is running before executing tests.

---

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

---

## Configuration

The project includes a `config.properties` file that contains all emulator and device details. Update this file with the required configurations, such as:
- Emulator name
- Platform version
- App path

Example:
```properties
deviceName=Pixel_4_API_30
platformVersion=11.0
appPath=/path/to/your/app.apk
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
