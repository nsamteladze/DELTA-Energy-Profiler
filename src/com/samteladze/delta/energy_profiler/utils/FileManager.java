package com.samteladze.delta.energy_profiler.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.os.Environment;

public class FileManager {
	private static final String PATH_RESULTS_DIR = "Delta/EnergyProfiler/results";
	public static final String PATH_TEMP_DIR = "Delta/EnergyProfiler/temp";
	private static final String PATH_TEST_DIR = "Delta/EnergyProfiler/test";
	
	public static final String FILE_NAME_DEFAULT_RESULTS = "default.csv";
	
	// Creates all the required directories and files if they don't already exist
	public static void initialize()
	{
		// Create results directory
		File resultsDir = new File(Environment.getExternalStorageDirectory(), PATH_RESULTS_DIR);
		if (!resultsDir.exists())
		{
			if (resultsDir.mkdirs())
			{		        	
				MyLogger.LogInfo("Created results directory", FileManager.class.getSimpleName());
			}
		}	
		
		// Create temp directory
		File tempDir = new File(Environment.getExternalStorageDirectory(), PATH_TEMP_DIR);
		if (!tempDir.exists())
		{
			if (tempDir.mkdirs())
			{		        	
				MyLogger.LogInfo("Created temp directory", FileManager.class.getSimpleName());
			}
		}	
		
		// Create test directory
		File testDir = new File(Environment.getExternalStorageDirectory(), PATH_TEST_DIR);
		if (!testDir.exists())
		{
			if (testDir.mkdirs())
			{		        	
				MyLogger.LogInfo("Created test directory", FileManager.class.getSimpleName());
			}
		}	
	}
	
	public static void cleanTempDir() {
		File tempDir = new File(Environment.getExternalStorageDirectory(), PATH_TEMP_DIR);
		
		if (tempDir.exists() && tempDir.isDirectory()) {
	        String[] children = tempDir.list();
	        for (int i = 0; i < children.length; i++) {
	            new File(tempDir, children[i]).delete();
	        }
	        
	        MyLogger.LogInfo("Cleaned temp directory", FileManager.class.getSimpleName());
	    }
	}
	
	public static void SaveObjectToFile(Object obj, String filePath)
	{
		File resultsFile = new File(Environment.getExternalStorageDirectory(), filePath);
		
		try
		{
			// Use UTF8 for multiple language support
			BufferedWriter out = new BufferedWriter
					(new OutputStreamWriter(new FileOutputStream(resultsFile, true), "UTF8"));
			out.write(obj.toString() + "\n");
			out.flush();
			out.close();
			
			MyLogger.LogInfo(FileManager.class.getSimpleName(), "Object info was saved to " + filePath);
		} 
		catch (Exception e)
		{
			MyLogger.LogError(FileManager.class.getSimpleName(), "Could not save object info to " + filePath);
		}
	}
	
	public static String getTestDirAbsolutePath() {
		return (Environment.getExternalStorageDirectory() + "/" + PATH_TEST_DIR);
	}
}
