package com.samteladze.delta.energy_profiler;

import android.content.Intent;
import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.samteladze.delta.energy_profiler.model.BatteryInfo;
import com.samteladze.delta.energy_profiler.model.CPUZipExperimentOptions;
import com.samteladze.delta.energy_profiler.model.IExperimentConditionsService;
import com.samteladze.delta.energy_profiler.utils.CompressionManager;
import com.samteladze.delta.energy_profiler.utils.FileManager;
import com.samteladze.delta.energy_profiler.utils.MyLogger;

public class CPUZipExperimentConditionsService extends WakefulIntentService implements IExperimentConditionsService {
	public CPUZipExperimentConditionsService() {
		super("CPUZipExperimentConditionsService");
		
		MyLogger.LogInfo("Created conditions service - CPU ZIP", CPUZipExperimentConditionsService.class.getSimpleName());
		
	}
	
	@Override
	protected void doWakefulWork(Intent intent) {	
		MyLogger.LogInfo("Started conditions service - CPU ZIP", CPUZipExperimentConditionsService.class.getSimpleName());

		// Get and save initial battery information
		BatteryInfo currentBatteryInfo = BatteryInfo.current(getApplicationContext());
		FileManager.saveObjectToFile(currentBatteryInfo, FileManager.getStepResultsAbsolutePath());
		
		String pathTestArchive = ((CPUZipExperimentOptions) Experimenter.getExperimentOptions()).getTestArchivePath();
		
		for (int i = 0; i < Experimenter.getExperimentOptions().getNumberOfMeasurements(); ++i) {
			MyLogger.LogInfo("Do decompression", CPUZipExperimentConditionsService.class.getSimpleName());
			
			// Decompress the test archive
			CompressionManager.unpackZip(pathTestArchive, FileManager.getTempDirAbsolutePath());
			
			// Clean the output directory
			
			
			// Get and save battery information
			currentBatteryInfo = BatteryInfo.current(getApplicationContext());
			FileManager.saveObjectToFile(currentBatteryInfo, FileManager.getStepResultsAbsolutePath());
		}
		
		MyLogger.LogInfo("Stopped conditions service - CPU ZIP", CPUZipExperimentConditionsService.class.getSimpleName());
	}
}
