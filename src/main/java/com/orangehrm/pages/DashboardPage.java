package com.orangehrm.pages;

import com.orangehrm.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object — OrangeHRM Dashboard Page
 * Used only for post-login assertions in LoginTest.
 */
public class DashboardPage {

    private final WebDriver driver;
    private final WaitHelper wait;

    // ── Locators ─────────────────────────────────────────────────────────────

    @FindBy(css = "h6.oxd-topbar-header-breadcrumb-module")
    private WebElement dashboardHeader;

    // ── Constructor ──────────────────────────────────────────────────────────

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }

    // ── State Query Methods ──────────────────────────────────────────────────

    public boolean isDashboardVisible() {
        try {
            return wait.waitForVisibility(dashboardHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getDashboardHeaderText() {
        return wait.waitForVisibility(dashboardHeader).getText();
    }

    public boolean isOnDashboardUrl() {
        return wait.waitForUrlContains("/dashboard/index");
    }
}
