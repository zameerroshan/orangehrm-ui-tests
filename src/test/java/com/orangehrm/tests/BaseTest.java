package com.orangehrm.tests;

import com.orangehrm.config.ConfigReader;
import com.orangehrm.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * BaseTest — parent of all UI test classes.
 *
 * @BeforeMethod → fresh browser + navigate to login page before each test
 * @AfterMethod  → quit browser after each test (even if test failed)
 *
 * Each test method runs in complete isolation — no shared state between tests.
 */
public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.getBaseUrl() + "/web/index.php/auth/login");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
