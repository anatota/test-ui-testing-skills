package com.saucedemo.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class InventoryPage {
    private final Page page;
    private final Locator menuButton;
    private final Locator allItemsLink;
    private final Locator logoutLink;

    public InventoryPage(Page page) {
        this.page = page;
        menuButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Open Menu"));
        allItemsLink = page.getByTestId("inventory-sidebar-link");
        logoutLink = page.getByTestId("logout-sidebar-link");
    }

    public Locator title() {
        return page.getByTestId("title");
    }

    public Locator inventoryItems() {
        return page.getByTestId("inventory-item");
    }

    public InventoryPage openMenu() {
        menuButton.click();
        return this;
    }

    public InventoryPage navigateToAllItems() {
        allItemsLink.click();
        return this;
    }

    public LoginPage logout() {
        logoutLink.click();
        return new LoginPage(page);
    }
}
