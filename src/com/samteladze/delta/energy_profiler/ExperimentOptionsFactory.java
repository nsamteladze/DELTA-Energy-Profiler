package com.samteladze.delta.energy_profiler;

import com.samteladze.delta.energy_profiler.model.ExperimentOptions;
import com.samteladze.delta.energy_profiler.model.ExperimentType;
import com.samteladze.delta.energy_profiler.model.IdleExperimentOptions;
import com.samteladze.delta.energy_profiler.utils.MyLogger;

public class ExperimentOptionsFactory {
	
	private final static long TIME_BETWEEN_MEASUREMENTS = 30000;
	private final static int NUMBER_OF_MEASUREMENTS = 120;
	
	public static ExperimentOptions instantiate(ExperimentType experimentType) {
		
		switch (experimentType) {
		case Idle:
			return new IdleExperimentOptions(experimentType, TIME_BETWEEN_MEASUREMENTS, NUMBER_OF_MEASUREMENTS);
		default:
			MyLogger.LogError("Failed to instantiate experiment options; experiment type was not found", 
							  ExperimentOptionsFactory.class.toString());
			return null;
		}
	}
	
}
