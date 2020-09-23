package canva.ui.registration.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GreetingPage {

    private static final String EXPECTED_TITLE = "Главная — Canva";
    private static final String EXPECTED_TOPIC_LIST_TITLE = "Для чего вы планируете использовать Canva?";

    private final WebDriver driver;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[3]/div/div/div/div/div/div/div[1]/h3")
    private WebElement topicListWindow;

    public GreetingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean atPage() {
        boolean atPage;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 6, 100);
            atPage = wait.until(ExpectedConditions.titleIs(EXPECTED_TITLE));
        } catch (TimeoutException e) {
            atPage = false;
            System.out.println("Did not enter at greeting page " + e.getMessage());
        }
        return atPage;
    }

    public boolean isTopicListAvailable() {
        System.out.println(topicListWindow.getText());
        return topicListWindow.getText().equals(EXPECTED_TOPIC_LIST_TITLE);
    }
}
