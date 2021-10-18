package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class CheckNewUser extends BaseTest {

    String value = "test" + System.currentTimeMillis() + "@gmail.com";
    private Thread Tread;

    @Test(description = "Check Add a New User Functionality")
    public void newUser() throws InterruptedException, IOException {
        loginPage.fillUserName("hovhannes.kalajyan@pharmaccx.com");
        loginPage.fillPassword("Anahit123");
        loginPage.clickOnLoginButton();
        newUserPage.selectUsersFromMenu();
        newUserPage.clickOnNewUserButton();
        newUserPage.fillFirstNameInput("FirstName");
        newUserPage.fillLastNameInput("LastName");
        newUserPage.fillEmailInput(value);
        newUserPage.chooseOrganizationFromSelect("Amgen");
        newUserPage.chooseRolesFromSelect("Payer Admin User");
        newUserPage.chooseRolesFromSelect("Payer User");
        newUserPage.fillBankIdNumberInput("2284568562");
        newUserPage.clickOnSaveButton();
        webDriver().navigate().to("https://admin.qa.ccxsandbox.com/admin/user");
        webDriver().navigate().refresh();
        newUserPage.fillEmailForSearchInput(value);
        Thread.sleep(1000);
        String actualResultForName = newUserPage.getDataByColumnName("Name");
        Assert.assertEquals(actualResultForName,"FirstName LastName");
        String actualResultForLoginName = newUserPage.getDataByColumnName("Login Name");
        Assert.assertEquals(actualResultForLoginName,value);
        String actualResultForOrganization = newUserPage.getDataByColumnName("Organization");
        Assert.assertEquals(actualResultForOrganization,"Amgen");
        String actualResultForRoles = newUserPage.getDataByColumnName("Roles");
        Assert.assertEquals(actualResultForRoles,"Payer Admin User + Payer User");

    }
}
