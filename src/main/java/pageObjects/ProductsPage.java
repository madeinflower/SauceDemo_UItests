package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.stream.Collectors;

import java.time.Duration;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//span[@class='title']")
    private WebElement productsTitle;

    @FindBy (xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    private WebElement addToCartSauceLabsBackpackButton;


    @FindBy (xpath = "//button[@id='remove-sauce-labs-backpack']")
    private WebElement removeFromCartSauceLabsBackpackButton;

    @FindBy (xpath = "//button[@id='add-to-cart-sauce-labs-bike-light']")
    private WebElement addToCartSauceLabsBikeLightButton;

    @FindBy (xpath = "//button[@id='remove-sauce-labs-bike-light']")
    private WebElement removeFromCartSauceLabsBikeLightButton;

    @FindBy (xpath = "//button[@id='add-to-cart-test.allthethings()-t-shirt-(red)']")
    private WebElement addToCartTestAllTheThingsButton;

    @FindBy (xpath = "//button[@id='remove-test.allthethings()-t-shirt-(red)']")
    private WebElement removeFromCartTestAllTheThingsButton;


    @FindBy (className = "shopping_cart_badge")
    private WebElement shoppingCart;


    @FindBy (xpath = "//a[@class='shopping_cart_link']")
    private WebElement emptyShoppingCart;

    @FindBy (xpath = "//button[@id='react-burger-menu-btn']")
    private WebElement hamburgerMenu;

    @FindBy (xpath = "//a[@id='logout_sidebar_link']")
    private WebElement logOutButton;

    @FindBy (xpath = "//select[@class='product_sort_container']")
    private WebElement sortingDropDown;



    public WebElement getProductsTitle() {
        return productsTitle;
    }

    public WebElement getAddToCartSauceLabsBackpackButton() {
        return addToCartSauceLabsBackpackButton;
    }

    public WebElement getRemoveFromCartSauceLabsBackpackButton() {
        return removeFromCartSauceLabsBackpackButton;
    }

    public WebElement getAddToCartSauceLabsBikeLightButton() {
        return addToCartSauceLabsBikeLightButton;
    }

    public WebElement getRemoveFromCartSauceLabsBikeLightButton() {
        return removeFromCartSauceLabsBikeLightButton;
    }

    public WebElement getAddToCartTestAllTheThingsButton() {
        return addToCartTestAllTheThingsButton;
    }

    public WebElement getRemoveFromCartTestAllTheThingsButton() {
        return removeFromCartTestAllTheThingsButton;
    }

    public WebElement getEmptyShoppingCart() { return emptyShoppingCart; }

    public WebElement getShoppingCart() {
        return shoppingCart;
    }

    public WebElement getHamburgerMenu() {
        return hamburgerMenu;
    }

    public WebElement getLogOutButton() {
        return logOutButton;
    }

    public WebElement getSortingDropDown() {return sortingDropDown; }




    public List<String> getAllProductNames() {
        List<WebElement> productNameElements = driver.findElements(By.className("inventory_item_name"));
        return productNameElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getAllProductPrices() {
        return driver.findElements(By.className("inventory_item_price"))
                .stream().map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public void clearAllProductsForTest() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "document.querySelectorAll('.inventory_item').forEach(function(e) { e.remove(); });"
        );
    }


}
