package stepdefs;

import driver.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductSteps {
    private ProductsPage productsPage;

    @Given("I am logged in as {string} with password {string}")
    public void iAmLoggedInAs(String username, String password) {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @Given("I am on the products page")
    public void iAmOnTheProductsPage() {
        productsPage = new ProductsPage(DriverFactory.getDriver());
    }

    @When("I add product {string} to the cart")
    public void iAddProductToTheCart(String product) {
        productsPage.addProductToCart(product);
    }

    @When("I have added product {string} to the cart")
    public void iHaveAddedProductToTheCart(String product) {
        productsPage.addProductToCart(product);
    }

    @When("I remove product {string} from the cart")
    public void removeProductFromTheCart(String product) {
        productsPage.removeProductFromCart(product);
    }

    @Then("the cart count should be {string}")
    public void theCartShouldBeEmpty(String expectedCount) {
        if(expectedCount.equals("0")) {
            boolean cartBadgeGone = productsPage.getCartCount().isEmpty();
            Assert.assertTrue(cartBadgeGone, "Expected cart to be empty but found: " + productsPage.getCartCount());
        } else {
            String actualCount = productsPage.getCartCount();
            Assert.assertEquals(actualCount, expectedCount, "Cart count mismatch");
        }
    }

    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        Assert.assertTrue(productsPage.isCartEmpty(),"Expected cart to be empty but badge was still visible");
    }
}
