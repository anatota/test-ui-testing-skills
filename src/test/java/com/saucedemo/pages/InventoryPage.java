package com.saucedemo.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class InventoryPage {
    private final Page page;

    public InventoryPage(Page page) {
        this.page = page;
    }

    public Locator title() {
        return page.getByTestId("title");
    }

    public Locator inventoryItems() {
        return page.getByTestId("inventory-item");
    }
}
