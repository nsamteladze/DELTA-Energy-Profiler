package com.samteladze.delta.energy_profiler.model;

public class IdleExperimentOptions extends ExperimentOptions{
	public IdleExperimentOptions(ExperimentType experimentType, int numberOfMeasurements,
			   					 long timeBetweenMeasurements) {
		super(experimentType, numberOfMeasurements, timeBetweenMeasurements);
	}
}
