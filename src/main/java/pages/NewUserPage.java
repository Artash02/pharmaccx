package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewUserPage extends BasePage {

    @FindBy(className = "fa-plus")
    private WebElement newUserButtonElement;
    @FindBy(id = "firstName")
    private WebElement firstNameElement;
    @FindBy(id = "lastName")
    private WebElement lastNameElement;
    @FindBy(id = "email")
    private WebElement emailElement;
    @FindBy(id = "bankIdNumber")
    private WebElement bankIdNumberElement;
    @FindBy(className = "nav")
    private WebElement menuElement;
    @FindBy(className = "nav-link")
    private List<WebElement> menuListElements;
    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveButtonElement;
    @FindBy(id = "search-bar-0")
    private WebElement searchElement;
    @FindBy(id = "table-striped")
    private WebElement tableElement;
    @FindBy(tagName = "th")
    private List<WebElement> rowsOfTable;
    @FindBy(tagName = "td")
    private List<WebElement> datasFromTable;
    private Thread Tread;


    public NewUserPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void selectUsersFromMenu() {

        waitToElementIsClickable(menuElement);
        clickOnElement(findElementByText(menuListElements, "Users"));
    }

    public void chooseOrganizationFromSelect(String value) throws IOException, InterruptedException {

        WebElement inputElement = webDriver.findElement(By.id("oorganization")).findElement(By.tagName("input"));
        inputElement.sendKeys(value, Keys.ENTER);

    }

    public void chooseRolesFromSelect(String value) throws IOException {

        WebElement inputElement = webDriver.findElement(By.id("roles")).findElement(By.tagName("input"));
        inputElement.sendKeys(value, Keys.ENTER);
    }

    public void clickOnNewUserButton() {
        clickOnElement(newUserButtonElement);
    }

    public void fillFirstNameInput(String value) {
        fillValue(firstNameElement, value);
    }

    public void fillLastNameInput(String value) {

        fillValue(lastNameElement, value);
    }

    public void fillEmailInput(String value) {

        fillValue(emailElement, value);
    }

    public void fillBankIdNumberInput(String value) {

        fillValue(bankIdNumberElement, value);
    }

    public void clickOnSaveButton() {
        clickOnElement(saveButtonElement);
    }

    public void fillEmailForSearchInput(String value) {
        waitToElement(searchElement);
        fillValue(searchElement, value);
    }

    public void getAllDataFromTable() {

      List<WebElement> allRows = tableElement.findElements(By.tagName("tr"));
        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                System.out.println("content >>   " + cell.getText());
            }
        }

    }
    public String getDataByColumnName(String columnName) throws InterruptedException {
        Thread.sleep(5000);
        int j = 0;
        WebElement columnElement = findElementByText(rowsOfTable, columnName);
        for (WebElement rowOfTable:rowsOfTable) {
            if(rowOfTable.getText().equals(columnElement.getText())){
              break;
            }
            j++;


        }
        List<String> elementsFromTable=new ArrayList<>();
        for (WebElement dataFromTable:datasFromTable) {
            elementsFromTable.add(dataFromTable.getText());
        }
        System.out.println(elementsFromTable);
        System.out.println(j);
        return elementsFromTable.get(j);
        //Assert.assertEquals(elementsFromTable.get(j),value,"This user does not exists in DB");
 }

}
