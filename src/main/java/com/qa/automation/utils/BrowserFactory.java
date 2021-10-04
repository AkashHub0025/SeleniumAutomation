package com.qa.automation.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
public class BrowserFactory {

	public WebDriver driver;
	public String chromeDriverPath="";
	public String firefoxDriverPath="";
	public String safariDriverPath="";
	public String edgeDriverPath="";
	public String host=System.getProperty("host");
	public String environment=System.getProperty("environment");
	public String browser=System.getProperty("Browser")!=null?System.getProperty("Browser"):System.getProperty("localbrowser");
	public String HOME=System.getProperty("user.dir");

	public WebDriver getBrowser() throws MalformedURLException{
		if(host.equalsIgnoreCase("local"))
		{System.out.println("--------------");
			if(browser.equalsIgnoreCase("chrome")){
				if(System.getProperty("os.name").contains("MAC")){
					chromeDriverPath=ConfigFileReader.getProperty("chromeDriverPathMac");
					
				}
				else{
					chromeDriverPath=ConfigFileReader.getProperty("chromeDriverPathWindow");
					
				}
				System.setProperty("webdriver.chrome.driver", HOME+chromeDriverPath);
				
			
			
			Map<String,Object> chromePrefs=new HashMap<String,Object>();
			chromePrefs.put("profile.default_content_setting.popups",0);
			chromePrefs.put("download.default_directory", "");
			ChromeOptions chromeOptions=new ChromeOptions();
			chromeOptions.setExperimentalOption("prefs", chromePrefs);
			//DesiredCapabilities capabilities=DesiredCapabilities.chrome();
			driver=new ChromeDriver(chromeOptions);
				
		}
		if(browser.equalsIgnoreCase("firefox")){
			if(System.getProperty("os.name").contains("MAC")){
				chromeDriverPath=ConfigFileReader.getProperty("firefoxDriverPathMac");
				
			}
			else{
				chromeDriverPath=ConfigFileReader.getProperty("firefoxDriverPathWindow");
				
			}
			System.setProperty("webdriver.gecko.driver", HOME+chromeDriverPath);
			
			driver=new FirefoxDriver();
		}
		if(browser.equalsIgnoreCase("safarisafari")){
			safariDriverPath=ConfigFileReader.getProperty("safaridriverpath");
	System.setProperty("webdriver.safari.driver", HOME+safariDriverPath);
			
			driver=new SafariDriver();
		}	
		if(browser.equalsIgnoreCase("ie")){

			//driver=new InternetExplorerDriver();
		}
		
		if(browser.equalsIgnoreCase("edge")){

			driver=new EdgeDriver();
		}
			
		
		}
		
		else{
			DesiredCapabilities caps=new DesiredCapabilities();
			if(browser.equalsIgnoreCase("firefox")){
				
				System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
				caps=DesiredCapabilities.firefox();
				caps.setBrowserName("Firefox");
				caps.setCapability("Marionete",true);
				caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
				caps.setCapability("acceptInsecureCerts",true);
				caps.setCapability("javascriptEnabled",true);
			}
			
	if(browser.equalsIgnoreCase("IE")){
				
				System.setProperty("webdriver.ie.driver", "C:\\IEDriverServer.exe");
				caps=DesiredCapabilities.internetExplorer();
				caps.setBrowserName("inetrnet explorer");
				caps.setCapability("nativeEvens",false);
				caps.setCapability("INTRODUCE_FALKINESS_BY_IGNORING_SECURITY_DOMAIN",true);
				
			}
			
	if(browser.equalsIgnoreCase("Chrome")){
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		caps=DesiredCapabilities.chrome();
		Map<String,Object> chromePrefs=new HashMap<String,Object>();
		chromePrefs.put("profile.default_content_setting.popups",0);
		ChromeOptions options=new ChromeOptions();
		chromePrefs.put("download.default_directory", "");
		ChromeOptions chromeOptions=new ChromeOptions();
		chromeOptions.setExperimentalOption("prefs", chromePrefs);
		
		
		caps.setBrowserName("inetrnet explorer");
		caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS,false);
		caps.setCapability(chromeOptions.CAPABILITY,options);
		
	}	
	//Version
	System.out.println("Before driver intilization**********");
	driver=new RemoteWebDriver(new URL("http://10.89.56.45/wb/hub"),caps);
	System.out.println("After driver intilization**********");
	caps.setCapability(CapabilityType.TAKES_SCREENSHOT,true);
		}
		
		driver.manage().window().maximize();
		DriverFactory.addDriver(driver);
		return driver;
		
	}




}
