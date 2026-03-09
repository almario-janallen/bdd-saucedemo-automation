package stepdefs;

import config.ConfigReader;
import driver.DriverFactory;
import io.cucumber.java.en.When;

public class NavigationSteps {

    @When("I navigate directly to the products page URL")
    public void iNavigateDirectlyToTheProductsPageUrl() {
        String inventoryUrl = ConfigReader.get("base.url") + "/inventory.html";
        DriverFactory.getDriver().get(inventoryUrl);
    }
}
