package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{
    private final By checkOutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");
    private final By shoppingCartLink = By.cssSelector(".shopping_cart_link");

    private By removeButtonFor(String productName) {
        return By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button[text()='Remove']");
    }

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToCart() {
        click(shoppingCartLink);
    }
    public void clickCheckout() {
        click(checkOutButton);
    }

    public void clickContinueShoppingButton() {
        click(continueShoppingButton);
    }

    public void removeProductFromCart(String productName) {
        click(removeButtonFor(productName));
    }
}
