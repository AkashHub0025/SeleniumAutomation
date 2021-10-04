package com.qa.automation.stepdefinations;

import com.qa.automation.pages.ui.LogginPageObject;
import com.qa.automation.utils.DriverManager;
import com.qa.automation.utils.SeleniumTestHelper;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class LoginStepDefination {
	
private LogginPageObject login;

	public LoginStepDefination(DriverManager manager,LogginPageObject login) {
this.login=login;
	}

	@Given("^I login to ecommerce app$")
	public void i_login_to_ecommerce_app() throws Throwable {
	     String env=System.getProperty("UserMode");
	     System.out.println("env is : "+env);
		login.ecommerceLogin(env);
		
	}
	
	@And("^I sign out from website$")
	public void iSignOutWebsite(){
		SeleniumTestHelper.clickOnButton(login.signOutBtn);
	}
}
