import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginStandardUser extends BaseTestUser {
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Test
    @Description("Login test with Standard use: valid data input")
    public void testLoginWithValidData() {
        loginWithValidDataStandardUser();
        productsPage = new ProductsPage(driver);
        assertTrue(productsPage.getProductsTitle().isDisplayed());
    }

    @Test
    @Description("Login test with Standard user: empty username input field")
    public void testLoginWithEmptyUsernameField() {
        loginPage = new LoginPage(driver);
        loginPage.inputPassword("secret_sauce");
        assertTrue(loginPage.getEmptyUsernameFieldErrorMessage().isDisplayed());
        assertTrue(loginPage.getLoginButton().isEnabled()); // this line is written in accordance with realization but I would make this button disabled in this case
    }




}
