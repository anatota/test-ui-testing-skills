# SauceDemo Playwright Java Boilerplate

Java UI test boilerplate using Playwright, JUnit 5, Maven, and the Page Object
Model.

## Prerequisites

- Java 17+
- Maven 3.8+

## Run

Install Playwright's Chromium browser:

```bash
mvn test-compile exec:java \
  -Dexec.mainClass=com.microsoft.playwright.CLI \
  -Dexec.classpathScope=test \
  -Dexec.args="install chromium"
```

Run all tests:

```bash
mvn test
```

Run in headed mode:

```bash
mvn test -Dheadless=false
```

Run with another browser or a delay between actions:

```bash
mvn test -Dbrowser=firefox -DslowMo=250
```

Supported browser values are `chromium`, `firefox`, and `webkit`. Default
settings are in `src/test/resources/test.properties`; command-line system
properties override them.

## Project Structure

```text
src/test/java/com/saucedemo/
├── config/     # Environment and browser configuration
├── pages/      # Page objects
└── tests/      # Test fixtures and test cases
```

Add new page objects under `pages` and test classes extending `BaseTest` under
`tests`.
