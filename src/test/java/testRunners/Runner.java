package testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features = ".//Features/MyTest.feature",
        features = "/Users/tatiana/myCourseProjectOnCucumber/src/test/resources/Features/MyTest.feature",
        glue = "stepDefinitions",
        dryRun = false,
        monochrome = true,
        plugin = {"pretty", "html:test-output"})
public class Runner {
}
