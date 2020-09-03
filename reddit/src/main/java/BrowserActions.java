import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BrowserActions {

    public static void goTo(String url) {
        DriverManager.getDriver().get(url);
    }

    public static void click(By locator) {
        WebElement button = waitAndGetElement(locator, DriverManager.getDriver());
        button.click();
    }

    public static List<WebElement> getAllArticles(By locator, int pages) {
        for (int i = 1; i <= pages; i++) {
            waitForElementsToReachCount(locator, i * 10);
            scrollToTheEnd();
        }
        return waitAndGetElements(locator, DriverManager.getDriver());
    }

    private static void waitForElementsToReachCount(By locator, int expectedCount) {
        while (waitAndGetElements(locator, DriverManager.getDriver()).size() < expectedCount){
            System.out.println(waitAndGetElements(locator, DriverManager.getDriver()).size());
        }
    }

    public static void scrollToTheEnd() {
        WebElement body = waitAndGetElement(By.xpath("//body"), DriverManager.getDriver());
        body.sendKeys(Keys.END);
    }

    private static WebElement waitAndGetElement(By locator, WebDriver webDriver) {
        return new WebDriverWait(webDriver, 5)
                .until(driver -> driver.findElement(locator));
    }

    private static List<WebElement> waitAndGetElements(By locator, WebDriver webDriver) {
        return new WebDriverWait(webDriver, 5)
                .until(driver -> driver.findElements(locator));
    }
}
