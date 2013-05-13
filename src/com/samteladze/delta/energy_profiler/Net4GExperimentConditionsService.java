package com.samteladze.delta.energy_profiler;

import java.io.File;

import android.content.Intent;
import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.samteladze.delta.energy_profiler.model.BatteryInfo;
import com.samteladze.delta.energy_profiler.model.IExperimentConditionsService;
import com.samteladze.delta.energy_profiler.utils.CompressionManager;
import com.samteladze.delta.energy_profiler.utils.FileManager;
import com.samteladze.delta.energy_profiler.utils.MyLogger;

public class Net4GExperimentConditionsService extends WakefulIntentService implements IExperimentConditionsService {
	public Net4GExperimentConditionsService() {
		super("Net4GExperimentConditionsService");
		
		MyLogger.LogInfo("Created conditions service - NET 4G", Net4GExperimentConditionsService.class.getSimpleName());
		
	}
	
	@Override
	protected void doWakefulWork(Intent intent) {	
		MyLogger.LogInfo("Started conditions service - NET 4G", Net4GExperimentConditionsService.class.getSimpleName());

		// Get and save initial battery information
		BatteryInfo currentBatteryInfo = BatteryInfo.current(getApplicationContext());
		FileManager.saveObjectToFile(currentBatteryInfo, FileManager.getStepResultsAbsolutePath());
		
		// TEMP
		String pathTestArchive = new File(FileManager.getTestDirAbsolutePath(), "test.zip").getAbsolutePath();
		// Decompress the test archive
		CompressionManager.unpackZip(pathTestArchive, FileManager.getTempDirAbsolutePath());
		
		// Do data transfer
		
		
		MyLogger.LogInfo("Stopped conditions service - NET 4G", CPUZipExperimentConditionsService.class.getSimpleName());
	}
}
