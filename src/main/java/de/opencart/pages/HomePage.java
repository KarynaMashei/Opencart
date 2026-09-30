package de.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By searchInput = By.cssSelector("#search input[name='search']");
    private final By searchButton = By.cssSelector("#search button");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public SearchResultsPage searchFor(String productName) {
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput)
        );
        input.clear();
        input.sendKeys(productName);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        wait.until(ExpectedConditions.urlContains("route=product/search"));
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage openSearchPage() {
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput)
        );
        input.clear();
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        wait.until(ExpectedConditions.urlContains("route=product/search"));
        return new SearchResultsPage(driver);
    }

    public CategoryPage openCategory(String categoryName) {
        By categoryLocator = By.xpath(
                "//nav[@id='menu']//a[normalize-space()='" + categoryName + "']"
        );
        WebElement category = wait.until(
                ExpectedConditions.elementToBeClickable(categoryLocator)
        );

        if (category.getAttribute("class").contains("dropdown-toggle")) {
            category.click();
            By showAllLocator = By.xpath(
                    "//nav[@id='menu']//a[contains(@class,'see-all') " +
                            "and normalize-space()='Show All " + categoryName + "']"
            );
            wait.until(ExpectedConditions.elementToBeClickable(showAllLocator)).click();
        } else {
            category.click();
        }

        wait.until(ExpectedConditions.urlContains("route=product/category"));
        return new CategoryPage(driver);
    }
}
