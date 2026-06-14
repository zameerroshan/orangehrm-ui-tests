# OrangeHRM UI Automation

## Which App and Why
I chose **OrangeHRM** (opensource-demo.orangehrmlive.com) because it is a stable, feature-rich web application that closely mirrors enterprise HR software. It offers a variety of UI elements (dropdowns, forms, complex locators) making it an ideal candidate to demonstrate robust browser automation.

## Framework and Language
* **Language:** Java
* **Framework:** Selenium WebDriver (for browser interaction), TestNG (for execution and assertions), and Maven (for dependency management).

## How to Run the Tests
1. Ensure you have Java (JDK 11 or higher) and Maven installed.
2. Clone this repository and navigate to the project directory.
3. Run the following command in your terminal:
   ```bash
   mvn clean test
   ```
4. A browser window will open automatically, run the tests, and close. Test results will be available in your console and `target/surefire-reports`.

## Assumptions and Limitations
* **Environment Stability:** Since we are using the public demo site, test data might occasionally be modified or reset by other users, which could temporarily cause tests to fail.
* **Browser Requirement:** The tests are currently configured to run on Google Chrome, assuming Chrome is installed on the execution machine.
