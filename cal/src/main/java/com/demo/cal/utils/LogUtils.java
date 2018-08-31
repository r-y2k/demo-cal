package com.demo.cal.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class LogUtils {

	@SuppressWarnings("serial")
	public static final Map<String, Level> LOG_LEVELS_MAP = new HashMap<String, Level>() {
		{
			put("DEBUG", Level.DEBUG);
			put("INFO",  Level.INFO);
			put("ERROR", Level.ERROR);
		}
	};
	
	
	public static Logger getLogger(Class<?> clazz) {
		
		Logger log =  (Logger)  LoggerFactory.getLogger(clazz);
		
		//Set logLevel if specified in vm options "-DlogLevel=INFO", default is ERROR Level
		String logLevel = System.getProperty("logLevel") != null ? System.getProperty("logLevel").trim().toUpperCase() : "ERROR";
		
		if(LOG_LEVELS_MAP.get(logLevel) != null){
			
			log.setLevel(LOG_LEVELS_MAP.get(logLevel)); 
			
		}
		
		return log;
	}
	
}
