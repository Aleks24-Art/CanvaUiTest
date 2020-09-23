package canva.ui.registration.pages;

import com.mifmif.common.regex.Generex;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private static final String LOGIN_PAGE_URL = "https://www.canva.com/signup";
    private static final String EXPECTED_TITLE = "Работайте сообща и бесплатно создавайте потрясающие дизайны";
    private static final String EXPECTED_INCORRECT_EMAIL_MSG = "Введите действительный электронный адрес.";
    private static final String EXPECTED_WEEK_PASSWORD_MSG = "Крайне ненадежный";
    private static final String EXPECTED_SHORT_PASSWORD_MSG = "Используйте комбинацию из букв, чисел и символов. Минимальное количество знаков: 8.";

    private final WebDriver driver;

    @FindBy(name = "name")
    private WebElement nameField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div[6]/div/section/div/div/div/div/div/div/div[2]/form/button/span")
    private WebElement registerBtn;

    @FindBy(xpath = "//*[@id=\"js-flash-container\"]/div/div")
    private WebElement errorMsg;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div[6]/div/section/div/div/div/div/div/div/button/span")
    private WebElement signUpWithEmail;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div[6]/div/section/div/div/div/div/div/div/div[2]/form/div[2]/p")
    private WebElement incorrectEmailMsg;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div[6]/div/section/div/div/div/div/div/div/div[2]/form/div[3]/div/div/span[1]")
    private WebElement weekPasswordMsg;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div[6]/div/section/div/div/div/div/div/div/div[2]/form/div[3]/div/div/span")
    private WebElement shortPasswordMsg;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(LOGIN_PAGE_URL);
    }

    public void enterName(String name) {
        nameField.sendKeys(name);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterEmail() {
        emailField.sendKeys(new Generex("\\w{20}\\@gmail\\.com").random());
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickRegister() {
        registerBtn.click();
    }

    public boolean atPage() {
        return driver.getTitle().equals(EXPECTED_TITLE);
    }

    public void chooseSignUpWithEmail() {
        signUpWithEmail.click();
    }

    public boolean isErrorMsgForIncorrectEmailAvailable() {
        return incorrectEmailMsg.getText().equals(EXPECTED_INCORRECT_EMAIL_MSG);
    }

    public boolean isErrorMsgForWeekPasswordAvailable() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(x -> shortPasswordMsg.isEnabled());
        } catch (TimeoutException e) {
            System.out.println(e.getMessage());
        }
        return weekPasswordMsg.getText().equals(EXPECTED_WEEK_PASSWORD_MSG);
    }

    public boolean isErrorMsgForShortPasswordAvailable() {
        return shortPasswordMsg.getText().equals(EXPECTED_SHORT_PASSWORD_MSG);
    }
}
