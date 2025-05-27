package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//span[@class='title']")
    private WebElement productsTitle;

    @FindBy (xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    private WebElement addToCartButton;

    @FindBy (xpath = "//button[@id='remove-sauce-labs-backpack']")
    private WebElement removeFromCartButton;



    @FindBy (xpath = "//button[@id='react-burger-menu-btn']")
    private WebElement hamburgerMenu;

    @FindBy (xpath = "//a[@id='logout_sidebar_link']")
    private WebElement logOutButton;


    public WebElement getProductsTitle() {
        return productsTitle;
    }

    public WebElement getAddToCartButton() {
        return addToCartButton;
    }

    public WebElement getRemoveFromCartButton() {
        return removeFromCartButton;
    }

    public WebElement getHamburgerMenu() {
        return hamburgerMenu;
    }

    public WebElement getLogOutButton() {
        return logOutButton;
    }
}
