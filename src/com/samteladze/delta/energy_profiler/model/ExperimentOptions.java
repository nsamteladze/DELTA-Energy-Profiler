package com.samteladze.delta.energy_profiler.model;

public abstract class ExperimentOptions {
	public ExperimentType experimentType;
	private int _numberOfMeasurements;
	
	protected ExperimentOptions(ExperimentType experimentType, int numberOfMeasurements) {
		this.experimentType = experimentType;
		this._numberOfMeasurements = numberOfMeasurements;
	}
	
	public int getNumberOfMeasurements() {
		return _numberOfMeasurements;
	}
}
