package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnMyAccountButton() {
        WebElement webElement = webDriver.findElement(By.id("ma"));
        clickOnElement(webElement);
    }
}
