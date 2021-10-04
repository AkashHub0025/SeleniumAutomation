package com.qa.automation.utils;

import java.net.MalformedURLException;

public class DriverManager {

	public DriverManager() throws MalformedURLException{
		
		if(DriverFactory.getDriver()==null){
			BrowserFactory browserFactory=new BrowserFactory();
				browserFactory.getBrowser();

		}
	}
}
