package com.qa.automation.stepdefinations;

import com.qa.automation.pages.ui.EcommercePageObject;
import com.qa.automation.pages.ui.LogginPageObject;
import com.qa.automation.utils.DriverManager;
import com.qa.automation.utils.SeleniumTestHelper;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;


public class EcommerceStepDefinnation extends EcommercePageObject{
	LogginPageObject loggin=new LogginPageObject();


	@And("^I click on \"([^\"]*)\" section$")
	public void i_click_on_section(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		SeleniumTestHelper.click(womenTxt);
	
	}

	@And("^I select the \"([^\"]*)\"$")
	public void i_select_the(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions

	}
}
