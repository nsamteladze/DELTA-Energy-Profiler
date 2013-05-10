package com.samteladze.delta.energy_profiler.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.os.Environment;

public class FileManager {
	private static final String PATH_RESULTS_DIR = "Delta/EnergyProfiler/results";
	
	public static final String FILE_NAME_DEFAULT_RESULTS = "default.csv";
	
	// Creates all the required directories and files if they don't already exist
	public static void Initialize()
	{
		File resultsDir = new File(Environment.getExternalStorageDirectory(), PATH_RESULTS_DIR);

		// Create directories
		if (!resultsDir.exists())
		{
			if (resultsDir.mkdirs())
			{		        	
				MyLogger.LogInfo("Created required directories", FileManager.class.getSimpleName());
			}
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
}
