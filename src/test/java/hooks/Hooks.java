package hooks;

import config.ConfigReader;
import driver.DriverFactory;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Hooks {
    private static final Logger log = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp() {
        DriverFactory.initDriver();
        WebDriver driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(
                        Integer.parseInt(ConfigReader.get("implicit.wait"))
                ));
        driver.get(ConfigReader.get("base.url"));
        log.info("Browser launched and navigated to: {}", ConfigReader.get("base.url"));
    }

    @After
    public void tearDown(@NotNull Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();

        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            log.error("Scenario FAILED: {}",scenario.getName());
        } else {
            log.info("Scenario PASSED: {}", scenario.getName());
        }

        DriverFactory.quitDriver();
    }
}
