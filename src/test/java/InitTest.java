import enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InitTest {
    protected WebDriver driver;
    protected static final Logger logger = LogManager.getLogger(InitTest.class);

    @BeforeMethod
    public void setUp() {
        logger.info("Setting up WebDriver for the test.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed.");
        }
    }

    protected void initializeDriver(BrowserType browser) {
        if (browser == BrowserType.CHROME) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser == BrowserType.EDGE) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        logger.info(browser + " browser launched.");
    }
}
