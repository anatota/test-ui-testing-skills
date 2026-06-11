package com.saucedemo.tests;

import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AuthenticationTest extends BaseTest {

    @Test
    public void userCanSignInWithValidCredentialsAndOpenDashboard() {
        InventoryPage inventoryPage = new LoginPage(page)
                .open()
                .loginAs("standard_user", "secret_sauce");

        assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");
        assertThat(inventoryPage.title()).hasText("Products");
        assertThat(inventoryPage.inventoryItems()).hasCount(6);
    }

    @Test
    public void invalidCredentialsDisplayAnError() {
        LoginPage loginPage = new LoginPage(page)
                .open()
                .attemptLoginAs("invalid_user", "wrong_password");

        assertThat(page).hasURL("https://www.saucedemo.com/");
        assertThat(loginPage.errorMessage()).hasText(
                "Epic sadface: Username and password do not match any user in this service");
    }
}
