package io.loop.utilities.utilities;

import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.junit.Assert.assertTrue;


public class BrowserUtils {

    public static Scenario myScenario;
    public static void takeScreenshot(){
        try {
            myScenario.log("Current url is: " + Driver.getDriver().getCurrentUrl());
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            myScenario.attach(screenshot, "image/png", myScenario.getName());

        } catch (WebDriverException wbd){
            wbd.getMessage();

        } catch (ClassCastException cce){
            cce.getMessage();

        }
    }

    /**
     * validate if the driver switched to the expected url or title
     *
     * @param driver
     * @param expectedUrl
     * @param expectedTitle
     * @author NSH
     * implements assertion
     */
    public static void switchWindowAndValidate(WebDriver driver, String expectedUrl, String expectedTitle) {
        // to lowercase the params in order to avoid miss type
        expectedTitle = expectedTitle.toLowerCase();
        expectedUrl = expectedUrl.toLowerCase();

        // get all window handles, switch one by one and each time check if the url matches expected to stop
        // var windowHandles = driver.getWindowHandles();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String each : windowHandles) {
            driver.switchTo().window(each);
            if (driver.getCurrentUrl().toLowerCase().contains(expectedUrl)) {
                break;
            }
        }
        // after stopping on expected url, validate the title
        assertTrue(driver.getTitle().toLowerCase().contains(expectedTitle));
    }

    /**
     * @param driver
     * @param targetTitle
     * @author NSH
     */
    public static void switchToWindow(WebDriver driver, String targetTitle) {
        String origin = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().contains(targetTitle)) {
                return;
            }
        }
        driver.switchTo().window(origin);
    }

    /**
     * Clicks any link from Loop Practice by its visible text
     *
     * @param nameOfPage the exact text of the link
     */
    public static void loopLinkClick(String nameOfPage) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[.='" + nameOfPage + "']")
        ));
        element.click();
    }

    /**
     * Wait for an element to be clickable
     *
     * @param element element to wait for
     * @param seconds timeout in seconds
     * @return the clickable element
     */
    public static WebElement waitForClickable(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Wait for an element to be visible
     *
     * @param element element to wait for
     * @param seconds timeout in seconds
     * @return the visible element
     */
    public static WebElement waitForVisibility(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait for an element to become invisible
     *
     * @param element element to wait for
     * @param seconds timeout in seconds
     */
    public static void waitForInvisibility(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
}