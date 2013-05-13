package com.samteladze.delta.energy_profiler.model;

public class CPUZipExperimentOptions extends ExperimentOptions {

	private String _pathTestArchive;
	
	public CPUZipExperimentOptions(ExperimentType experimentType, int numberOfMeasurements, String pathTestArchive) {
		super(experimentType, numberOfMeasurements);
		this._pathTestArchive = pathTestArchive;
	}
	
	public String getTestArchivePath() {
		return _pathTestArchive;
	}

}
