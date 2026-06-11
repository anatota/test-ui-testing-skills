package com.saucedemo.tests;

import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class LoginTest extends BaseTest {

    @Test
    void standardUserCanLogIn() {
        InventoryPage inventoryPage = new LoginPage(page)
                .open()
                .loginAs("standard_user", "secret_sauce");

        assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");
        assertThat(inventoryPage.title()).hasText("Products");
        assertThat(inventoryPage.inventoryItems()).hasCount(6);
    }
}
