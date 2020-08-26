import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private final By topArticlesLoc = By.xpath("//a[@href='/top/']//div[text()='top']");
    private final By listArticlesLoc = By.xpath("//div[@data-testid]");

    public void goTo() {
        BrowserActions.goTo("https://www.reddit.com/");
    }

    public void listTopArticles() {
        BrowserActions.click(topArticlesLoc);

    }

    public List<WebElement> getAllArticles() {
        return BrowserActions.getAllArticles(listArticlesLoc, 3);
    }
}
