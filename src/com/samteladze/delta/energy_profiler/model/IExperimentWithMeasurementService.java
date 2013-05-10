package com.samteladze.delta.energy_profiler.model;

// Represents experiment that supports an external measurement service
// Implemented by the experiment options classes
public interface IExperimentWithMeasurementService {
	public long getTimeBetweenMeasurements();
}
