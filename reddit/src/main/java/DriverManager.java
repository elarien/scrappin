import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void quitDriver(){
        webDriver.get().close();
        webDriver.get().quit();
        webDriver.remove();
    }
}
