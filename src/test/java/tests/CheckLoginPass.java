package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;


public class CheckLoginPass extends BaseTest{

       @Test(description = "Check Login Functionality")
       public void loginTest() throws InterruptedException {
              homePage.clickOnMyAccountButton();
              loginPage.fillUserName("wededwed");
              loginPage.fillPassword("wedwedwed");
              loginPage.clickOnLoginButton();
              //Assert.assertEquals(loginPage.getCurrentUrl(), "edwedwedwe");
       }
}
