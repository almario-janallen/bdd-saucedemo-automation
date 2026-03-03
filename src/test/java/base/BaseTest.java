package base;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest extends AbstractTestNGCucumberTests {
    private static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeSuite
    public void suiteSetup() {
        log.info("========== TEST SUITE STARTED ==========");
    }

    @AfterSuite
    public void suiteTeardown() {
        log.info("========== TEST SUITE FINISHED ==========");
    }
}
