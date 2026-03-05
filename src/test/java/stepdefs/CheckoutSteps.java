package stepdefs;

import driver.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CartPage;
import pages.CheckoutPage;

import java.sql.Driver;

public class CheckoutSteps {
    private CheckoutPage checkoutPage;
    private CartPage cartPage;

    @When("I navigate to the cart")
    public void iNavigateToTheCart() {
        cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.navigateToCart();
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        cartPage.clickCheckout();
        checkoutPage = new CheckoutPage(DriverFactory.getDriver());
    }

    @When("I fill in shipping details with first name {string} last name {string} and postal code {string}")
    public void iFillInShippingDetails(String firstName, String lastName, String postalCode) {
        checkoutPage.enterFirstName(firstName);
        checkoutPage.enterLastName(lastName);
        checkoutPage.enterPostalCode(postalCode);
    }

    @When("I click continue")
    public void iClickContinue() {
        checkoutPage.clickContinueButton();
    }

    @When("I click finish")
    public void iClickFinish() {
        checkoutPage.clickFinishButon();
    }

    @Then("I should see the order confirmation message {string}")
    public void iShouldSeeTheOrderConfirmationMessage(String expectedMessage) {
        String actualMessage = checkoutPage.getConfirmationMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Confirmation message mismatch");
    }

}
