package com.saucedemo.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.saucedemo.config.TestConfig;

public class LoginPage {
    private final Page page;
    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator errorMessage;

    public LoginPage(Page page) {
        this.page = page;
        usernameInput = page.getByTestId("username");
        passwordInput = page.getByTestId("password");
        loginButton = page.getByTestId("login-button");
        errorMessage = page.getByTestId("error");
    }

    public LoginPage open() {
        page.navigate(TestConfig.baseUrl());
        return this;
    }

    public InventoryPage loginAs(String username, String password) {
        usernameInput.fill(username);
        passwordInput.fill(password);
        loginButton.click();
        return new InventoryPage(page);
    }

    public LoginPage attemptLoginAs(String username, String password) {
        usernameInput.fill(username);
        passwordInput.fill(password);
        loginButton.click();
        return this;
    }

    public Locator loginButton() {
        return loginButton;
    }

    public Locator errorMessage() {
        return errorMessage;
    }
}
