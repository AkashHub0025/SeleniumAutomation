package com.qa.automation.pages.ui;

import com.qa.automation.utils.Base;
import com.qa.automation.utils.Driver;
import com.qa.automation.utils.DriverFactory;
import com.qa.automation.utils.SeleniumTestHelper;
import com.qa.automation.utils.TestStubReader;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.pdfbox.text.PDFTextStripper;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class EcommercePageObject extends Base{
public static WebDriver driver;
 TestStubReader stubReader=new TestStubReader();
 Map<String,String> valStoreMap=new HashMap<String,String>();
	public EcommercePageObject(){
		driver=DriverFactory.getDriver();
		PageFactory.initElements(DriverFactory.getDriver(),this);
	}
	 
	@FindBy(xpath="//label[text()='Password']//preceding::input[1]")
	public WebElement userIdTxt;
	
	@FindBy(xpath="//label[text()='Password']//following::input[1]")
	public WebElement passwordTxt;
	
	@FindBy(xpath="//a[contains(text(),'Forgot your password?')]//following::span[1]")
	public WebElement signInBtn;
	
	@FindBy(xpath="//a[text()='Women']")
	public WebElement womenTxt;;
	
	
	//PDF validation
	public boolean verifyPDFContent(String rowHeader) throws IOException,AWTException, InterruptedException{
		boolean flag=false;
		String p=System.getProperty("user.dir");
		String path=p+"/"+"SelDownloads";
		path=path.replace("/","\\").replace("//", "\\");
		File directory;
		File[] files;
		directory=new File(path);
		if(!directory.exists()){
			directory.mkdir();
			
		}
		else{
			files=directory.listFiles();
		for(File allFile:files){
			if(allFile.getName().endsWith("pdf")){
				allFile.delete();
			}
		}
		}
		
		SeleniumTestHelper.switchToOtherWindow(driver);
		new Actions(driver).sendKeys(Keys.chord(Keys.CONTROL,"s")).perform();
		StringSelection stringSelection =new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_S);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_S);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(10000);
		
		String getUrl=driver.getCurrentUrl();
		PDDocument doc=null;
	    BufferedInputStream file=null;
		String output=null;
				
		Pattern pattern=Pattern.compile("(.*download/)(.*)(\\?roleCode)");
		Matcher matcher=pattern.matcher(getUrl);
		String findFileName=null;
		while(matcher.find()){
		String anchorText=matcher.group(2);
		findFileName=anchorText.replace("/", "_");
		
		String paths="file:///"+path+"\\"+findFileName+".pdf";
		String dirPath=System.getProperty("user.dir");
		String stubFileName=dirPath+"/SelDownloads/"+findFileName+".pdf";
		File f=new File(stubFileName);
		while(!f.exists()){
			try{
				System.out.println("waiting for PDF to download");
				Thread.sleep(2000);
			}
			catch(InterruptedException e){
				}
		}
		
		
		URL urlOFPDF=new URL(paths);
		BufferedInputStream fileToParse=null;
		fileToParse=new BufferedInputStream(urlOFPDF.openStream());
		
		PDDocument document=PDDocument.load(fileToParse);
		output=new PDFTextStripper().getText(document).replace(":\\s", "");
		output=output.replace(",","");
		stubReader.updateExcelForPDF("PDFTestdata",rowHeader,valStoreMap);
		List<String> list=new ArrayList<String>();
		List<String> finallist=new ArrayList<String>();
		
		
		for(int i=0;i<stubReader.columnCount;i++){
			String colHeader=stubReader.setSheetAndGetData("PDFTestData", "Scenerio",i);
			String value=stubReader.setSheetAndGetData("PDFTestData", "Data",colHeader);
			String append=null;
			
			if(valStoreMap.containsKey(colHeader) && (colHeader.equals("MemoCodes"))){
				value=colHeader.equalsIgnoreCase("MemoCodes")?value.replace("-",""):value;
				list=Stream.of(value.split(",")).collect(Collectors.toList());
				finallist.addAll(list);
			}
			
			else{
				if(valStoreMap.containsKey(colHeader)){
					append=colHeader+" "+value;
					finallist.add(append);
				}
				
			}
		}
		
		for(String valToFind:finallist){
			valToFind=valToFind.trim();
			SeleniumTestHelper.assertTrue(output.contains(valToFind), "This value is not found in PDF");
			
		}
		}
		return flag;
	}
	
	public void saveearningDedMemo(String earDedMemo){
	String[] keySet=earDedMemo.split("/");
	for(String key:keySet){
		String value=driver.findElement(By.xpath("div[text()='Memo']")).getText();
		
		if(valStoreMap.containsKey(key)){
		valStoreMap.put(key, valStoreMap.get(key)+","+value);
		}
		else{
			valStoreMap.put(key, value);
			
		}
	}
	
	
		
	}
	
}
