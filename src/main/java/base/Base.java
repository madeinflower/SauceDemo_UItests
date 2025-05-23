package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {

    protected WebDriver getDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    protected void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}

