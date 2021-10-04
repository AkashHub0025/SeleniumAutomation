package com.qa.automation.stepdefinations;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import com.qa.automation.utils.Base;
import com.qa.automation.utils.DriverFactory;

import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import org.openqa.selenium.*;


public class Hooks extends Base{

	WebDriver driver;
	public static int testDataNo=-1;
	static String currentScenario;
	static String currentStatus;
	Logger log=Logger.getLogger(this.getClass());
	 @Before
	    public void intiate(Scenario scenario){
	    	
	    	Reporter.log("Execution started for : "+scenario.getName(), true);
	    	if(!scenario.getName().equals(currentScenario)){
	    		testDataNo=-1;
	    		currentScenario = scenario.getName();
	    		Reporter.log("Testing in Progress.");
	    	}
	    }
	    
	    @After
	    public void cleanUp(Scenario scenario){
	    	
	    	System.out.println(scenario.getName());
	    try{
	    	log.info("Ending Scenerio :"+scenario.getName());
	    	driver.close();
	    	DriverFactory.removeDriver();
	    }
	    catch(Exception e){}
	    	log.info("no driver instance found");
	    }
	    	
/*	@AfterStep
	public void afterStep(Scenario scenario){
		if(scenario.isFailed()){
			log.info("scenario failed : "+scenario.getName());
			byte[] SrcFile=((TakesScreenshot)DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(SrcFile, "image/png", scenario.getId()+"test.png");
		}
		
	}*/
}
