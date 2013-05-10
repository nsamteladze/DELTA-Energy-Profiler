package com.samteladze.delta.energy_profiler;

import com.samteladze.delta.energy_profiler.model.ExperimentType;
import com.samteladze.delta.energy_profiler.model.IExperimentConditionsService;
import com.samteladze.delta.energy_profiler.utils.MyLogger;

// Construct the service that maintains the experiment's conditions
public class ExperimentConditionsServiceFactory {
	public static IExperimentConditionsService instantiate(ExperimentType experimentType) {
		switch (experimentType) {
		case Idle:
			return new IdleExperimentConditionsService();
		default:
			MyLogger.LogError("Failed to instantiate experiment conditions service; experiment type was not found", 
					ExperimentConditionsServiceFactory.class.toString());
			return null;
		}
	}
}