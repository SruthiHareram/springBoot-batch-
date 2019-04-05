package com.dnb.test.springboot.step;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.batch.item.ItemWriter;

import com.dnb.test.springboot.CleanUp;
import com.dnb.test.springboot.connection.Connection;
import com.jcraft.jsch.Session;

public class Writer implements ItemWriter<String> {
	
	/*@Override
	public void write(List<? extends String> messages) throws Exception {
		System.out.println("messages :"+messages);
		for (String msg : messages) {
			System.out.println("Writing the data " + msg);
		}
	}*/
	
	
	
	public String filePath;
	
	@Override
	public void write(List<? extends String> messages) throws Exception {
		System.out.println("wirter clean up started");
		
		//for server
		CleanUp cleanup = new CleanUp();
		ArrayList<String> paths = null ;
		cleanup.cleanup(paths);
		
		//for local directory
		/*for (String msg : messages) {
			System.out.println("Writing the data " + msg);
			filePath=msg;
			FileUtils.cleanDirectory(new File(filePath)); 
		}*/
		System.out.println("writer clean up completed");
			
	}


}