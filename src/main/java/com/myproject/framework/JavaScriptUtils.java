package com.myproject.framework;

import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Roman Onyshkiv
 */
@NoArgsConstructor
public class JavaScriptUtils {

    public static void execute(String command) {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(command);
    }

    public static void execute(String command, WebElement element) {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(command, element);
    }

    public static void click(WebElement element) {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", element);
    }

    public static void click(By by) {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebElement element = driver.findElement(by);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", element);
    }

    public static void sendKeys(String keys, WebElement element) {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].value='" + keys + "';", element);
    }

    public static boolean isPageReady(WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return executor.executeScript("return document.readyState").equals("complete");
    }

    public static boolean isAjaxReady(WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (Boolean) executor.executeScript("return jQuery.active==0");
    }


}
