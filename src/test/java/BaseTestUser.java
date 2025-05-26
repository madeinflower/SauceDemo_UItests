import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.LoginPage;
import utils.RandomDataGenerator;

public class BaseTestUser {
    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }

    public void loginWithValidDataStandardUser() {
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.submitForm();
    }

    public String getRandomString(int maxLength) {
        return RandomDataGenerator.generateRandomString(maxLength);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
