package canva.ui.login.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private static final String EXPECTED_TITLE = "Home - Canva";

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean atPage() {
        boolean atPage;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            atPage = wait.until(ExpectedConditions.titleIs(EXPECTED_TITLE));
        } catch (TimeoutException e) {
            System.out.println("Did not enter at home page " + e.getMessage());
            atPage = false;
        }
        return atPage;
    }
}
