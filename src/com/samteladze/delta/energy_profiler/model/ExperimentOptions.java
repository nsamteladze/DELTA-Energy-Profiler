package com.samteladze.delta.energy_profiler.model;

public abstract class ExperimentOptions {
	public ExperimentType experimentType;
	
	protected ExperimentOptions(ExperimentType experimentType) {
		this.experimentType = experimentType;
	}
}
