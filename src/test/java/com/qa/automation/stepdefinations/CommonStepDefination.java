package com.qa.automation.stepdefinations;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import com.qa.automation.utils.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


/**
 * Unit test for simple App.
 */
public class CommonStepDefination 
{
	WebDriver driver;
	public static int testDataNo=-1;
	static String currentScenario;
	static String currentStatus;
	static  String productCreationSheetName=null ;
	  
    public CommonStepDefination(){
    	this.driver = DriverFactory.getDriver();
    }
    @Before
    public void intiate(Scenario scenario){
    	
    	Reporter.log("Execution started for : "+scenario.getName(), true);
    	if(!scenario.getName().equals(currentScenario)){
    		testDataNo=-1;
    		currentScenario = scenario.getName();
    	}
    }
    
    @After
    public void cleanUp(Scenario scenario){
    	
    	System.out.println(scenario.getStatus());
    	
    	if("failed".equals(scenario.getStatus())){
    		try {
    			//com.cucumber.listener.Reporter.addScreenCaptureFromPath(SeleniumTestHelper.getFailedScreenshot());
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	

    	//DriverFactory.getDriver().close();
    	//DriverFactory.removeDriver();
	testDataNo=-1;
    }

  	
	
}
