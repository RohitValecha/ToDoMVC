package com.singtel.todomvc.web.run;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber/", "json:target/report/cucumber.json", "junit:target/report/cucumber.xml"},
        features = {"src/test/resources/com.singtel.todomvc.features"},
        glue = {"com.singtel.todomvc.web.steps"}
)

public class todoMvcRunner {
}

