package com.qa.automation.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
//import com.opencsv.CSVReader;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVFileReader {

	private static final String csvFilePathWindow=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\";
	private static final String csvFilePathMac=System.getProperty("user.dir")+"/src/test/resources/TestData/";

	
	public String getData(String filename,String headername,int rownum){
	String retValue="";
	boolean valueFound = false;
	
	try{
		BufferedReader br=new BufferedReader(new FileReader(getFilePath()+filename+".csv"));
		CSVParser parser=CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(br);
		
		for(CSVRecord record: parser){
			if(record.getRecordNumber()==rownum){
				retValue=record.get(headername);
				valueFound=true;
			}
		}
		if(!valueFound){
			
			throw new Exception("Unable to find requessted data");
		}
		
	}
	catch(Exception e){
		
	}
	return retValue;
		}
	
	public void updateCSV(String fileName,HashMap<String,String> keyValues,int row){
		
		try{
			File file=new File(getFilePath()+fileName+".csv");
			BufferedReader br=new BufferedReader(new FileReader(file));
			CSVParser parser=CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(br);
			
			CSVReader reader=new CSVReader(new FileReader(file),',');
			List<String[]> csvBody=reader.readAll();
			
			Map<String,Integer> map=new HashMap<String,Integer>();
			map=parser.getHeaderMap();
			
			for(String key:map.keySet()){
				for(String keyHeader: keyValues.keySet()){
					if(key.contains(keyHeader)){
						String value=ConfigFileReader.getProperty(keyValues.get(key));
						csvBody.get(row)[Integer.valueOf(map.get(key))]=value;
					}
					
				}
				
				
				
			}
			reader.close();
			
			CSVWriter writer=new CSVWriter(new FileWriter(file),',');
			writer.writeAll(csvBody);
			writer.flush();
			writer.close();
		}
		
		catch(Exception e){
			
		}
	}
public static String getFilePath(){
	if(System.getProperty("os.name").contains("Mac"))
	{
		return csvFilePathMac;
	}
	else{
		return csvFilePathWindow;
	}
}

}
