package com.myproject.framework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.myproject.framework.SupportedBrowsers.*;

/**
 * @author Roman Onyshkiv
 */
@SuppressWarnings("varargs")
public class CreateDriver {

    private static CreateDriver instance = null;
    @Setter
    private String browserHandle = null;
    private static final int IMPLICIT_TIMEOUT = 0;
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private ThreadLocal<AppiumDriver<MobileElement>> mobileDriver = new ThreadLocal<>();
    private ThreadLocal<String> sessionId = new ThreadLocal<>();
    @Getter
    private ThreadLocal<String> sessionBrowser = new ThreadLocal<>();
    private ThreadLocal<String> sessionPlatform = new ThreadLocal<>();
    private ThreadLocal<String> sessionVersion = new ThreadLocal<>();
    private String getEnv = null;
    private String getPlatform = null;

    private CreateDriver() {
    }

    public static CreateDriver getInstance() {
        if (instance == null) {
            instance = new CreateDriver();
        }
        return instance;
    }

    public final void setDriver(SupportedBrowsers browser, String environment, String platform, Map<String, Object> optPreferences) throws Exception {
        getPlatform = platform;
        getEnv = environment;
        DesiredCapabilities caps = null;
        String localHub = "http://localhost:4444/wd/hub";
        switch (browser) {
            case FIREFOX:
//                WebDriverManager.firefoxdriver().setup();
                caps = DesiredCapabilities.firefox();
                FirefoxOptions options = new FirefoxOptions();
                FirefoxProfile profile = new FirefoxProfile();
                profile.setAcceptUntrustedCertificates(true);
                profile.setAssumeUntrustedCertificateIssuer(true);
                profile.setPreference("network.proxy.type", 0);
                options.setCapability(FirefoxDriver.PROFILE, profile);
                webDriver.set(new FirefoxDriver(options.merge(caps)));
                break;
            case CHROME:
//                WebDriverManager.chromedriver().setup();
                caps = DesiredCapabilities.chrome();
                ChromeOptions chromeOptions = new ChromeOptions();
                Map<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("credentials_enable_service", true);
                chromeOptions.setExperimentalOption("pref", chromePrefs);
                chromeOptions.addArguments("--disable-plugins", "--disable-extentions", "--disable-popup-blocking");
                caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                caps.setCapability("applicationCacheEnabled", false);
                webDriver.set(new ChromeDriver(chromeOptions.merge(caps)));
                break;
            case SAFARI:
                caps = DesiredCapabilities.safari();
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setUseTechnologyPreview(true);
                caps.setCapability(SafariOptions.CAPABILITY, safariOptions);
                caps.setCapability("autoAcceptAlerts", true);
                webDriver.set(new SafariDriver(safariOptions.merge(caps)));
                break;
            case IPHONE:
            case IPAD:
                if (browser.equals(IPHONE)) {
                    caps = DesiredCapabilities.iphone();
                } else {
                    caps = DesiredCapabilities.ipad();
                }
                mobileDriver.set(new IOSDriver<>(caps));
                break;
            case ANDROID:
                caps = DesiredCapabilities.android();
                mobileDriver.set(new AndroidDriver<>(caps));
            default:
                throw new IllegalStateException("Unexpected value: " + browser);
        }
    }

    public void setDriver(WebDriver driver) {
        webDriver.set(driver);

        sessionId.set(((RemoteWebDriver) webDriver.get()).getSessionId().toString());
        sessionBrowser.set(((RemoteWebDriver) webDriver.get()).getCapabilities().getBrowserName());
        sessionPlatform.set(((RemoteWebDriver) webDriver.get()).getCapabilities().getPlatform().toString());
        setBrowserHandle(getDriver().getWindowHandle());
    }



    public void setDriver(AppiumDriver<MobileElement> driver) {
        mobileDriver.set(driver);
        sessionId.set(mobileDriver.get().getSessionId().toString());
        sessionBrowser.set(mobileDriver.get().getCapabilities().getBrowserName());
        sessionPlatform.set(mobileDriver.get().getCapabilities().getPlatform().toString());
    }

    public WebDriver getDriver() {
        return webDriver.get();
    }

    public AppiumDriver<MobileElement> getDriver(boolean mobile) {
        return mobileDriver.get();
    }

    public WebDriver getCurrentDriver() {
        if ((getInstance().getSessionBrowser().contains("iphone")) || getInstance().getSessionBrowser().contains("ipad")
                || getInstance().getSessionBrowser().contains("android")) {
            return getInstance().getDriver(true);
        } else {
            return getInstance().getDriver();
        }
    }


    public void driverWait(long seconds) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void driverRefresh() {
        getCurrentDriver().navigate().refresh();
    }

    public void closeDriver() {
        try {
            getCurrentDriver().quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSessionId() {
        return sessionId.get();
    }

    public String getSessionBrowser() {
        return sessionBrowser.get();
    }

    public String getSessionVersion() {
        return sessionVersion.get();
    }

    public String getSessionPlatform() {
        return sessionPlatform.get();
    }

}
