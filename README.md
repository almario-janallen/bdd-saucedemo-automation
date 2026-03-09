# Selenium Cucumber BDD Test Framework
### Automated E2E Testing for [SauceDemo](https://www.saucedemo.com)

---

## Why BDD?

BDD (Behavior-Driven Development) lets teams write test scenarios in plain English that both developers and non-technical stakeholders can read and validate. This bridges the gap between business requirements and automated tests, ensuring what gets built matches what was intended. By using Cucumber feature files as living documentation, the test suite itself becomes a continuously verified specification of the application's behavior.

---

## Framework Architecture Diagram

```
┌─────────────────────────────────────────────────────────┐
│                    Feature Files (.feature)             │
│         login │ product │ checkout │ negative           │
└──────────────────────────┬──────────────────────────────┘
                           │  Gherkin (Given/When/Then)
┌──────────────────────────▼──────────────────────────────┐
│                   Step Definitions                      │
│         LoginSteps │ ProductSteps │ CheckoutSteps       │
└──────────────────────────┬──────────────────────────────┘
                           │  Java method calls
┌──────────────────────────▼──────────────────────────────┐
│                    Page Objects                         │
│         LoginPage │ ProductPage │ CheckoutPage          │
│                   (extends BasePage)                    │
└──────────────────────────┬──────────────────────────────┘
                           │  Selenium WebDriver API
┌──────────────────────────▼──────────────────────────────┐
│                   Driver Layer                          │
│      DriverFactory (ThreadLocal WebDriver)              │
│      WebDriverManager (auto binary management)          │
└──────────────────────────┬──────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────────┐
│                 Cross-Cutting Concerns                  │
│   ConfigReader │ Hooks │ ScreenshotUtil │ LoggerUtil    │
└─────────────────────────────────────────────────────────┘
```

---

## Folder Structure

```
src/
├── main/java/
│   ├── config/         → ConfigReader.java
│   ├── driver/         → DriverFactory.java
│   ├── pages/          → BasePage.java, LoginPage.java,
│   │                      ProductPage.java, CheckoutPage.java
│   └── utils/          → LoggerUtil.java, ScreenshotUtil.java
├── test/java/
│   ├── base/           → BaseTest.java
│   ├── hooks/          → Hooks.java
│   └── stepdefs/       → LoginSteps.java, ProductSteps.java,
│                          CheckoutSteps.java
├── test/resources/
│   ├── features/       → login.feature, product.feature,
│   │                      checkout.feature, negative.feature
│   ├── config/         → config.properties, qa.properties
│   └── log4j2.xml
├── testng.xml
└── pom.xml
```

---

## How to Run

**Run all tests (default environment: qa, default browser: chrome):**
```bash
mvn test
```

**Run only @smoke tagged scenarios:**
```bash
mvn test -Dtags=@smoke
```

**Run only @regression tagged scenarios:**
```bash
mvn test -Dtags=@regression
```

**Run against the QA environment:**
```bash
mvn test -Denv=qa
```

**Run a specific feature:**
```bash
mvn test -Dcucumber.features=src/test/resources/features/login.feature
```

---

## Environment Switching

The framework supports multiple environments via a `-Denv` JVM argument. At startup, `ConfigReader.java` checks `System.getProperty("env", "qa")` and loads the corresponding properties file from `src/test/resources/config/`.

| Flag | Properties File Loaded | Use Case |
|---|---|---|
| `-Denv=qa` *(default)* | `qa.properties` | QA / test environment |

The properties file defines values for:

```properties
base.url=https://www.saucedemo.com
username=standard_user
password=secret_sauce
browser=chrome
implicit.wait=10
```

To add a new environment (e.g. `staging`), create `staging.properties` in `src/test/resources/config/` and run with `-Denv=staging` — no code changes required.

---

## Reporting

### Cucumber HTML Report
After every test run, an HTML report is auto-generated at:
```
target/cucumber-reports/report.html
```
Open it in any browser for a full scenario-by-scenario breakdown with pass/fail status and step details.

### JSON Report
A machine-readable JSON report is also produced at:
```
target/cucumber-reports/report.json
```
This can be consumed by CI tools (Jenkins, GitHub Actions) or third-party dashboards.

### Screenshots
Screenshots are captured automatically on **test failure** by `Hooks.java` and saved to:
```
target/screenshots/<ScenarioName>_<timestamp>.png
```
Screenshots are named by scenario and timestamped so multiple failures never overwrite each other.

### Logs
Log4j2 writes runtime logs to both the console and:
```
logs/test.log
```
Log level and appender configuration lives in `src/test/resources/log4j2.xml`.