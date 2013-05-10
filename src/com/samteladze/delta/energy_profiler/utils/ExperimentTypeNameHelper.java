package com.samteladze.delta.energy_profiler.utils;

import com.samteladze.delta.energy_profiler.model.ExperimentType;

// Use to get experiment name to display from the experiment type
public class ExperimentTypeNameHelper {
	
	// Returns experiment name that can be displayed based on the experiment type
	public static String getExperimentName(ExperimentType type) {
		
		String experimentName = null;
		
		switch (type) {
		case Idle:
			experimentName = "Idle";
			break;
		case Screen:
			experimentName = "Screen";
			break;
		case Net_4G:
			experimentName = "4G";
			break;
		case CPU_Diff:
			experimentName = "CPU Diff";
			break;
		default:
			experimentName = "Unknown";
			break;
		}
		
		return experimentName;
	}

}
