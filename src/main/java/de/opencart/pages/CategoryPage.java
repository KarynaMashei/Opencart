package de.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CategoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By categoryTitle = By.cssSelector("#content h2");
    private final By productNames = By.cssSelector(".product-layout h4 a");

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTitle))
                .getText()
                .trim();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public List<String> getProductNames() {
        return wait.until(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(productNames)
                )
                .stream()
                .map(element -> element.getText().trim())
                .toList();
    }
}
