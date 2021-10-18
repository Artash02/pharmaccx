package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@name='username']")
    private WebElement userNameElement;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void fillUserName(String value) {
        fillValue(userNameElement, value);
    }

    public void fillPassword(String value) {
        WebElement passwordElement = webDriver.findElement(By.xpath("//input[@name='password']"));
        fillValue(passwordElement, value);
    }

    public void clickOnLoginButton() {
        WebElement loginButtonElement = webDriver.findElement(By.className("custom-form-button"));
        clickOnElement(loginButtonElement);
    }

}
