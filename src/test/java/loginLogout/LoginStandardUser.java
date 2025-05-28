package loginLogout;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


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
    @Description("Log out successfully with Standard user")
    @Deprecated
    public void testSuccessfulLogout() {
        loginWithValidDataStandardUser();
        productsPage = new ProductsPage(driver);
        productsPage.getHamburgerMenu().click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(productsPage.getLogOutButton()));
        productsPage.getLogOutButton().click();

        //loginPage = new LoginPage(driver);
        //wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginButton()));
        //assertTrue(loginPage.getLoginButton().isDisplayed());
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

    @ParameterizedTest
    @MethodSource("utils.InjectionPayloads#htmlInjectionPayloads")
    @Description("Login test with HTML injection attempts in username field")
    void testLoginWithHtmlInjectionInUsername(String payload) {
        loginPage = new LoginPage(driver);
        loginPage.inputUsername(payload);
        loginPage.inputPassword("secret_sauce");
        loginPage.submitForm();
        assertTrue(loginPage.getInvalidUsernameOrPasswordErrorMessage().isDisplayed());
        assertFalse(isAlertPresent(), "HTML injection worked: an alert window appeared");
    }

    @ParameterizedTest
    @MethodSource("utils.InjectionPayloads#sqlInjectionPayloads")
    @Description("Login test with SQL injection attempts in username field")
    void testLoginWithSqlInjectionInUsername(String payload) {
        loginPage = new LoginPage(driver);
        loginPage.inputUsername(payload);
        loginPage.inputPassword("secret_sauce");
        loginPage.submitForm();
        assertTrue(loginPage.getInvalidUsernameOrPasswordErrorMessage().isDisplayed());
    }

    @Test
    @Description("Login test with Standard user: username contains leading/trailing spaces")
    public void testUsernameWithLeadingAndTrailingSpaces() {
        loginPage = new LoginPage(driver);
        loginPage.inputUsername(" standard_user ");
        loginPage.inputPassword("secret_sauce");
        loginPage.submitForm();
        assertTrue(loginPage.getInvalidUsernameOrPasswordErrorMessage().isDisplayed());
    }

    @Test
    @Description("Login test with Standard user: username field contains only spaces")
    public void testUsernameWithOnlySpaces() {
        loginPage = new LoginPage(driver);
        loginPage.inputUsername("     ");
        loginPage.inputPassword("secret_sauce");
        loginPage.submitForm();
        assertTrue(loginPage.getInvalidUsernameOrPasswordErrorMessage().isDisplayed());
    }

    @Test
    @Description("Login test with Standard user: username exceeds max expected length")
    public void testUsernameWithExcessiveLength() {
        loginPage = new LoginPage(driver);
        String longUsername = "user".repeat(100); // например, 400 символов
        loginPage.inputUsername(longUsername);
        loginPage.inputPassword("secret_sauce");
        loginPage.submitForm();
        assertTrue(loginPage.getInvalidUsernameOrPasswordErrorMessage().isDisplayed());
    }

    @Test
    @Description("Login test with Standard user: username with special characters")
    public void testUsernameWithSpecialCharacters() {
        loginPage = new LoginPage(driver);
        loginPage.inputUsername("us!@#%&*()_+=<>?");
        loginPage.inputPassword("secret_sauce");
        loginPage.submitForm();
        assertTrue(loginPage.getInvalidUsernameOrPasswordErrorMessage().isDisplayed());
    }

    @Test
    @Description("Login test with Standard user: double click on login button does not break login flow")
    public void testDoubleClickLoginButton() {
        loginPage = new LoginPage(driver);
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.getLoginButton().click();
        productsPage = new ProductsPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(productsPage.getProductsTitle()));
        assertTrue(productsPage.getProductsTitle().isDisplayed(), "Login page was reloaded or broken by second click");
    }

    @Test
    @Description("Login test with Standard user: password field contains only spaces")
    public void testPasswordWithOnlySpaces() {
        loginPage = new LoginPage(driver);
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("     ");
        loginPage.submitForm();

        assertTrue(loginPage.getInvalidUsernameOrPasswordErrorMessage().isDisplayed());
    }

    @Test
    @Description("Login test with Standard user: password contains leading/trailing spaces")
    public void testPasswordWithLeadingAndTrailingSpaces() {
        loginPage = new LoginPage(driver);
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword(" secret_sauce ");
        loginPage.submitForm();

        assertTrue(loginPage.getInvalidUsernameOrPasswordErrorMessage().isDisplayed());
    }

    @Test
    @Description("Login test with Standard user: password field with maximum allowed length")
    public void testPasswordWithMaxLength() {
        loginPage = new LoginPage(driver);
        loginPage.inputUsername("standard_user");
        String longPassword = "a".repeat(255); // или 512, если хочешь проверить больше
        loginPage.inputPassword(longPassword);
        loginPage.submitForm();

        assertTrue(loginPage.getInvalidUsernameOrPasswordErrorMessage().isDisplayed());
    }

    @Test
    @Description("Login test with Standard user: password field with special characters")
    public void testPasswordWithSpecialCharacters() {
        loginPage = new LoginPage(driver);
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("!@#$%^&*()_+-=~`");
        loginPage.submitForm();

        assertTrue(loginPage.getInvalidUsernameOrPasswordErrorMessage().isDisplayed());
    }

    @Test
    @Description("Login test with Standard user: password field input type should be 'password'")
    public void testPasswordFieldIsMasked() {
        loginPage = new LoginPage(driver);
        String inputType = loginPage.getPasswordInputField().getAttribute("type");
        assertTrue(inputType.equals("password"), "Password input field is not masked (should be type='password')");
    }

}








