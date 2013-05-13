package com.samteladze.delta.energy_profiler;

import android.content.Intent;
import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.samteladze.delta.energy_profiler.model.BatteryInfo;
import com.samteladze.delta.energy_profiler.model.IExperimentConditionsService;
import com.samteladze.delta.energy_profiler.model.Net4GExperimentOptions;
import com.samteladze.delta.energy_profiler.utils.CommunicationManager;
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
		
		// Do data transfer
		String urlDownloadedFile = ((Net4GExperimentOptions) Experimenter.getExperimentOptions()).getDownloadedFileURL();
		MyLogger.LogInfo("Start downloading " + urlDownloadedFile, CPUZipExperimentConditionsService.class.getSimpleName());
		CommunicationManager.downloadFileFromURL(urlDownloadedFile, FileManager.getTempFileAbsolutePath());
		
		MyLogger.LogInfo("Stopped conditions service - NET 4G", CPUZipExperimentConditionsService.class.getSimpleName());
	}
}
