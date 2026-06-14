package com.orangehrm.utils;

import com.orangehrm.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * WebDriver factory using ThreadLocal for test isolation.
 * WebDriverManager resolves the correct driver binary automatically —
 * no manual chromedriver download required.
 */
public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {}

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            driverThreadLocal.set(createDriver());
        }
        return driverThreadLocal.get();
    }

    private static WebDriver createDriver() {
        boolean headless = ConfigReader.isHeadless();

        switch (ConfigReader.getBrowser().toLowerCase()) {

            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions opts = new FirefoxOptions();
                if (headless) opts.addArguments("--headless");
                return new FirefoxDriver(opts);
            }

            default: {   // chrome
                WebDriverManager.chromedriver().setup();
                ChromeOptions opts = new ChromeOptions();
                if (headless) {
                    opts.addArguments("--headless=new");
                    opts.addArguments("--no-sandbox");
                    opts.addArguments("--disable-dev-shm-usage");
                }
                opts.addArguments("--window-size=1920,1080");
                return new ChromeDriver(opts);
            }
        }
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
