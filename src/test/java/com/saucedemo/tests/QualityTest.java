package com.saucedemo.tests;

import com.deque.html.axecore.playwright.AxeBuilder;
import com.deque.html.axecore.results.AxeResults;
import com.deque.html.axecore.results.Rule;
import com.microsoft.playwright.ConsoleMessage;
import com.saucedemo.pages.LoginPage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class QualityTest extends BaseTest {
    private static final Set<String> SERIOUS_IMPACTS = Set.of("serious", "critical");

    @Test
    void authenticatedFlowHasNoUnexpectedConsoleErrors() {
        List<String> consoleErrors = new ArrayList<>();
        page.onConsoleMessage(message -> {
            if ("error".equals(message.type()) && !isKnownTelemetryError(message)) {
                consoleErrors.add(message.text() + " @ " + message.location());
            }
        });

        new LoginPage(page)
                .open()
                .loginAs("standard_user", "secret_sauce")
                .openMenu()
                .navigateToAllItems();

        assertTrue(consoleErrors.isEmpty(),
                () -> "Unexpected browser console errors: " + consoleErrors);
    }

    @Test
    void dashboardHasNoSeriousAccessibilityViolations() {
        new LoginPage(page)
                .open()
                .loginAs("standard_user", "secret_sauce");

        AxeResults results = new AxeBuilder(page).analyze();
        List<Rule> seriousViolations = results.getViolations().stream()
                .filter(rule -> SERIOUS_IMPACTS.contains(rule.getImpact()))
                .toList();

        assertTrue(seriousViolations.isEmpty(),
                () -> "Serious accessibility violations: " + seriousViolations);
    }

    private boolean isKnownTelemetryError(ConsoleMessage message) {
        return message.location().contains("events.backtrace.io/");
    }
}
