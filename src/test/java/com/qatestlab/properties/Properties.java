package com.qatestlab.properties;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.remote.BrowserType;

import java.nio.file.Paths;

public class Properties {

    public static String getBrowser() {
        String name = System.getProperty(PropertiesNames.BROWSER.toString());

        if (name == null)
            return BrowserType.FIREFOX;

        switch (name) {
            case BrowserType.SAFARI:
                return BrowserType.SAFARI;
            case BrowserType.IE:
                return BrowserType.IE;
            case BrowserType.CHROME:
                return BrowserType.CHROME;
            default:
                return BrowserType.FIREFOX;
        }
    }

    private static String getDriversDir() {
        return System.getProperty(PropertiesNames.DRIVERS_DIR.toString());
    }

    public static String getChromeDriverPath() {
        String basePath = getDriversDir();
        if (basePath == null)
            return null;

        if (SystemUtils.IS_OS_WINDOWS) {
            return Paths.get(basePath, "chromedriver.exe").toString();
        }
        if (SystemUtils.IS_OS_LINUX) {
            return Paths.get(basePath, "chromedriver_linux").toString();
        }
        if (SystemUtils.IS_OS_MAC) {
            return Paths.get(basePath, "chromedriver_mac").toString();
        }

        return null;
    }

    public static String getIEDriverPath(){
        return Paths.get(getDriversDir(),"IEDriverServer.exe").toString();
    }

    public static String getBaseUrl() {
        String login = System.getProperty(PropertiesNames.LOGIN.toString(), "Unomytest");
        String password = System.getProperty(PropertiesNames.PASSWORD.toString(), "zxcvb123");
        String base_url = System.getProperty(PropertiesNames.BASE_URL.toString(), "https://beta.unomy.com/");
        String url = base_url.substring(0, 8) + login + ":" + password + "@" + base_url.substring(8);
        return url;
    }

    public static String getConfigDir() {
        return System.getProperty(PropertiesNames.CONFIG_DIR.toString());
    }

    public static String getHubUrl() {
        return System.getProperty(PropertiesNames.HUB.toString());
    }

    public static String getPlatform() {
        return System.getProperty(PropertiesNames.PLATFORM.toString());
    }

}
