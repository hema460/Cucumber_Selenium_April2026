package rahulshettyacademy.AbstractComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import rahulshettyacademy.PageObjects.LandingPage;

public class BaseTest {
	public static WebDriver driver;
	public static Properties prop;
	public static LandingPage landingPage;
	public BaseTest() throws IOException {
		FileInputStream fis=new FileInputStream(".//src/test/resources/config.properties");
		// OR FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		prop=new Properties();
		
			prop.load(fis);
			fis.close();
		
	}
	
	public WebDriver intializeDriver() {
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--start-maximized");
		
		String browserName = System.getProperty("browserName") != null 
                ? System.getProperty("browserName") 
                : prop.getProperty("browserName");
		if(browserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver(options);
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		else {
			driver=new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get(prop.getProperty("url"));
		return driver;
	}
	
	
	
	
	
	

}
