package winners.net;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckBettingTips {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() throws Exception {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://winners.net/");
    }


    @Test()
    public void bettingTips() throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();
        driver.findElement(By.xpath("/html/body/div/div/header/div[2]/div/a[4]/h2")).click();
        Thread.sleep(5000);
        String actualTitle="Betting Tips - Winners.net";
        String expectedTitle= driver.getTitle();
        //Assert.assertEquals(expectedTitle,actualTitle);

        List<WebElement> containers = driver.findElements(By.className("QuickCardContainer"));
          List<WebElement> logo = driver.findElements(By.className("logo"));
        List<String> actualLogos = new ArrayList<>(Arrays.asList("Betway","Winners.bet","Unikrn"));
        List<WebElement> score = driver.findElements(By.xpath(" /html/body/div/div/aside/div/div/div/div[1]/span"));
        List<String> actualScores = new ArrayList<>(Arrays.asList("9 / 10","8.6 / 10","8.6 / 10"));

        List<String> scores=new ArrayList<>();
        for(WebElement item:score){
            scores.add(item.getText());

        }
        List<String> logos=new ArrayList<>();
        for(WebElement item:logo){
            logos.add(item.getAttribute("title"));
        }

        for (int i=0; i<containers.size(); i++) {
            softAssert.assertEquals(scores.get(i),actualScores.get(i));
            softAssert.assertEquals(logos.get(i),actualLogos.get(i));
            softAssert.assertAll();
            System.out.println(i);

        }
    }


    @AfterMethod
    public void afterMethod() throws InterruptedException {

        Thread.sleep(1000);
        driver.quit();

    }

}
