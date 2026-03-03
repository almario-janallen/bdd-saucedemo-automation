package runner;

import base.BaseTest;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"hooks", "stepdefs"},
        plugin = {"pretty", "html:target/cucumber-reports/report.html"}
)
public class CucumberRunner extends BaseTest {
    // gets AbstractTestNGCucumberTests through BaseTest
    // gets suite logging through BaseTest
}