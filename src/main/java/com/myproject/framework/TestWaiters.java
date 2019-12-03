package com.myproject.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Roman Onyshkiv
 */
public class TestWaiters {

    public static void waitFor(WebElement element, int timer) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }

    public static void waitFor(By by, int timer) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(by)));
    }

    public static void waitFor(String title, int timer) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.titleContains(title)));
    }

    public static void waitForClickable(By by, int timer) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(by)));
    }

    public static void waitForClickable(WebElement element, int timer) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }

    public static void waitForGone(By by, int timer) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOfElementLocated(by)));
    }

    public static void waitForUrl(String url, int seconds) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, seconds);
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains(url)));
    }

}
