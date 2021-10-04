package com.qa.automation.pages.ui;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.automation.utils.Base;
import com.qa.automation.utils.ConfigFileReader;
import com.qa.automation.utils.DriverFactory;


public class LogginPageObject extends Base{

	public WebDriver driver;
	public LogginPageObject(){
		this.driver=DriverFactory.getDriver();
		PageFactory.initElements(driver,this);
	}
	 
	@FindBy(xpath="//label[text()='Password']//preceding::input[1]")
	public WebElement userNameTxtBx;
	
	@FindBy(xpath="//label[text()='Password']//following::input[1]")
	public WebElement userPwdTxtBx;
	
	@FindBy(xpath="//a[contains(text(),'Forgot your password?')]//following::span[1]")
	public WebElement SignInBtn;
	
	@FindBy(xpath="(//a[contains(text(),'Sign out')])[1]")
	public WebElement signOutBtn;
	
	public void loggin(){
		
		waitForElementToBeDisplayed(driver,userNameTxtBx,10);
		enterTextInTextBox(userNameTxtBx,"akash");
		enterTextInTextBox(userPwdTxtBx,"Thapa");
		clickOnButton(SignInBtn);
	
	}
	
	public void login(String userName, String userPwd) throws InterruptedException {
		try {
			Thread.sleep(2500);
			if (userNameTxtBx.isDisplayed()) {
				//userNameTxtBx.clear();
				//userPwdTxtBx.clear();
/*				userNameTxtBx.sendKeys(userName);
				userPwdTxtBx.sendKeys(userPwd);
				SignInBtn.click();*/
				enterTextInTextBox(userNameTxtBx,userName);
				enterTextInTextBox(userPwdTxtBx,userPwd);
				clickOnButton(SignInBtn);
			}
		} catch (NoSuchElementException noSuchElementExec) {
			waitForElementToBeDisplayed(driver,userNameTxtBx,10);
			Thread.sleep(2000);
			//userNameTxtBx.clear();
			//userPwdTxtBx.clear();
			userNameTxtBx.sendKeys(userName);
			userPwdTxtBx.sendKeys(userPwd);
			SignInBtn.click();
		}

	}


	public void open(String url) {
		//DriverFactory.getDriver().get(url);
		driver.get(url);
	}

	public EcommercePageObject ecommerceLogin(String env) throws InterruptedException {
		if(env.equalsIgnoreCase("Admin")){
			System.out.println("url is : "+ConfigFileReader.getProperty("url"));
		open(ConfigFileReader.getProperty("url"));
		login(ConfigFileReader.getProperty("username"), ConfigFileReader.getProperty("password"));
		return new EcommercePageObject();
	}
		else{
			
			return new EcommercePageObject();	
		}

	}
}
