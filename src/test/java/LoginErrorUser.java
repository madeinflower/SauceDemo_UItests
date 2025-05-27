import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import pageObjects.LoginPage;
import pageObjects.ProductsPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginErrorUser extends BaseTestUser {

    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Test
    @Description("Error user login: Remove button remains and Add button does not reappear after click")
    public void testErrorUserLogin() {
        loginPage = new LoginPage(driver);
        loginPage.inputUsername("error_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.submitForm();
        productsPage = new ProductsPage(driver);
        productsPage.getAddToCartButton().click();
        assertTrue(productsPage.getRemoveFromCartButton().isDisplayed(), "Remove from Cart button is visible");
        productsPage.getRemoveFromCartButton().click();
        assertTrue(productsPage.getRemoveFromCartButton().isDisplayed(), "Remove button should still be visible after clicking");
    }
}

