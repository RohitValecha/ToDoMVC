package com.singtel.todomvc.web.steps;

import com.singtel.todomvc.web.utils.ElementContainer;
import com.singtel.todomvc.web.utils.WebDriverInitializer;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.List;

public class WebCommonSteps extends WebCommonMethods {
    final static Log logger = LogFactory.getLog(WebCommonSteps.class);


    @After
    public void tearDown(Scenario sc) throws Throwable {
        // Initialize the value of random variables

        if (driver != null) {
            if (sc.isFailed()) {
                try {
                    final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    sc.embed(screenshot, "image/png"); // stick it in the report
                    sc.write(driver.getCurrentUrl());
                    logger.info(driver.getCurrentUrl());

                } catch (WebDriverException wde) {
                    System.err.println(wde.getMessage());
                } catch (ClassCastException cce) {
                    cce.printStackTrace();
                }
            }
            driver.quit();
        }
    }

    @Given("^user opens the TODO List$")
    public void userOpensTheTODOList() throws Throwable {
        //Open and verify the webpage
        if (driver != null) {
            driver = null;
        }
        WebDriverInitializer.initializeWebDriver();
        String url = environment.getProperty("TodoMVCURL");
        driver.get(url);
        WebElement el = fluentWait(By.xpath(ElementContainer.getElementLocator("ToDos")));
        Assert.assertTrue(el.isDisplayed(), "Page Did not load correctly");
    }


    @When("^user adds TODOs$")
    public void userAddsTODOs(List<String> todoList) throws NoSuchFieldException {
        //Add the tasks mentioned in feature file
        WebElement el = fluentWait(By.xpath(ElementContainer.getElementLocator("ToDoText")));
        for (int i = 1; i <= todoList.size(); i++) {
            el.sendKeys(todoList.get(i - 1));
            el.sendKeys(Keys.ENTER);
        }
    }

    @Then("^his TODO List should contain$")
    public void hisTODOListShouldContain(List<String> todoList) throws NoSuchFieldException {
        for (int i = 1; i <= todoList.size(); i++) {
            //Verify if the list contains the added tasks
            WebElement el = fluentWait(By.xpath("//*[text()='" + todoList.get(i - 1) + "']"));
            Assert.assertEquals(el.getText(), todoList.get(i - 1), "Incorrrect Values");
            //Verify the number of Tasks in List
            Assert.assertEquals(fluentWait(By.xpath(ElementContainer.getElementLocator("NumberOfItems"))).getText(),String.valueOf(todoList.size()), "Number of items are incorrect in the list");
        }
    }

    @Then("^Complete the following TODOs$")
    public void completeTheFollowingTODOs(List<String> todoList) throws NoSuchFieldException {
        //Complete the individual tasks
        for (int i = 1; i <= todoList.size(); i++) {
            WebElement ele = fluentWait(By.xpath("//*[text()='" + todoList.get(i - 1) + "']/preceding-sibling::input[@type='checkbox']"));
            ele.click();
        }
    }

    @Then("^Verify if the Completed tasks has$")
    public void verifyIfTheCompletedTasksHas(List<String> todoList) throws NoSuchFieldException {
        //Click on the Completed link first
        WebElement el = fluentWait(By.xpath(ElementContainer.getElementLocator("CompletedLink")));
        el.click();
        //Verify if the task which were marked completed does not exists in this list
        for (int i = 1; i <= todoList.size(); i++) {
            WebElement CompletedTask = fluentWait(By.xpath("//*[text()='" + todoList.get(i - 1) + "']"));
            Assert.assertTrue(CompletedTask.isDisplayed(), "Task does not exist in this list");
        }
    }

    @Then("^Complete all the TODOs in the List$")
    public void completeAllTheTODOsInTheList() throws NoSuchFieldException {
        //Click on the Button to complete all tasks together
        WebElement el = fluentWait(By.xpath(ElementContainer.getElementLocator("ToggleAll")));
        el.click();
    }

    @Then("^Clear all the completed tasks in the List$")
    public void clearAllTheCompletedTasksInTheList() throws NoSuchFieldException {
        //Click on the Clear completed to clear all the completed tasks together
        WebElement el = fluentWait(By.xpath(ElementContainer.getElementLocator("ClearCompleted")));
        el.click();
    }


