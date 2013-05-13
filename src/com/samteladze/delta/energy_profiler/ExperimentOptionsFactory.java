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
	private final static int IDLE_NUMBER_OF_MEASUREMENTS = 200;
	private final static int SCREEN_NUMBER_OF_MEASUREMENTS = 200;
	private final static int CPU_NUMBER_OF_MEASUREMENTS = 200;
	private final static int NET_NUMBER_OF_MEASUREMENTS = 50;
	private final static String CPU_TEST_ARCHIVE = "test.zip";
	private final static String NET_DONWLOADED_FILE_URL = "http://www.csee.usf.edu/~nsamteladze/res/resume.pdf";
	
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
			return new Net4GExperimentOptions(experimentType, NET_NUMBER_OF_MEASUREMENTS, TIME_BETWEEN_MEASUREMENTS, 
											  NET_DONWLOADED_FILE_URL);
		default:
			MyLogger.LogError("Failed to instantiate experiment options; experiment type was not found", 
							  ExperimentOptionsFactory.class.toString());
			return null;
		}
	}
	
}
