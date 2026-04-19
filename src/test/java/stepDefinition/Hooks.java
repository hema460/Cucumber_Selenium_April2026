package stepDefinition;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import rahulshettyacademy.AbstractComponent.BaseTest;
import rahulshettyacademy.PageObjects.LandingPage;

public class Hooks extends BaseTest {

	public Hooks() throws IOException {
		super();
		
	}
	@Before
	public void launchApplication() {
		driver=intializeDriver();
		 landingPage=new LandingPage(driver);
		
		
	}
	@After
	 public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            // This is the "Listener" replacement
            // It attaches the screenshot directly to the HTML/JSON reports
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed_Step_Screenshot");
        }
        
        if (driver != null) {
            driver.quit(); 
        }
    }

}
