package canva.ui.registration;

import canva.ui.login.pages.HomePage;
import canva.ui.login.pages.LoginPage;
import canva.ui.registration.pages.MainPage;
import canva.ui.registration.pages.RegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class RegistrationTest {

    private WebDriver driver;
    private RegistrationPage registrationPage;
    private MainPage mainPage;

    @BeforeClass
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        registrationPage = new RegistrationPage(driver);
        mainPage = new MainPage(driver);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            //driver.quit();
        }
    }


    @Test
    public void registerNewAccountWithCorrectData() {
        registrationPage.open();
        registrationPage.chooseSignUpWithEmail();
        assertTrue(registrationPage.atPage());
        registrationPage.enterName("SomeName");
        registrationPage.enterEmail("somehardeqmailtestqwe@gmail.com");
        registrationPage.enterPassword("Qwerty_12345");
        registrationPage.clickRegister();
        assertTrue(mainPage.atPage());
    }
}
