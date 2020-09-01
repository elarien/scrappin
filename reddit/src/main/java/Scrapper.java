import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Scrapper {
    private static HomePage homePage;

    public static void main(String[] args) {
        homePage = new HomePage();

        initialiseBrowser();
        homePage.goTo();
        homePage.listTopArticles();
        List<WebElement> elements = homePage.getAllArticles();
        List<String> subreddits = new ArrayList<>();
        for (WebElement element : elements) {
            String subReddit;
            try {
                subReddit = element.findElement(By.xpath(".//a[@data-click-id='subreddit']")).getAttribute("href");
            } catch (NoSuchElementException ex) {
                System.out.println("SubReddit: PROMOTION!");
                continue;
            }

            subreddits.add(subReddit);
        }

        closeBrowser();
    }

    private static void initialiseBrowser() {
        DriverManager.setWebDriver(new ChromeDriver());
    }

    private static void closeBrowser() {
        DriverManager.quitDriver();
    }
}
