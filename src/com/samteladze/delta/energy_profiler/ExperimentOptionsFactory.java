package com.samteladze.delta.energy_profiler;

import java.io.File;

import com.samteladze.delta.energy_profiler.model.CPUZipExperimentOptions;
import com.samteladze.delta.energy_profiler.model.ExperimentOptions;
import com.samteladze.delta.energy_profiler.model.ExperimentType;
import com.samteladze.delta.energy_profiler.model.IdleExperimentOptions;
import com.samteladze.delta.energy_profiler.model.Net4GExperimentOptions;
import com.samteladze.delta.energy_profiler.model.ScreenExperimentOptions;
import com.samteladze.delta.energy_profiler.utils.FileManager;
import com.samteladze.delta.energy_profiler.utils.MyLogger;

public class ExperimentOptionsFactory {
	
	private final static long TIME_BETWEEN_MEASUREMENTS = 30000;
	private final static int IDLE_NUMBER_OF_MEASUREMENTS = 240;
	private final static int SCREEN_NUMBER_OF_MEASUREMENTS = 180;
	private final static int CPU_NUMBER_OF_MEASUREMENTS = 180;
	// For 1 MB file
	private final static int NET_NUMBER_OF_MEASUREMENTS_1 = 600;
	private final static String NET_DONWLOADED_FILE_URL_1 = "http://www.csee.usf.edu/~nsamteladze/res/test_1.dat";
	// For 5 MB file
	private final static int NET_NUMBER_OF_MEASUREMENTS_5 = 120;
	private final static String NET_DONWLOADED_FILE_URL_5 = "http://www.csee.usf.edu/~nsamteladze/res/test_5.dat";
	// For 10 MB file
	private final static int NET_NUMBER_OF_MEASUREMENTS_10 = 60;
	private final static String NET_DONWLOADED_FILE_URL_10 = "http://www.csee.usf.edu/~nsamteladze/res/test_10.dat";
	
	private final static String CPU_TEST_ARCHIVE = "test.zip";
	
	
	public static ExperimentOptions instantiate(ExperimentType experimentType) {
		
		switch (experimentType) {
		case Idle:
			return new IdleExperimentOptions(experimentType, IDLE_NUMBER_OF_MEASUREMENTS, TIME_BETWEEN_MEASUREMENTS);
		case Screen:
			return new ScreenExperimentOptions(experimentType, SCREEN_NUMBER_OF_MEASUREMENTS, TIME_BETWEEN_MEASUREMENTS);
		case CPU_Zip:
			return new CPUZipExperimentOptions(experimentType, CPU_NUMBER_OF_MEASUREMENTS, TIME_BETWEEN_MEASUREMENTS,  
					(new File(FileManager.getTestDirAbsolutePath(), CPU_TEST_ARCHIVE)).getAbsolutePath());
		case Net_4G:
			return new Net4GExperimentOptions(experimentType, NET_NUMBER_OF_MEASUREMENTS_5, TIME_BETWEEN_MEASUREMENTS, 
											  NET_DONWLOADED_FILE_URL_5);
		default:
			MyLogger.LogError("Failed to instantiate experiment options; experiment type was not found", 
							  ExperimentOptionsFactory.class.toString());
			return null;
		}
	}
	
}
