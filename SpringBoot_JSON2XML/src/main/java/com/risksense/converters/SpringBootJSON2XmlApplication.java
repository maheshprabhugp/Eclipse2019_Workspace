package com.risksense.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJSON2XmlApplication implements CommandLineRunner{
	
	@Autowired
	private ConverterFactory converterFactory;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJSON2XmlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		converterFactory.createXMLJSONConverter();
		System.out.println("Program Ends");
	}
}
