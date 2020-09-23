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

        registrationPage.open();
        registrationPage.chooseSignUpWithEmail();
        assertTrue(registrationPage.atPage());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void registerNewAccountWithCorrectData_shouldGoToGreetingPage() {
        registrationPage.enterRegisterData(
                "SomeName",
                "Qwerty_12345");

        registrationPage.clickRegister();

        assertTrue(greetingPage.atPage());
        assertTrue(greetingPage.isTopicListAvailable());
    }

    @Test
    public void registerNewAccountWithIncorrectEmail_shouldShowIncorrectEmailMsg() {
        registrationPage.enterRegisterData(
                "SomeName",
                "Incorrect_email",
                "Qwerty_12345");

        assertTrue(registrationPage.isErrorMsgForIncorrectEmailAvailable());
        assertFalse(greetingPage.atPage());
    }

    @Test
    public void registerNewAccountWithWeekPassword_shouldShowWeekPasswordMsg() {
        registrationPage.enterRegisterData(
                "SomeName",
                "some.email@gmail.com",
                "aaaaaaaa");

        assertTrue(registrationPage.isErrorMsgForWeekPasswordAvailable());
        assertFalse(greetingPage.atPage());
    }

    @Test
    public void registerNewAccountWithShortPassword_shouldShowShortPasswordMsg() {
        registrationPage.enterRegisterData(
                "SomeName",
                "some.email@gmail.com",
                "a");

        assertTrue(registrationPage.isErrorMsgForShortPasswordAvailable());
        assertFalse(greetingPage.atPage());
    }

    @Test
    public void registerNewAccountWithSpaceOnlyDataUsingShortPassword_shouldShowShortPasswordMsg() {
        registrationPage.enterRegisterData(
                "          ",
                "          ",
                "  ");

        assertTrue(registrationPage.isErrorMsgForShortPasswordAvailable());
        assertFalse(greetingPage.atPage());
    }

    @Test
    public void registerNewAccountWithSpaceOnlyDataUsingWeekPassword_shouldShowWeekPasswordMsg() {
        registrationPage.enterRegisterData(
                "          ",
                "          ",
                "          ");

        assertTrue(registrationPage.isErrorMsgForWeekPasswordAvailable());
        assertFalse(greetingPage.atPage());
    }
}
