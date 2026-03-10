package stepdefs;

import config.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.LoginPage;
import driver.DriverFactory;

public class LoginSteps {

    private LoginPage loginPage;

    public LoginSteps() {
        loginPage = new LoginPage(DriverFactory.getDriver());
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        // no steps here since it is handled in Hooks.java
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        String resolvedUsername = username.equals("(empty)") ? "" : username;
        loginPage.enterUsername(resolvedUsername);
    }

    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        String resolvedPassword = password.equals("(empty)") ? "" : password;
        loginPage.enterPassword(resolvedPassword);
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLogin();
    }

    @Given("I log in with valid credentials")
    public void iLogInWithValidCredentials() {
        loginPage.enterUsername(ConfigReader.get("username"));
        loginPage.enterPassword(ConfigReader.get("password"));
        loginPage.clickLogin();
    }

    @When("I accept change password alert")
    public void iAcceptChangePasswordAlert() {
        loginPage.acceptAlert();
    }

    @Then("I should be redirected to the products page")
    public void iShouldBeRedirectedToTheProductsPage() {
        String currentUrl = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory"),
                "Expected to be on products page but was: " + currentUrl);
    }

    @Then("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualMessage, expectedMessage,
                "Error message mismatch");
    }

    @Then("I should be redirected back to the login page")
    public void iShouldBeRedirectedBackToTheLoginPage() {
        String currentUrl = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("saucedemo.com") && !currentUrl.contains("inventory"),
                "Expected to be on login page but was: " + currentUrl);
    }
}