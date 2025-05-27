import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import pageObjects.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginLockedOutUser extends BaseTestUser {

    private LoginPage loginPage;

    @Test
    @Description("Locked out user login")
    public void lockedOutUserLogin() {
        loginPage = new LoginPage(driver);
        loginPage.inputUsername("locked_out_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.submitForm();
        assertTrue(loginPage.getLockedoutUserErrorMessage().isDisplayed());
    }

}
