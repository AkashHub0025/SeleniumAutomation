package com.qa.automation.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
public static Properties configFile;
public static String filePath;
	public static String jenkinsEnvironment=System.getProperty("Environment");
	public static String jenkinsClient=System.getProperty("Client");
	public static String localEnvironment=System.getProperty("LocalEnvironment");
	public static String localClient=System.getProperty("LocalClient");
	
	
	
	
	
	public static String getDownloadLocation(){
		String location =null;
		if(null!=getProperty("downloadLocation")){
			location=System.getProperty("user.dir")+"/"+System.getProperty("downloadLocation");
			
		}
		
		else{
			location=System.getProperty("user.home")+"/"+System.getProperty("Downloads");
			
		}
		return location.replace("/",File.separator);
	}
	
	
	public static String getFilePath(String environment,String client){
		
		
		String propertyFilePathWindow=System.getProperty("user.dir")+"\\Config\\"+"\\"+environment+"\\"+client+"_config.properties";
		String propertyFilePathMac=System.getProperty("user.dir")+"/Config/"+"/"+environment+"/"+client+"_config.properties";
		
		if(System.getProperty("os.name").contains("Mac")){
			return propertyFilePathMac;
		}
		else{
		return propertyFilePathWindow;
		}
		
	}
	
	public static void readPropertyFile(String environment,String client){
		BufferedReader reader;
		try{
		if(jenkinsEnvironment!=null && !jenkinsEnvironment.isEmpty() && jenkinsClient!=null && !jenkinsClient.isEmpty()){
			filePath=getFilePath(jenkinsEnvironment,jenkinsClient);
			
		}
		else{
			filePath=getFilePath(environment,client);
		}
		System.out.println("filePath is : "+filePath);
		reader=new BufferedReader(new FileReader(filePath));
		configFile=new Properties();
		configFile.load(reader);
		reader.close();
	}
		catch(FileNotFoundException e){
			} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	
	public static void writePropertyFile(String key,String value){
		BufferedReader reader;
		try{
		if(jenkinsEnvironment!=null && !jenkinsEnvironment.isEmpty() && jenkinsClient!=null && !jenkinsClient.isEmpty()){
			filePath=getFilePath(jenkinsEnvironment,jenkinsClient);
			
		}
		else{
			filePath=getFilePath(localEnvironment,localClient);
		}
		
		reader=new BufferedReader(new FileReader(filePath));
		configFile=new Properties();
		configFile.load(reader);
		reader.close();

	BufferedWriter writter=new BufferedWriter(new FileWriter(filePath));
	configFile.setProperty(key,value);
	configFile.store(writter, null);
	writter.close();
		}
		catch(FileNotFoundException e){
			} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static String getProperty(String key){
		try{
			key=configFile.getProperty(key);
			}
		
		catch(Exception e){
			
		}
		return key;
	}
	
	
}
