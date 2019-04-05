package com.dnb.test.springboot.step;

import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<String, String> {

	@Override
	public String process(String data) throws Exception {
		
		
		System.out.println("data: "+data);
		return data.toUpperCase();
	}
	
}