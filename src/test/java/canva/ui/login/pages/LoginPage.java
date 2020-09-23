package canva.ui.login.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private static final String LOGIN_PAGE_URL = "https://www.canva.com/login";
    private static final String EXPECTED_TITLE = "Работайте сообща и бесплатно создавайте потрясающие дизайны";
    private static final String EXPECTED_INCORRECT_EMAIL_MSG = "Не удалось найти учетную запись по указанному имени пользователя. Зарегистрироваться.";
    private static final String EXPECTED_INCORRECT_PASSWORD_MSG = "Неправильный пароль.";
    private static final String EXPECTED_ERROR_EMAIL_MSG = "Введите действительный электронный адрес или номер мобильного телефона.";
    private static final String EXPECTED_ERROR_ENTER_ONLY_SPACE_MSG = "Не удалось проверить страну или регион, к которым принадлежит этот номер телефона. Пожалуйста, убедитесь, что вы выбрали правильные страну или регион.";

    private final WebDriver driver;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div[6]/div/section/div/div/div/div/div/div/div[4]/form/button")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div[6]/div/section/div/div/div/div/div/div/div[4]/div/div")
    private WebElement incorrectEmailMsg;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div[6]/div/section/div/div/div/div/div/div/div[4]/div/div")
    private WebElement incorrectPasswordMsg;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div[6]/div/section/div/div/div/div/div/div/div[4]/form/div[1]/p")
    private WebElement errorEmailMsg;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div[6]/div/section/div/div/div/div/div/div/div[4]/form/div[1]/p")
    private WebElement errorEnterOnlySpaceMsg;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(LOGIN_PAGE_URL);
    }

    public void enterEmail(String login) {
        emailField.sendKeys(login);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginBtn.click();
    }

    public boolean atPage() {
        return driver.getTitle().equals(EXPECTED_TITLE);
    }

    public boolean isErrorMsgForIncorrectEmailAvailable() {
        return incorrectEmailMsg.getText().equals(EXPECTED_INCORRECT_EMAIL_MSG);
    }

    public boolean isErrorMsgForIncorrectPasswordAvailable() {
        return incorrectPasswordMsg.getText().equals(EXPECTED_INCORRECT_PASSWORD_MSG);
    }

    public boolean isErrorMsgForEmailAvailable() {
        return errorEmailMsg.getText().equals(EXPECTED_ERROR_EMAIL_MSG);
    }

    public boolean isErrorMsgForEnterOnlySpaceAvailable() {
        return errorEnterOnlySpaceMsg.getText().equals(EXPECTED_ERROR_ENTER_ONLY_SPACE_MSG);
    }


}
