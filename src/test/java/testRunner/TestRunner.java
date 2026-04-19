package testRunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests; // Removed .api
import io.cucumber.testng.CucumberOptions;             // Removed .api

@CucumberOptions
(features = "src/test/resources/features",
		glue="stepDefinition",monochrome=true,dryRun=false,
		plugin= {"pretty","html:target/cucumber.html",    // Basic browser report
		        "json:target/cucumber.json"    // Data for Jenkins graphs
		         }  
		
		)
//mvn test -Dcucumber.features.retry=1
public class TestRunner extends AbstractTestNGCucumberTests{
	@Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        // This line tells the system to look for the retry property in the code
        System.setProperty("cucumber.features.retry", "1"); 
        return super.scenarios();
    }

}
