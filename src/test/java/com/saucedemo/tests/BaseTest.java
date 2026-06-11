package com.saucedemo.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.saucedemo.config.TestConfig;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected Page page;

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;

    @BeforeClass
    public void launchBrowser() {
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

    @BeforeMethod
    public void createContextAndPage() {
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1440, 900));
        page = context.newPage();
    }

    @AfterMethod(alwaysRun = true)
    public void closeContext() {
        if (context != null) {
            context.close();
        }
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        if (playwright != null) {
            playwright.close();
        }
    }
}
