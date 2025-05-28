package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public WebElement getEmptyShoppingCart() {
        return emptyShoppingCart;
    }

    public WebElement getShoppingCart() {
        return shoppingCart;
    }

    public WebElement getHamburgerMenu() {
        return hamburgerMenu;
    }

    public WebElement getLogOutButton() {
        return logOutButton;
    }
}
