package com.dnb.test.springboot.step;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

public class Reader implements ItemReader<String> {

	//private String[] messages = { "javainuse.com","Welcome to Spring Batch Example","We use H2 Database for this example" };
	private String[] messages = getPaths();
	private int count = 0;
	
	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		System.out.println("messages.length :"+messages.length);
		if (count < messages.length) {
			String res =messages[count++];
			System.out.println("res :"+ res);
			return res;
		} else {
			count = 0;
		}
		return null;
	}

	@SuppressWarnings("resource")
	@PostConstruct
	public String[] getPaths(){
	
		String csvFilepath = "D:\\Sruthi\\projects\\REPOS\\SpringBoot\\SpringBoot\\springboot\\springboot\\src\\main\\resources\\qa1.csv";
		System.out.println("csvFilepath: "+csvFilepath); 
		File file= new File(csvFilepath);
		
		ArrayList<String> arraypaths = new ArrayList<>();
			try { 
			    FileReader filereader = new FileReader(file); 
		        CSVReader csvReader = new CSVReader(filereader); 
		        String[] nextRecord; 
		        while ((nextRecord = csvReader.readNext()) != null) { 
		         	for (String cell : nextRecord) { 
		            	arraypaths.add(cell);
		                       	
		                System.out.print("cell :"+cell + "\t"); 
		            } 
		            System.out.println(); 
		        } 
		    } 
		    catch (Exception e) { 
		        e.printStackTrace(); 
		    }
		String[] myArray = new String[arraypaths.size()];
		arraypaths.toArray(myArray);
		System.out.println("myArray: "+myArray);
			return myArray ;
		}
	
}