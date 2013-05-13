package com.samteladze.delta.energy_profiler.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.os.Environment;

public class FileManager {
	private static final String PATH_RESULTS_DIR = "Delta/EnergyProfiler/results";
	private static final String PATH_TEMP_DIR = "Delta/EnergyProfiler/temp";
	private static final String PATH_TEST_DIR = "Delta/EnergyProfiler/test";
	
	private static final String FILE_NAME_PERIODIC_RESULTS = "periodic.csv";
	private static final String FILE_NAME_STEP_RESULTS = "step.csv";
	private static final String FILE_NAME_TEMP = "temp.dat";
	
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
		
		// Clean temp directory
		cleanTempDir();
	}
	
	// Cleans temp directory defined by PATH_TEMP_DIR
	// Does not delete temp directory itself
	public static void cleanTempDir() {
		File tempDir = new File(Environment.getExternalStorageDirectory(), PATH_TEMP_DIR);
		
		if (tempDir.exists() && tempDir.isDirectory()) {
			for (File subdir : tempDir.listFiles()) {
	        	deleteDirWithSubdir(subdir);
	        }
			
	        MyLogger.LogInfo("Cleaned temp directory", FileManager.class.getSimpleName());
	    }
		else {
			MyLogger.LogWarning("Failed to delete temp directory", FileManager.class.getSimpleName());
		}
			
	}
	
	// Recursively deletes all the files and all the sub directories
	private static void deleteDirWithSubdir(File dirToDelete) {
	    if (dirToDelete.isDirectory()) {
	        for (File subdir : dirToDelete.listFiles()) {
	        	deleteDirWithSubdir(subdir);
	        }
	    }
	    dirToDelete.delete();
	}
	
	// Saves object to file using object.toString()
	public static void saveObjectToFile(Object obj, String filePath)
	{
		File resultsFile = new File(filePath);
		
		try
		{
			// Use UTF8 for multiple language support
			BufferedWriter out = new BufferedWriter
					(new OutputStreamWriter(new FileOutputStream(resultsFile, true), "UTF8"));
			out.write(obj.toString() + "\n");
			out.flush();
			out.close();
			
			MyLogger.LogInfo("Object info was saved to " + filePath, FileManager.class.getSimpleName());
		} 
		catch (Exception e)
		{
			MyLogger.LogInfo("Could not save object info to " + filePath, FileManager.class.getSimpleName());
		}
	}
	
	public static String getTestDirAbsolutePath() {
		return (Environment.getExternalStorageDirectory() + "/" + PATH_TEST_DIR);
	}
	
	public static String getTempDirAbsolutePath() {
		return (Environment.getExternalStorageDirectory() + "/" + PATH_TEMP_DIR);
	}
	
	// Periodic results are measured in the alarm receiver every N seconds
	public static String getPeriodicResultsAbsolutePath() {
		return (Environment.getExternalStorageDirectory() + "/" + PATH_RESULTS_DIR + "/" + FILE_NAME_PERIODIC_RESULTS);
	}
	
	// Step results are measured in the experiment conditions service after each experiment iteration
	public static String getStepResultsAbsolutePath() {
		return (Environment.getExternalStorageDirectory() + "/" + PATH_RESULTS_DIR + "/" + FILE_NAME_STEP_RESULTS);
	}
	
	public static String getTempFileAbsolutePath() {
		return (Environment.getExternalStorageDirectory() + "/" + PATH_TEMP_DIR + "/" + FILE_NAME_TEMP);
	}
	
	// Deletes results files from the results directory
	public static void deleteResultsFiles() {
		if ((new File(getStepResultsAbsolutePath())).delete() &&
			(new File(getPeriodicResultsAbsolutePath())).delete()) {
			MyLogger.LogInfo("Deleted the results files", FileManager.class.getSimpleName());
		}
		else {
			MyLogger.LogWarning("Failed to delete the results files", FileManager.class.getSimpleName());
		}
	}
}
