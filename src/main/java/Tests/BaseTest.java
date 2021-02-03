package Tests;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static WebDriver driver;
    public Logger logger = LoggerFactory.getILoggerFactory()
            .getLogger(BaseTest.class.getName());

    @BeforeClass
    public static void setTests() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--kiosk"); No need to run full screen
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }


    @AfterClass
    public static void tearDown() {
        driver.close();
    }
}
