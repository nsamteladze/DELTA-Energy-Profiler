package com.samteladze.delta.energy_profiler.model;

public class IdleExperimentOptions extends ExperimentOptions implements IExperimentWithMeasurementService {
	private long _timeBetweenMeasurements;
	
	public IdleExperimentOptions(ExperimentType experimentType,
									   long timeBetweenMeasurements, 
									   int numberOfMeasurements) {
		super(experimentType, numberOfMeasurements);
		
		this._timeBetweenMeasurements = timeBetweenMeasurements;
	}

	@Override
	public long getTimeBetweenMeasurements() {
		return _timeBetweenMeasurements;
	}	
}
