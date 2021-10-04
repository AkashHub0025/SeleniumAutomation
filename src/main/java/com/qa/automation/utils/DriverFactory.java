package com.qa.automation.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;


public class DriverFactory {

	
	private static ThreadLocal<WebDriver> drivers=new ThreadLocal<>();
	private static List<WebDriver> storedDriver=new ArrayList<>();

	
	private DriverFactory(){
		
	}
	
	public static WebDriver getDriver(){
		return drivers.get();

	}
	
	public static void addDriver(WebDriver driver){
		storedDriver.add(driver);
		 drivers.set(driver);
	}
	
	public static void removeDriver(){
		storedDriver.remove(drivers.get());
		 drivers.remove();
	}
	
}
