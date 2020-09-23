package canva.ui.registration;

import canva.ui.registration.pages.GreetingPage;
import canva.ui.registration.pages.RegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RegistrationTest {

    private WebDriver driver;
    private RegistrationPage registrationPage;
    private GreetingPage greetingPage;

    @BeforeClass
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        registrationPage = new RegistrationPage(driver);
        greetingPage = new GreetingPage(driver);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void registerNewAccountWithCorrectData_shouldGoToGreetingPage() {
        registrationPage.open();
        registrationPage.chooseSignUpWithEmail();
        assertTrue(registrationPage.atPage());
        registrationPage.enterName("SomeName");
        registrationPage.enterEmail();
        registrationPage.enterPassword("Qwerty_12345");
        registrationPage.clickRegister();
        assertTrue(greetingPage.atPage());
        assertTrue(greetingPage.isTopicListAvailable());
    }

    @Test
    public void registerNewAccountWithIncorrectEmail_shouldShowIncorrectEmailMsg() {
        registrationPage.open();
        registrationPage.chooseSignUpWithEmail();
        assertTrue(registrationPage.atPage());
        registrationPage.enterName("SomeName");
        registrationPage.enterEmail("Incorrect_email");
        registrationPage.enterPassword("Qwerty_12345");
        assertTrue(registrationPage.isErrorMsgForIncorrectEmailAvailable());
        assertFalse(greetingPage.atPage());
    }

    @Test
    public void registerNewAccountWithWeekPassword_shouldShowWeekPasswordMsg() {
        registrationPage.open();
        registrationPage.chooseSignUpWithEmail();
        assertTrue(registrationPage.atPage());
        registrationPage.enterName("SomeName");
        registrationPage.enterEmail("some.email@gmail.com");
        registrationPage.enterPassword("aaaaaaaa");
        assertTrue(registrationPage.isErrorMsgForWeekPasswordAvailable());
        assertFalse(greetingPage.atPage());
    }

    @Test
    public void registerNewAccountWithShortPassword_shouldShowShortPasswordMsg() {
        registrationPage.open();
        registrationPage.chooseSignUpWithEmail();
        assertTrue(registrationPage.atPage());
        registrationPage.enterName("SomeName");
        registrationPage.enterEmail("some.email@gmail.com");
        registrationPage.enterPassword("a");
        assertTrue(registrationPage.isErrorMsgForShortPasswordAvailable());
        assertFalse(greetingPage.atPage());
    }

    @Test
    public void registerNewAccountWithSpaceOnlyDataUsingShortPassword_shouldShowShortPasswordMsg() {
        registrationPage.open();
        registrationPage.chooseSignUpWithEmail();
        assertTrue(registrationPage.atPage());
        registrationPage.enterName("          ");
        registrationPage.enterEmail("          ");
        registrationPage.enterPassword("  ");
        assertTrue(registrationPage.isErrorMsgForShortPasswordAvailable());
        assertFalse(greetingPage.atPage());
    }

    @Test
    public void registerNewAccountWithSpaceOnlyDataUsingWeekPassword_shouldShowWeekPasswordMsg() {
        registrationPage.open();
        registrationPage.chooseSignUpWithEmail();
        assertTrue(registrationPage.atPage());
        registrationPage.enterName("          ");
        registrationPage.enterEmail("          ");
        registrationPage.enterPassword("          ");
        assertTrue(registrationPage.isErrorMsgForWeekPasswordAvailable());
        assertFalse(greetingPage.atPage());
    }
}
