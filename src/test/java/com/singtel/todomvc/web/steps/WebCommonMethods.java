package com.singtel.todomvc.web.steps;

import com.singtel.todomvc.web.utils.WebDriverInitializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.function.Function;

public class WebCommonMethods extends WebDriverInitializer {
    final static Log logger = LogFactory.getLog(WebCommonMethods.class);


    public WebElement fluentWait(final By locator) throws Error {
        new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
                .until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        WebElement element = driver.findElement(locator);
                        return element != null;
                    }
                });
        return driver.findElement(locator);
    }


    public void doubleClickAndUpdateTask(WebElement element, String newTask) {
        Actions act = new Actions(driver);
        String OS = "";
        if (System.getProperty("OS") == null) {
            OS = environment.getProperty("OS");
        } else {
            OS = System.getProperty("OS");
        }
        if (OS.equalsIgnoreCase("mac")) {
            act.doubleClick(element)
                    .keyDown(Keys.COMMAND)
                    .sendKeys("a")
                    .keyUp(Keys.COMMAND)
                    .sendKeys(Keys.BACK_SPACE)
                    .sendKeys(newTask)
                    .sendKeys(Keys.ENTER)
                    .build()
                    .perform();
            logger.info("Task Edited");
        } else if (OS.equalsIgnoreCase("windows")) {
            act.doubleClick(element)
                    .keyDown(Keys.CONTROL)
                    .sendKeys("a")
                    .keyUp(Keys.CONTROL)
                    .sendKeys(Keys.BACK_SPACE)
                    .sendKeys(newTask)
                    .sendKeys(Keys.ENTER)
                    .build()
                    .perform();
            logger.info("Task Edited");
        }
    }
}
