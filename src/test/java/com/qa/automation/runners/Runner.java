package com.qa.automation.runners;
import org.apache.log4j.BasicConfigurator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import com.qa.automation.utils.ConfigFileReader;

//import cucumber.api.CucumberOptions;
//import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/Features",
		glue={"com.qa.automation.stepdefinations"},
		plugin={"json:target/cucumber.json","html:target/cucumber.html","rerun:failure/rerun.txt"
				},
		tags={"@Test"}
		)

//,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
//cucumber-testng dependency is needed for AbstractTestNGCucumberTests
public class Runner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios(){
		return super.scenarios();
	}
	
	@BeforeMethod
	@Parameters({"browser","host"})
	public void testParam(String browser,String host){
		System.setProperty("localbrowser", browser);
		System.setProperty("host", host);
	}
	
	@BeforeSuite
	@Parameters({"environment","client","userMode"})
	public void beforeSuite(String environment,String client,String userMode){
		System.out.println("Inside Runner");
		BasicConfigurator.configure();
		System.setProperty("LocalEnvironment",environment);
		System.setProperty("LocalClient",client);
		System.setProperty("UserMode",userMode);
		ConfigFileReader.readPropertyFile(environment,client);
	}
}
