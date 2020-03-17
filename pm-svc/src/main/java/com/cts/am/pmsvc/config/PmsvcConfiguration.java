package com.cts.am.pmsvc.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.aspectj.bridge.MessageWriter;


public class PmsvcConfiguration {
	
	public List<String> getProjectsValues(){
		
		final org.apache.logging.log4j.Logger logger = 
				LogManager.getLogger(MessageWriter.class.getName());
		ArrayList<String> projects = new ArrayList<String>();
		
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("projects.properties")) {

	            Properties prop = new Properties();
	           
	            if (input == null){
	                return projects;
	            }

	            prop.load(input);
	            prop.forEach((key, value) -> projects.add((String) value));
	            
		} catch (IOException ex) {
			logger.fatal("unable to find", ex);
	    }
		return projects;

	}


}
