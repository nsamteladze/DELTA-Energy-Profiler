package com.samteladze.delta.energy_profiler.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import com.samteladze.delta.energy_profiler.model.BatteryInfo;

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
	
	public static void WriteBatteryInfo(BatteryInfo batteryInfo, String filePath)
	{
		File resultsFile = new File(Environment.getExternalStorageDirectory(), filePath);
		
		try
		{
			// TEST for multiple languages support
			BufferedWriter out = new BufferedWriter
					(new OutputStreamWriter(new FileOutputStream(resultsFile, true), "UTF8"));
			out.write(batteryInfo.toString() + "\n");
			out.flush();
			out.close();
			
			MyLogger.LogInfo(FileManager.class.getSimpleName(), "Battery info was saved to " + filePath);
		} 
		catch (Exception e)
		{
			MyLogger.LogError(FileManager.class.getSimpleName(), "Could not save battery info to " + filePath);
		}
	}
}
