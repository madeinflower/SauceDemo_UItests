import jdk.jfr.Description;
import loginLogout.BaseTestUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pageObjects.ProductsPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class SortingTests extends BaseTestUser {

    private ProductsPage productsPage;

    @BeforeEach
    public void loginSuccess() {
        loginWithValidDataStandardUser();
    }


    @Test
    @Description("Verifies sorting by name from A to Z")
    public void testSortByNameAToZ() {
        productsPage = new ProductsPage(driver);
        Select dropdown = new Select(productsPage.getSortingDropDown());
        dropdown.selectByVisibleText("Name (A to Z)");
        List<String> names = productsPage.getAllProductNames();
        List<String> sorted = new ArrayList<>(names);
        Collections.sort(sorted);
        assertEquals(sorted, names);
    }

    @Test
    @Description("Verify sorting by product name from Z to A")
    public void testSortByNameZToA() {
        productsPage = new ProductsPage(driver);
        Select dropdown = new Select(productsPage.getSortingDropDown());
        dropdown.selectByVisibleText("Name (Z to A)");
        List<String> names = productsPage.getAllProductNames();
        List<String> sorted = new ArrayList<>(names);
        sorted.sort(Collections.reverseOrder());
        assertEquals(sorted, names);
    }

    @Test
    @Description("Verify sorting by product price from low to high")
    public void testSortByPriceLowToHigh() {
        productsPage = new ProductsPage(driver);
        Select dropdown = new Select(productsPage.getSortingDropDown());
        dropdown.selectByVisibleText("Price (low to high)");
        List<Double> prices = productsPage.getAllProductPrices();
        List<Double> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);
        assertEquals(sorted, prices);
    }

    @Test
    @Description("Verify sorting by product price from high to low")
    public void testSortByPriceHighToLow() {
        productsPage = new ProductsPage(driver);
        Select dropdown = new Select(productsPage.getSortingDropDown());
        dropdown.selectByVisibleText("Price (high to low)");
        List<Double> prices = productsPage.getAllProductPrices();
        List<Double> sorted = new ArrayList<>(prices);
        sorted.sort(Collections.reverseOrder());
        assertEquals(sorted, prices);
    }

    @Test
    @Description("Verify all available sorting options are present and correct")
    public void testDropdownOptionsPresentAndWorking() {
        productsPage = new ProductsPage(driver);
        Select dropdown = new Select(productsPage.getSortingDropDown());
        List<String> actualOptions = dropdown.getOptions().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        List<String> expectedOptions = Arrays.asList(
                "Name (A to Z)",
                "Name (Z to A)",
                "Price (low to high)",
                "Price (high to low)"
        );
        assertEquals(expectedOptions, actualOptions);
    }

    @Test
    @Description("Attempt to set an invalid sorting value via JavaScript")
    public void testInvalidSortValueViaJs() {
        productsPage = new ProductsPage(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].value = 'INVALID'; arguments[0].dispatchEvent(new Event('change'));",
                productsPage.getSortingDropDown()
        );
        List<String> names = productsPage.getAllProductNames();
        assertFalse(names.isEmpty()); // list should not be broken
    }

    @Test
    @Description("Verify dropdown behavior when disabled via JavaScript")
    public void testDropdownDisabledBehavior() {
        productsPage = new ProductsPage(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('disabled', 'true');", productsPage.getSortingDropDown());
        assertFalse(productsPage.getSortingDropDown().isEnabled());
    }

    @Test
    @Description("Verify sorting behavior with an empty product list")
    public void testSortingWithEmptyProductList() {
        productsPage = new ProductsPage(driver);
        productsPage.clearAllProductsForTest(); // This method should remove or hide all items
        Select dropdown = new Select(productsPage.getSortingDropDown());
        dropdown.selectByVisibleText("Price (low to high)");
        List<String> names = productsPage.getAllProductNames();
        assertTrue(names.isEmpty());
    }




}
