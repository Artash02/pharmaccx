package tests;

import helper.Factory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pages.HomePage;
import pages.LoginPage;
import pages.NewUserPage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;


public class BaseTest {

    public ThreadLocal<WebDriver> driver;
    public LoginPage loginPage;
    public HomePage homePage;
    public NewUserPage newUserPage;


    protected WebDriver webDriver() {
        return driver.get();
    }

    @BeforeClass(description = "")
    public void beforeClass() {
        driver = new ThreadLocal<>();
        driver.set(Factory.createWebDriver());
        driver.get().manage().window().maximize();
        loginPage = PageFactory.initElements(webDriver(), LoginPage.class);
        homePage = PageFactory.initElements(webDriver(), HomePage.class);
        newUserPage = PageFactory.initElements(webDriver(),NewUserPage.class);
        webDriver().navigate().to("https://admin.qa.ccxsandbox.com/login");
    }

    @AfterMethod
    public void screenShot(ITestResult result, Method method) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            Screenshot myScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(webDriver());
            ImageIO.write(myScreenshot.getImage(), "PNG", new File("target/" + method.getName()+"screenshot.png"));
        }
    }

    @AfterClass(alwaysRun = true, description = "After Class")
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }
}
