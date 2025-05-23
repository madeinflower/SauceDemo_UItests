package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement usernameInputField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInputField;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id='login_button_container']/div/form/div[3]")
    private WebElement emptyUsernameFieldErrorMessage;





    public WebElement getUsernameInputField() {
        return usernameInputField;
    }

    public WebElement getPasswordInputField() {
        return passwordInputField;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getEmptyUsernameFieldErrorMessage() {
        return emptyUsernameFieldErrorMessage;
    }

    public void inputUsername(String username) {
        usernameInputField.clear();
        usernameInputField.sendKeys(username);
    }

    public void inputPassword(String password) {
        passwordInputField.clear();
        passwordInputField.sendKeys(password);
    }

    public void submitForm() {
        loginButton.click();
    }


}
