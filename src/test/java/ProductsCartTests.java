import jdk.jfr.Description;
import loginLogout.BaseTestUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.ProductsPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductsCartTests extends BaseTestUser {

    private ProductsPage productsPage;

    @BeforeEach
    public void loginSuccess() {
        loginWithValidDataStandardUser();
    }

    @Test
    @Description("Standard user adds a single product to cart")
    public void testAddSingleProductToCart() {
        productsPage = new ProductsPage(driver);
        productsPage.getAddToCartSauceLabsBackpackButton().click();
        assertTrue(productsPage.getRemoveFromCartSauceLabsBackpackButton().isDisplayed());
        String cartCount = productsPage.getShoppingCart().getText();
        assertTrue(cartCount.equals("1"), "Expected cart count to be 1 but was " + cartCount);
    }

    @Test
    @Description("Standard user adds multiple products to cart")
    public void testAddMultipleProductsToCart() {
        productsPage = new ProductsPage(driver);
        productsPage.getAddToCartSauceLabsBackpackButton().click();
        assertTrue(productsPage.getRemoveFromCartSauceLabsBackpackButton().isDisplayed());
        productsPage.getAddToCartSauceLabsBikeLightButton().click();
        assertTrue(productsPage.getRemoveFromCartSauceLabsBikeLightButton().isDisplayed());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productsPage.getAddToCartTestAllTheThingsButton());
        productsPage.getAddToCartTestAllTheThingsButton().click();
        assertTrue(productsPage.getRemoveFromCartTestAllTheThingsButton().isDisplayed());
        String cartCount = productsPage.getShoppingCart().getText();
        assertTrue(cartCount.equals("3"), "Expected cart count to be 3 but was " + cartCount);
        System.out.println(cartCount);
    }

    @Test
    @Description("Standard user removes a product from cart")
    public void testRemoveProductFromCart() {
        productsPage = new ProductsPage(driver);
        productsPage.getAddToCartSauceLabsBackpackButton().click();
        assertTrue(productsPage.getRemoveFromCartSauceLabsBackpackButton().isDisplayed());
        String cartCount = productsPage.getShoppingCart().getText();
        assertTrue(cartCount.equals("1"), "Expected cart count to be 1 but was " + cartCount);
        productsPage.getRemoveFromCartSauceLabsBackpackButton().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(productsPage.getAddToCartSauceLabsBackpackButton()));
        assertTrue(productsPage.getAddToCartSauceLabsBackpackButton().isDisplayed());
        wait.until(ExpectedConditions.textToBePresentInElement(productsPage.getShoppingCart(), ""));
        assertEquals("", productsPage.getShoppingCart().getText());
    }



    @Test
    @Description("Standard user navigates to cart from products page")
    public void testNavigationToCart() {
        productsPage = new ProductsPage(driver);
        productsPage.getAddToCartSauceLabsBackpackButton().click();
        productsPage.getShoppingCart().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("cart"));
        assertTrue(driver.getCurrentUrl().contains("cart"),
                "Expected URL to contain 'cart', but was: " + driver.getCurrentUrl());
    }

    @Test
    @Description("Standard user checks cart icon visibility with no items")
    public void testEmptyCartHasNoCounter() {
        productsPage = new ProductsPage(driver);
        assertEquals("", productsPage.getEmptyShoppingCart().getText());
    }

}

