package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

abstract public class BaseTest {

    protected WebDriver driver;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
       // options.addArguments("--headless");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.get("https://demoqa.com/books");

        BasePage.setDriver(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
