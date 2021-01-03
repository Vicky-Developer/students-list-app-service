package com.students.utils;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentsLogger implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private transient Logger logger;
	
	public static StudentsLogger getLogger(String loggerName) {

		
		StudentsLogger sLogger = new StudentsLogger();

		sLogger.logger = Logger.getLogger(loggerName);
		
		return sLogger;
	}
	
	public void info(String value) {
		logger.log(Level.INFO,value);
	}
	
	public void error(String value) {
		logger.log(Level.SEVERE,value);
	}
	

}
