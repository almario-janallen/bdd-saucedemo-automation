package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage{
    private By addToCartButtonFor(String productName) {
        return By.xpath("//div[text()='" + productName+ "']/ancestor::div[@class='inventory_item']//button");
    }

    private By removeButtonFor(String productName) {
        return By.xpath("//div[text()='" + productName+ "']/ancestor::div[@class='inventory_item']//button");
    }

    private final By cartBadge = By.cssSelector(".shopping_cart_badge");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToCart(String productName) {
        click(addToCartButtonFor(productName));
    }

    public void removeProductFromCart(String productName) {
        click(removeButtonFor(productName));
    }

    public String getCartCount() {
        return waitForElement(cartBadge).getText();
    }

}
