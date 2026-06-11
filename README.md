# SauceDemo Playwright Java Boilerplate

Java UI test boilerplate using Playwright, TestNG, Maven, and the Page Object
Model.

## Prerequisites

- Java 17+
- Maven 3.8+
- Node.js 18+ with npm (for project-local Codex Playwright skills)

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

## Project-Local UI Testing Skills

The project includes `playwright` and `playwright-interactive` under
`.codex/skills`. Restart Codex from this project directory to discover them.

Verify or reinstall the Node Playwright runtime and Chromium:

```bash
npm run ui:verify
npm run ui:install
```

Use the CLI skill wrapper directly:

```bash
npm run ui:cli -- open https://www.saucedemo.com/ --headed
npm run ui:cli -- snapshot
npm run ui:cli -- screenshot
npm run ui:cli -- close
```

Run the Java/TestNG suite with `mvn test` or `npm test`. The Java suite remains
the project test framework; the Node dependency supports the local Codex skills.

The interactive skill additionally requires a new Codex session with `js_repl`
enabled and temporary `danger-full-access` sandbox mode, as documented in its
`SKILL.md`. No dedicated accessibility skill was present in the inspected skill
repository; Playwright snapshots can still support accessibility-tree inspection.

On Linux, Playwright may warn that `libwoff1` is missing. Install it at the host
level only if browser rendering or font checks require it.

## Project Structure

```text
src/test/java/com/saucedemo/
├── config/     # Environment and browser configuration
├── pages/      # Page objects
└── tests/      # Test fixtures and test cases
```

Add new page objects under `pages` and test classes extending `BaseTest` under
`tests`.
