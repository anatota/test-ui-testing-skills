package com.saucedemo.tests;

import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class NavigationTest extends BaseTest {

    @Test
    void navigationAndLogoutWork() {
        InventoryPage inventoryPage = new LoginPage(page)
                .open()
                .loginAs("standard_user", "secret_sauce");

        inventoryPage.openMenu().navigateToAllItems();
        assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");
        assertThat(inventoryPage.title()).hasText("Products");

        LoginPage loginPage = inventoryPage.logout();
        assertThat(page).hasURL("https://www.saucedemo.com/");
        assertThat(loginPage.loginButton()).isVisible();
    }
}
