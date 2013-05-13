package com.samteladze.delta.energy_profiler.model;

public class Net4GExperimentOptions extends ExperimentOptions {
	private String _urlDownloadedFile = null;
	
	public Net4GExperimentOptions(ExperimentType experimentType, int numberOfMeasurements,
			   					  long timeBetweenMeasurements, String urlDownloadedFile) {
		super(experimentType, numberOfMeasurements, timeBetweenMeasurements);
		
		this._urlDownloadedFile = urlDownloadedFile;
	}

	public String getDownloadedFileURL() {
		return _urlDownloadedFile;
	}
}
