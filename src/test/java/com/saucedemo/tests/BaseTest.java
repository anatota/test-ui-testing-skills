package com.saucedemo.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.saucedemo.config.TestConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
    protected Page page;

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;

    @BeforeAll
    void launchBrowser() {
        playwright = Playwright.create();
        playwright.selectors().setTestIdAttribute("data-test");

        BrowserType browserType = switch (TestConfig.browser()) {
            case "chromium" -> playwright.chromium();
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();
            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + TestConfig.browser());
        };

        browser = browserType.launch(new BrowserType.LaunchOptions()
                .setHeadless(TestConfig.headless())
                .setSlowMo(TestConfig.slowMo()));
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1440, 900));
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        if (context != null) {
            context.close();
        }
    }

    @AfterAll
    void closeBrowser() {
        if (playwright != null) {
            playwright.close();
        }
    }
}
