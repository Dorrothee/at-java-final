import enums.BrowserType;
import org.example.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends InitTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"", "", "Epic sadface: Username is required", BrowserType.CHROME},   //UC-1
                {"user", "", "Epic sadface: Password is required", BrowserType.EDGE},   //UC-2
                {"visual_user", "secret_sauce", "", BrowserType.CHROME} //UC-3 (no error message expected)
        };
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, String expectedError, BrowserType browser) {
        initializeDriver(browser);
        driver.get("https://www.saucedemo.com/");
        logger.info("Navigated to SauceDemo website.");

        LoginPage loginPage = new LoginPage(driver);
        logger.info("Testing with username: " + username + ", password: " + password);

        loginPage.login(username, password);

        if (!expectedError.isEmpty()) {
            String actualError = loginPage.getErrorMessage();
            logger.info("Expected error: " + expectedError + ", Actual error: " + actualError);
            Assert.assertEquals(actualError, expectedError, "Error message mismatch");
        } else {
            Assert.assertEquals(driver.getTitle(), "Swag Labs", "Dashboard title mismatch");
            logger.info("Successfully logged in with valid credentials.");
        }
    }
}
