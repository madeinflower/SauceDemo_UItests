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

    public WebElement getProductsTitle() {
        return productsTitle;
    }
}
