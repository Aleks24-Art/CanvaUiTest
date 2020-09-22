package canva.ui.registration.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    private static final String LOGIN_PAGE_URL = "https://www.canva.com/signup";
    private static final String EXPECTED_TITLE = "Работайте сообща и бесплатно создавайте потрясающие дизайны";

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

   // public boolean isErrorMsgAvailable() {
     //   return errorMsg.getText().equals(EXPECTED_ERROR_MSG);
   // }
}

//aleks.artem24@gmail.com
//Correct_password

// //*[@id="form_reg"]/div/div[1]/div[1]/label - xpath icon
// //*[@id="send_btn"] - xpath btn dalee


// https://www.canva.com/login
//aaaqqqwwwsss@gmail.com
// https://www.canva.com/signup