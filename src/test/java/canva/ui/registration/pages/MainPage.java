package canva.ui.registration.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private static final String EXPECTED_TITLE = "Главная — Canva";

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean atPage() {
        boolean atPage;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            atPage = wait.until(ExpectedConditions.titleIs(EXPECTED_TITLE));
        } catch (TimeoutException e) {
            atPage = false;
        }
        return atPage;
    }
}
