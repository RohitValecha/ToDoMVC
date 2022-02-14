package com.singtel.todomvc.web.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriverService;

import java.io.File;

public class WebDriverInitializer {
    public static Environment environment = Environment.getInstance("application.properties");
    final static Log logger = LogFactory.getLog(WebDriverInitializer.class);
    protected static WebDriver driver = null;
    public static Dimension d = new Dimension(1024, 768);
    protected static ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
            .usingDriverExecutable(
                    new File(System.getProperty("user.dir") + OsCheck.getOperatingSystemDriver("Chrome")))
            .usingAnyFreePort().build();
    protected static GeckoDriverService geckoDriverService = new GeckoDriverService.Builder()
            .usingDriverExecutable(
                    new File(System.getProperty("user.dir") + OsCheck.getOperatingSystemDriver("Firefox")))
            .usingAnyFreePort().build();
    protected static InternetExplorerDriverService ieDriverService = new InternetExplorerDriverService.Builder()
            .usingDriverExecutable(new File(System.getProperty("user.dir") + OsCheck.getOperatingSystemDriver("IE")))
            .usingAnyFreePort().build();

    public static WebDriver initializeWebDriver() throws Exception {

        if (driver == null) {
            String browserName = "";
            if (System.getProperty("Browser") == null) {
                browserName = environment.getProperty("Browser");
            } else {
                browserName = System.getProperty("Browser");
            }
            browserName = browserName.toLowerCase();
            switch (browserName) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(false);
                    driver = new ChromeDriver(chromeDriverService, options);
                    driver.manage().window().setSize(d);
                    logger.info("!!!!!!!!!!!!!!!!!!!!!Start");
                    break;
                case "firefox":
                    FirefoxOptions optionsff = new FirefoxOptions();
                    optionsff.setHeadless(false);
                    driver = new FirefoxDriver(geckoDriverService, optionsff);
                    driver.manage().window().setSize(d);
                    logger.info("!!!!!!!!!!!!!!!!!!!!!Start");
                    break;
                default:
                    logger.info("Default Chrome");
                    ChromeOptions optionsd = new ChromeOptions();
                    optionsd.setHeadless(false);
                    driver = new ChromeDriver(chromeDriverService, optionsd);
                    driver.manage().window().setSize(d);
                    logger.info("!!!!!!!!!!!!!!!!!!!!!Start");
                    break;
            }
        }
        return driver;

    }


}
