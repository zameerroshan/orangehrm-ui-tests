package com.orangehrm.utils;

import com.orangehrm.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Centralises all explicit wait logic.
 * Thread.sleep() is NEVER used in this project.
 * Every synchronisation point goes through one of these methods.
 */
public class WaitHelper {

    private final WebDriverWait wait;

    public WaitHelper(WebDriver driver) {
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(ConfigReader.getExplicitWaitSeconds())
        );
    }

    /** Wait until element is visible in the DOM, then return it. */
    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /** Wait until element located by locator is visible, then return it. */
    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /** Wait until element is clickable, then return it. */
    public WebElement waitForClickability(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /** Wait until the URL contains the given substring. Returns true on success. */
    public boolean waitForUrlContains(String partialUrl) {
        return wait.until(ExpectedConditions.urlContains(partialUrl));
    }
}
