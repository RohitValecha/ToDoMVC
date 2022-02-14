package com.singtel.todomvc.web.run;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(jsonReport = "target/test-report/premium/test-report.json", overviewReport = true, outputFolder = "target/reports", reportPrefix = "todomvc-DATE(dd-MMM-yyyy)", retryCount = 1, detailedAggregatedReport = true, coverageReport = true, detailedReport = true)
@CucumberOptions(
        plugin = {"pretty", "html:target/test-report/premium", "json:target/test-report/premium/test-report.json",
                "junit:target/test-report/premium/test-report.xml"}, strict = true,
        features = {"src/test/resources/com.singtel.todomvc.features"},
        glue = {"com.singtel.todomvc.web.steps"}
)

public class todoMvcRunner {
}

