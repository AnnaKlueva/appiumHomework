package anna.kliuieva.petproject.utils;

import anna.kliuieva.petproject.DriverProvider;
import com.google.common.base.Predicate;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Waiters {
    private static final Logger LOG = LoggerFactory.getLogger(Waiters.class);
    private static final long TIME__SECONDS_LIMIT = 20;
    private static final long SLEEP_IN_MILLIS = 500;

    /**
     * Wait for element to be visible.
     */
    public static void waitForAppear(WebElement webElement) {
        final WebDriverWait wait = new WebDriverWait(DriverProvider.INSTANCE.getDriverInstance(), TIME__SECONDS_LIMIT, SLEEP_IN_MILLIS);

        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (NoSuchElementException ex) {
            LOG.info("Element was not found during timeout");
        }
    }

    /**
     * Wait for element to be invisible.
     */
    public static void waitForDisappear(WebElement webElement) {
        final FluentWait<WebElement> elementWait = new FluentWait<>(webElement);
        elementWait.withTimeout(TIME__SECONDS_LIMIT, TimeUnit.SECONDS);
        elementWait.pollingEvery(SLEEP_IN_MILLIS, TimeUnit.MILLISECONDS);
        elementWait.until(new Predicate<WebElement>() {

            @Override
            public boolean apply(final WebElement typifiedElement) {
                try {
                    return Boolean.valueOf(!typifiedElement.isDisplayed());
                } catch (NoSuchElementException var3) {
                    return Boolean.valueOf(true);
                } catch (StaleElementReferenceException var4) {
                    return Boolean.valueOf(true);
                }
            }
        });
    }

    /**
     * Verify if element displayed
     */
    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (final NoSuchElementException e) {
            LOG.info("Element isn't displayed on the page");
            return false;
        }
    }
}
