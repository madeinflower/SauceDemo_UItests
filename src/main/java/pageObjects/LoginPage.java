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


    @FindBy(xpath = "//h3[@data-test='error' and text()='Epic sadface: Username is required']")
    private WebElement emptyUsernameFieldErrorMessage;

    @FindBy(xpath = "//h3[@data-test='error' and text()='Epic sadface: Password is required']")
    private WebElement emptyPasswordFieldErrorMessage;

    @FindBy(xpath = "//h3[@data-test='error' and text()='Epic sadface: Username is required']")
    private WebElement emptyUsernameAndPasswordErrorMessage;

    @FindBy(xpath = "//h3[@data-test='error' and text()='Epic sadface: Username and password do not match any user in this service']")
    private WebElement invalidUsernameOrPasswordErrorMessage;

    @FindBy(xpath = "//h3[@data-test='error' and text()='Epic sadface: Sorry, this user has been locked out.']")
    private WebElement lockedoutUserErrorMessage;


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

    public WebElement getEmptyPasswordFieldErrorMessage() {
        return emptyPasswordFieldErrorMessage;
    }

    public WebElement getEmptyUsernameAndPasswordErrorMessage() {
        return emptyUsernameAndPasswordErrorMessage;
    }

    public WebElement getInvalidUsernameOrPasswordErrorMessage() {
        return invalidUsernameOrPasswordErrorMessage;
    }

    public WebElement getLockedoutUserErrorMessage() {
        return lockedoutUserErrorMessage;
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
