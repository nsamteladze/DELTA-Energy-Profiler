package com.samteladze.delta.energy_profiler;

import com.samteladze.delta.energy_profiler.model.CPUDiffExperimentOptions;
import com.samteladze.delta.energy_profiler.model.ExperimentOptions;
import com.samteladze.delta.energy_profiler.model.ExperimentType;
import com.samteladze.delta.energy_profiler.model.IdleExperimentOptions;
import com.samteladze.delta.energy_profiler.model.Net4GExperimentOptions;
import com.samteladze.delta.energy_profiler.model.ScreenExperimentOptions;
import com.samteladze.delta.energy_profiler.utils.MyLogger;

public class ExperimentOptionsFactory {
	
	private final static long TIME_BETWEEN_MEASUREMENTS = 30000;
	private final static int NUMBER_OF_MEASUREMENTS_IDLE = 200;
	private final static int NUMBER_OF_MEASUREMENTS_SCREEN = 200;
	
	public static ExperimentOptions instantiate(ExperimentType experimentType) {
		
		switch (experimentType) {
		case Idle:
			return new IdleExperimentOptions(experimentType, TIME_BETWEEN_MEASUREMENTS, NUMBER_OF_MEASUREMENTS_IDLE);
		case Screen:
			return new ScreenExperimentOptions(experimentType, TIME_BETWEEN_MEASUREMENTS, NUMBER_OF_MEASUREMENTS_SCREEN);
		case CPU_Diff:
			return new CPUDiffExperimentOptions(experimentType);
		case Net_4G:
			return new Net4GExperimentOptions(experimentType);
		default:
			MyLogger.LogError("Failed to instantiate experiment options; experiment type was not found", 
							  ExperimentOptionsFactory.class.toString());
			return null;
		}
	}
	
}
