package com.orangehrm.pages;

import com.orangehrm.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object — OrangeHRM Login Page
 *
 * POM Rules followed:
 *   ✔ All locators declared as @FindBy fields (PageFactory pattern)
 *   ✔ All interactions are methods — tests never touch WebElement directly
 *   ✔ State queries return primitives or Strings — never WebElement
 *   ✔ No assertions inside page class — assertions belong in tests only
 */
public class LoginPage {

    private final WebDriver driver;
    private final WaitHelper wait;

    // ── Locators ─────────────────────────────────────────────────────────────

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    // Alert shown after failed login attempt
    @FindBy(css = ".oxd-alert-content-text")
    private WebElement errorAlertText;

    // Inline "Required" shown under Username when empty
    @FindBy(xpath = "(//span[contains(@class,'oxd-input-field-error-message')])[1]")
    private WebElement usernameValidationMsg;

    // Inline "Required" shown under Password when empty
    @FindBy(xpath = "(//span[contains(@class,'oxd-input-field-error-message')])[2]")
    private WebElement passwordValidationMsg;

    // ── Constructor ──────────────────────────────────────────────────────────

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }

    // ── Action Methods ───────────────────────────────────────────────────────

    public void enterUsername(String username) {
        WebElement field = wait.waitForVisibility(usernameField);
        field.clear();
        field.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement field = wait.waitForVisibility(passwordField);
        field.clear();
        field.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.waitForClickability(loginButton).click();
    }

    /** Convenience: fills both fields and clicks Login in one call. */
    public void loginWith(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    // ── State Query Methods ──────────────────────────────────────────────────

    public String getErrorAlertMessage() {
        return wait.waitForVisibility(errorAlertText).getText();
    }

    public String getUsernameValidationMessage() {
        return wait.waitForVisibility(usernameValidationMsg).getText();
    }

    public String getPasswordValidationMessage() {
        return wait.waitForVisibility(passwordValidationMsg).getText();
    }

    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("/auth/login");
    }
}
