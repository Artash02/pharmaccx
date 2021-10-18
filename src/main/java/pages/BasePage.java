package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class BasePage {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, 40);
    }

    public void clickOnElement(WebElement webElement) {
        waitToElementIsClickable(webElement);
        webElement.click();
    }

    public void fillValue(WebElement webElement, String value) {
        waitToElement(webElement);
        webElement.clear();
        webElement.sendKeys(value);
    }

    public void waitToElement(WebElement webElement) {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitToElementIsClickable(WebElement webElement) {
        this.webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

//    public void waitToElementsCount(WebElement webElement) {
//        this.webDriverWait.until(ExpectedConditions.c(webElement));
//    }

    public String getCurrentUrl() {
       return webDriver.getCurrentUrl();
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public WebElement findElementByText(java.util.List<WebElement> webElements, String text) {
        return webElements
                .stream()
                .filter(webElement -> Objects.equals(webElement.getText(), text))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No WebElement found containing '" + text + "' text."));
    }

}
