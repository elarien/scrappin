import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Scrapper {
    private static HomePage homePage;

    public static void main(String[] args) {
        homePage = new HomePage();

        initialiseBrowser();
        homePage.goTo();
        homePage.listTopArticles();
        List<WebElement> elements = homePage.getAllArticles();
        for (WebElement element : elements) {
            String subReddit = element.findElement(By.xpath("//a[@data-click-id='subreddit']")).getAttribute("href");
            System.out.println("SubReddit: " + subReddit);
        }

        closeBrowser();
    }

    private static void initialiseBrowser() {
        DriverManager.setWebDriver(new ChromeDriver());
    }

    private static void closeBrowser() {
        DriverManager.getDriver().quit();
        DriverManager.getDriver().close();
    }
}
