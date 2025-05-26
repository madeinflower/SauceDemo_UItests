import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginStandardUser extends BaseTestUser {
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Test
    @Description("Login test with Standard user: valid data input")
    public void testLoginWithValidData() {
        loginWithValidDataStandardUser();
        productsPage = new ProductsPage(driver);
        assertTrue(productsPage.getProductsTitle().isDisplayed());
    }

    @Test
    @Description("Login test with Standard user: invalid username input")
    public void testLoginWithInvalidUsername() {
        loginPage = new LoginPage(driver);
        String randomUsername = getRandomString(15);
        loginPage.inputUsername(randomUsername);
        loginPage.inputPassword("secret_sauce");
        loginPage.submitForm();
        assertTrue(loginPage.getInvalidUsernameOrPasswordErrorMessage().isDisplayed());
    }

    @Test
    @Description("Login test with Standard user: invalid password input")
    public void testLoginWithInvalidPassword() {
        loginPage = new LoginPage(driver);
        loginPage.inputUsername("standard_user");
        String randomPassword = getRandomString(15);
        loginPage.inputPassword(randomPassword);
        loginPage.submitForm();
        assertTrue(loginPage.getInvalidUsernameOrPasswordErrorMessage().isDisplayed());
    }

    @Test
    @Description("Login test with Standard user: empty username input field")
    public void testLoginWithEmptyUsernameField() {
        loginPage = new LoginPage(driver);
        loginPage.inputPassword("secret_sauce");
        loginPage.submitForm();
        assertTrue(loginPage.getEmptyUsernameFieldErrorMessage().isDisplayed());
        assertTrue(loginPage.getLoginButton().isEnabled()); // this line is written in accordance with realization but I would make this button disabled in this case
    }

    @Test
    @Description("Login test with Standard user: empty password input field")
    public void testLoginWithEmptyPasswordField() {
        loginPage = new LoginPage(driver);
        loginPage.inputUsername("standard_user");
        loginPage.submitForm();
        assertTrue(loginPage.getEmptyPasswordFieldErrorMessage().isDisplayed());
        assertTrue(loginPage.getLoginButton().isEnabled()); // this line is written in accordance with realization but I would make this button disabled in this case
    }

    @Test
    @Description("Login test with Standard user: all input fields empty")
    public void testLoginWithAllInputFieldsEmpty() {
        loginPage = new LoginPage(driver);
        loginPage.submitForm();
        assertTrue(loginPage.getEmptyUsernameAndPasswordErrorMessage().isDisplayed());
        assertTrue(loginPage.getLoginButton().isEnabled()); // this line is written in accordance with realization but I would make this button disabled in this case
    }








}
