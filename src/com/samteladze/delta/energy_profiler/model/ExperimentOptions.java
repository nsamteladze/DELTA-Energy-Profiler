package com.samteladze.delta.energy_profiler.model;

public abstract class ExperimentOptions {
	private ExperimentType _experimentType;
	private int _numberOfMeasurements;
	private long _timeBetweenMeasurements;
	
	protected ExperimentOptions(ExperimentType experimentType, int numberOfMeasurements, 
								long timeBetweenMeasurements) {
		this._experimentType = experimentType;
		this._numberOfMeasurements = numberOfMeasurements;
		this._timeBetweenMeasurements = timeBetweenMeasurements;
	}
	
	public ExperimentType getExperimentType() {
		return _experimentType;
	}
	
	public int getNumberOfMeasurements() {
		return _numberOfMeasurements;
	}
	
	// Time between external measurements (using alarms) in milli seconds
	public long getTimeBetweenMeasurements() {
		return _timeBetweenMeasurements;
	}
}
