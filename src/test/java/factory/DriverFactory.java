package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ConfigReader;

import java.time.Duration;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver() {
        String browser = ConfigReader.get("browser");
        boolean headless = ConfigReader.getBool("headless", false);
        int implicit = ConfigReader.getInt("implicitWait", 10);

        if (browser == null || browser.isEmpty() || browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            // Run headless in CI if configured
            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }

            // Stability fixes for CI
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");

            driver.set(new ChromeDriver(options));

        } else {
            // TODO: Add Firefox/Edge support later
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        }

        // Maximize only when UI is visible
        if (!headless) {
            driver.get().manage().window().maximize();
        }

        driver.get().manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(implicit));

        return driver.get();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
