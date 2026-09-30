package de.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By productNames = By.cssSelector(".product-layout h4 a");
    private final By searchCriteriaInput = By.id("input-search");
    private final By categorySelect = By.cssSelector("select[name='category_id']");
    private final By subcategoryCheckbox = By.cssSelector("input[name='sub_category']");
    private final By descriptionCheckbox = By.id("description");
    private final By advancedSearchButton = By.id("button-search");
    private final By emptyResultsMessage = By.cssSelector("#content > p");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public List<String> getProductNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content")));
        return driver.findElements(productNames)
                .stream()
                .map(element -> element.getText().trim())
                .toList();
    }

    public SearchResultsPage searchWithFilters(
            String keyword,
            String category,
            boolean includeSubcategories,
            boolean includeDescriptions
    ) {
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchCriteriaInput)
        );
        input.clear();
        input.sendKeys(keyword);

        if (category != null) {
            new Select(wait.until(
                    ExpectedConditions.visibilityOfElementLocated(categorySelect)
            )).selectByVisibleText(category);
        }

        setCheckbox(subcategoryCheckbox, includeSubcategories);
        setCheckbox(descriptionCheckbox, includeDescriptions);
        wait.until(ExpectedConditions.elementToBeClickable(advancedSearchButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content")));
        return this;
    }

    public String getEmptyResultsMessage() {
        return wait.until(
                        ExpectedConditions.visibilityOfElementLocated(emptyResultsMessage)
                )
                .getText()
                .trim();
    }

    private void setCheckbox(By locator, boolean expectedState) {
        WebElement checkbox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
        if (checkbox.isSelected() != expectedState) {
            checkbox.click();
        }
    }
}
