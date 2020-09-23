package canva.ui.login;

import canva.ui.login.pages.HomePage;
import canva.ui.login.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeClass
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void loginWithCorrectLoginAndPassword_shouldGoToHomePage() {
        loginPage.open();
        assertTrue(loginPage.atPage());
        loginPage.enterEmail("aaaqqqwwwsss@gmail.com");
        loginPage.enterPassword("Correct_password");
        loginPage.clickLogin();
        assertTrue(homePage.atPage());
    }

    @Test
    public void loginWithCorrectLoginAndIncorrectPassword_shouldShowIncorrectPasswordMsg() {
        loginPage.open();
        assertTrue(loginPage.atPage());
        loginPage.enterEmail("aaaqqqwwwsss@gmail.com");
        loginPage.enterPassword("Incorrect_password");
        loginPage.clickLogin();
        assertFalse(homePage.atPage());
        assertTrue(loginPage.isErrorMsgForIncorrectPasswordAvailable());
    }

    @Test
    public void loginWithIncorrectLoginAndCorrectPassword_shouldShowIncorrectEmailMsg() {
        loginPage.open();
        assertTrue(loginPage.atPage());
        loginPage.enterEmail("Incorrect_login");
        loginPage.enterPassword("Correct_password");
        loginPage.clickLogin();
        assertFalse(homePage.atPage());
        assertTrue(loginPage.isErrorMsgForIncorrectEmailAvailable());
    }

    @Test
    public void loginWithIncorrectLoginAndIncorrectPassword_shouldShowIncorrectEmailMsg() {
        loginPage.open();
        assertTrue(loginPage.atPage());
        loginPage.enterEmail("Incorrect_login");
        loginPage.enterPassword("Incorrect_password");
        loginPage.clickLogin();
        assertFalse(homePage.atPage());
        assertTrue(loginPage.isErrorMsgForIncorrectEmailAvailable());
    }

    @Test
    public void loginWithSpaceOnlyLoginAndPassword_shouldShowErrorOnlySpaceMsg() {
        loginPage.open();
        assertTrue(loginPage.atPage());
        loginPage.enterEmail("   ");
        loginPage.enterPassword("      ");
        loginPage.clickLogin();
        assertFalse(homePage.atPage());
        assertTrue(loginPage.isErrorMsgForEnterOnlySpaceAvailable());
    }

    @Test
    public void loginWithEmptyLoginAndPassword_shouldShowErrorEmailMsg() {
        loginPage.open();
        assertTrue(loginPage.atPage());
        loginPage.enterEmail("");
        loginPage.enterPassword("");
        loginPage.clickLogin();
        assertFalse(homePage.atPage());
        assertTrue(loginPage.isErrorMsgForEmailAvailable());
    }

    @Test
    public void loginWithSpecSymbols_shouldShowErrorEmailMsg() {
        loginPage.open();
        assertTrue(loginPage.atPage());
        loginPage.enterEmail("/\\:*?\"<>{}[]|");
        loginPage.enterPassword("/\\:*?\"<>{}[]|");
        assertFalse(homePage.atPage());
        assertTrue(loginPage.isErrorMsgForEmailAvailable());
    }

}
