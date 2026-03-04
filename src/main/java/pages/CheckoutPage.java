package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage{
    //Step one - User Information page
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");

    //Step two - Overview page
    private final By finishButton = By.id("finish");
    private final By cancelButton = By.id("cancel");

    //Step three - Complete page
    private final By homeButton = By.id("back-to-products");
    private final By confirmationMessage = By.cssSelector(".complete-text");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    //Methods in Step One
    public void enterFirstName(String firstName) {
        type(firstNameField,firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameField,lastName);
    }

    public void enterPostalCode(String postalCode) {
        type(postalCodeField,postalCode);
    }

    public void clickContinueButton() {
        click(continueButton);
    }

    //Methods in Step Two
    public void clickFinishButon() {
        click(finishButton);
    }

    //Methods in Step Three
    public String getConfirmationMessage() {
        return waitForElement(confirmationMessage).getText();
    }

    public void clickHomeButton() {
        click(homeButton);
    }
}
