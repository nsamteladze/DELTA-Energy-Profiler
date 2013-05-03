package com.samteladze.delta.energy_profiler.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class MyLogger {
	private static Logger _logger = null;
	
	// STUB
	@SuppressWarnings("unused")
	private static Logger logger() {
		if (_logger == null) {
			
			try {
				  FileHandler handler = new FileHandler("logfile.log");
				  Logger logger = Logger.getLogger("com.somename");
				  logger.addHandler(handler);

				} catch (IOException e) {
					
				}
		}
		
		return _logger;
	}
	
	// STUB
	public static void LogError(String message, String occuredIn) {

	}
	
	// STUB
	public static void LogInfo(String message, String occuredIn) {

	}
}