    @Then("^Verify if the task list does not have following tasks$")
    public void verifyIfTheTaskListDoesNotHaveFollowingTasks(List<String> todoList) throws NoSuchFieldException {
        //Verify if the tasks which were marked completed and then Cleared does not exist in the list
        for (int i = 1; i <= todoList.size(); i++) {
            try {
                WebElement CompletedTask = driver.findElement(By.xpath("//*[text()='" + todoList.get(i - 1) + "']"));
            } catch (NoSuchElementException e) {
                logger.info("Task: " + todoList.get(i - 1) + " does not exist in the list");
            }
        }
    }

    @When("^user wants to edit \"([^\"]*)\" and change$")
    public void userWantsToEditAndChange(String oldTask, List<String> todoList) {
        //Double click on existing todo task and edit
        for (int i = 1; i <= todoList.size(); i++) {
            WebElement ele = fluentWait(By.xpath("//*[text()='" + oldTask + "']"));
            doubleClickAndUpdateTask(ele, todoList.get(i - 1));
        }
    }

    @When("^user wants to delete TODO$")
    public void userWantsToDeleteTODO(List<String> todoList) {
        //Delete the existing todo tasks
        for (int i = 1; i <= todoList.size(); i++) {
            WebElement ele = fluentWait(By.xpath("//*[text()='" + todoList.get(i - 1) + "']/following-sibling::button[@class='destroy']"));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", ele);
        }
    }

    @Then("^Verify if the Active tasks does not have the following TODO$")
    public void verifyIfTheActiveTasksDoesNotHaveTheFollowingTODO(List<String> todoList) throws NoSuchFieldException {
        //Click on the Active link first
        WebElement el = fluentWait(By.xpath(ElementContainer.getElementLocator("ActiveLink")));
        el.click();
        //Verify if the task which were marked completed does not exists in this list
        for (int i = 1; i <= todoList.size(); i++) {
            try {
                WebElement CompletedTask = driver.findElement(By.xpath("//*[text()='" + todoList.get(i - 1) + "']"));
            } catch (NoSuchElementException e) {
                logger.info("Task: " + todoList.get(i - 1) + " does not exist in the Active list");
            }
        }
    }

    @Then("^Verify if the Active tasks have the following TODO$")
    public void verifyIfTheActiveTasksHaveTheFollowingTODO(List<String> todoList) throws NoSuchFieldException {
        //Click on the Active link first
        WebElement el = fluentWait(By.xpath(ElementContainer.getElementLocator("ActiveLink")));
        el.click();
        //Verify if the task which were not marked completed exists in this list
        for (int i = 1; i <= todoList.size(); i++) {
            WebElement CompletedTask = fluentWait(By.xpath("//*[text()='" + todoList.get(i - 1) + "']"));
            Assert.assertTrue(CompletedTask.isDisplayed(), "Task" + todoList.get(i - 1) + " does not exists in this list");
            logger.info("Task: " + todoList.get(i - 1) + " exists in the Active list");

        }
    }

    @Then("^Verify if the All tasks have the following TODO$")
    public void verifyIfTheAllTasksHaveTheFollowingTODO(List<String> todoList) throws NoSuchFieldException {
        //Click on the All link first
        WebElement el = fluentWait(By.xpath(ElementContainer.getElementLocator("AllLink")));
        el.click();
        //Verify all the tasks completed and not completed exist in this link
        for (int i = 1; i <= todoList.size(); i++) {
            WebElement Task = fluentWait(By.xpath("//*[text()='" + todoList.get(i - 1) + "']"));
            Assert.assertTrue(Task.isDisplayed(), "Task" + todoList.get(i - 1) + " does not exists in this list");
            logger.info("Task: " + todoList.get(i - 1) + " exists in the All list");

        }
    }


    @And("^then wait for (\\d+) seconds$")
    public void thenWaitForSeconds(int seconds) throws InterruptedException {
        this.logger.info("Waiting for " + seconds + "seconds");
        Thread.sleep((long) (seconds * 1000));
    }

    @And("^Verify if the number of Task list has \"([^\"]*)\" items$")
    public void verifyIfTheNumberOfTaskListHasItems(String itemNumber) throws Throwable {
        Assert.assertEquals(fluentWait(By.xpath(ElementContainer.getElementLocator("NumberOfItems"))).getText(),itemNumber, "Number of items are incorrect in the list");
        logger.info("Task List has: " + itemNumber +" items left");
    }

    @Then("^mark UnComplete the following TODOs$")
    public void markUnCompleteTheFollowingTODOs(List<String> todoList) {
        //Mark Un Complete the individual tasks
        for (int i = 1; i <= todoList.size(); i++) {
            WebElement ele = fluentWait(By.xpath("//*[text()='" + todoList.get(i - 1) + "']/preceding-sibling::input[@type='checkbox']"));
            ele.click();
        }
    }
}

