package com.orangehrm.tests;

import com.orangehrm.config.ConfigReader;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * LoginTest — 3 automated tests covering TC-001, TC-003, TC-005
 *
 * POM in action:
 *   ✔ Tests instantiate page objects, call their methods, then assert
 *   ✔ No locators, no WebElement, no driver.findElement() in this file
 *   ✔ No Thread.sleep() — all waits are inside WaitHelper
 */
public class LoginTest extends BaseTest {

    // ── TC-001: Valid Login ──────────────────────────────────────────────────

    @Test(description = "TC-001: Valid credentials redirect user to dashboard")
    public void testValidLogin() {
        LoginPage     loginPage     = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);

        loginPage.loginWith(
            ConfigReader.getValidUsername(),
            ConfigReader.getValidPassword()
        );

        Assert.assertTrue(
            dashboardPage.isOnDashboardUrl(),
            "URL should contain '/dashboard/index' after valid login"
        );
        Assert.assertTrue(
            dashboardPage.isDashboardVisible(),
            "Dashboard header element should be visible after login"
        );
        Assert.assertEquals(
            dashboardPage.getDashboardHeaderText(),
            "Dashboard",
            "Dashboard header text should be 'Dashboard'"
        );
    }

    // ── TC-003: Invalid Password ─────────────────────────────────────────────

    @Test(description = "TC-003: Wrong password shows 'Invalid credentials' alert")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginWith(
            ConfigReader.getValidUsername(),
            "wrongPassword@99"
        );

        Assert.assertEquals(
            loginPage.getErrorAlertMessage(),
            "Invalid credentials",
            "Error message should say 'Invalid credentials'"
        );
        Assert.assertTrue(
            loginPage.isOnLoginPage(),
            "User should remain on login page after wrong password"
        );
    }

    // ── TC-005: Empty Fields Validation ──────────────────────────────────────

    @Test(description = "TC-005: Empty form submit shows 'Required' on both fields")
    public void testEmptyFieldsValidation() {
        LoginPage loginPage = new LoginPage(driver);

        // Click Login without filling anything
        loginPage.clickLoginButton();

        Assert.assertEquals(
            loginPage.getUsernameValidationMessage(),
            "Required",
            "Username field should show 'Required' when empty"
        );
        Assert.assertEquals(
            loginPage.getPasswordValidationMessage(),
            "Required",
            "Password field should show 'Required' when empty"
        );
        Assert.assertTrue(
            loginPage.isOnLoginPage(),
            "User should remain on login page after empty submission"
        );
    }
}
