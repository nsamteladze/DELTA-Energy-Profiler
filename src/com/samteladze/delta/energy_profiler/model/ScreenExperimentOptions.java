package com.samteladze.delta.energy_profiler.model;

public class ScreenExperimentOptions extends ExperimentOptions implements IExperimentWithMeasurementService {
	private long _timeBetweenMeasurements;
	
	public ScreenExperimentOptions(ExperimentType experimentType,
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
