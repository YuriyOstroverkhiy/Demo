package com.qatestlab.base;


import com.qatestlab.properties.Properties;
import com.unomy.actions.Actions;
import com.unomy.pages.Pages;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    private static WebDriver driver;
    public static boolean isDriverIE = false;

    public static WebDriver driver() {
        return driver;
    }

    protected CustomRemoteWebDriver setupRemoteDriver() {
        String platformName = Properties.getPlatform();
        Platform platform =  (platformName != null) ? Platform.valueOf(platformName) : Platform.ANY;

        String url = Properties.getHubUrl();
        Assert.assertNotNull(url, "Cannot setup remote webdriver with no hub URL!");
        Assert.assertNotEquals(url, "", "Cannot setup remote webdriver with empty hub URL!");

        String name = Properties.getBrowser();

        DesiredCapabilities capabilities;
        switch (name) {
            case BrowserType.SAFARI:
                capabilities = DesiredCapabilities.safari();
                capabilities.setBrowserName(name);
            case BrowserType.IE:
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setBrowserName("internet explorer");
                capabilities.setCapability("nativeEvents", false);
            case BrowserType.CHROME:
                capabilities = DesiredCapabilities.chrome();
                capabilities.setBrowserName(name);
            default:
                capabilities = DesiredCapabilities.firefox();
                capabilities.setBrowserName(name);
        }
        capabilities.setPlatform(platform);
        capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);

        try {
            return new CustomRemoteWebDriver(new URL(url), capabilities);
        } catch (MalformedURLException e) {
            skipTest("Cannot setup remote webdriver using incorrect hub URL! " + e.getMessage());
        }

        return null;
    }

    protected WebDriver setupDriver() {
        String name = Properties.getBrowser();

        switch (name) {
            case BrowserType.SAFARI:
                return new SafariDriver();
            case BrowserType.IE:
                String ieDriver = Properties.getIEDriverPath();
                Assert.assertNotNull(ieDriver, "Unable to determine the path to IEdriver!");


                System.setProperty("webdriver.ie.driver", ieDriver);
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability("nativeEvents", false);
                return new InternetExplorerDriver(capabilities);
            case BrowserType.CHROME:
                String chromeDriver = Properties.getChromeDriverPath();
                Assert.assertNotNull(chromeDriver, "Unable to determine the path to chromedriver!");

                System.setProperty("webdriver.chrome.driver", chromeDriver);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");
                return new ChromeDriver(options) {
                    @Override
                    public WebElement findElement(By by) {
                        try {
                            return by.findElement(this);
                        } catch (org.openqa.selenium.NoSuchElementException nse) {
                            Field f = null;
                            try {
                                f = Throwable.class.getDeclaredField("detailMessage");
                            } catch (NoSuchFieldException e) {
                                throw nse;
                            }
                            f.setAccessible(true);
                            try {
                                String error = "\n" + by.toString() + "\n" + f.get(nse);
                                f.set(nse, error);
                            } catch (IllegalAccessException ia) { }
                            throw nse;
                        }
                    }
                };
            default:
                return new FirefoxDriver();
        }
    }


    @BeforeClass
    public void setUp() {
        String hubUrl = Properties.getHubUrl();

        driver = (hubUrl == null || hubUrl.isEmpty())
                ? setupDriver()
                : setupRemoteDriver();
        Assert.assertNotNull(driver, "Webdriver is not set up!");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(BasePage.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(BasePage.FOUR_MINUTES, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
        stopDriver();
    }


    protected void stopDriver() {
        Actions.clear();
        Pages.clear();
        driver.quit();
    }

    protected void skipTest(String message) {
        throw new SkipException(message);
    }
}
