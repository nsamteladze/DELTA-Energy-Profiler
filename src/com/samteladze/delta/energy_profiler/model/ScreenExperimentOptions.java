package com.samteladze.delta.energy_profiler.model;

public class ScreenExperimentOptions extends ExperimentOptions implements IExperimentWithMeasurementService {
	private long _timeBetweenMeasurements;
	private int _numberOfMeasurements;
	
	public ScreenExperimentOptions(ExperimentType experimentType,
								   long timeBetweenMeasurements, 
								   int numberOfMeasurements) {
		super(experimentType);
		
		this._timeBetweenMeasurements = timeBetweenMeasurements;
		this._numberOfMeasurements = numberOfMeasurements;
	}

	@Override
	public long getTimeBetweenMeasurements() {
		return _timeBetweenMeasurements;
	}

	@Override
	public int getNumberOfMeasurements() {
		return _numberOfMeasurements;
	}
	
}
