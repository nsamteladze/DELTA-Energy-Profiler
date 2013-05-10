package com.samteladze.delta.energy_profiler;

import com.samteladze.delta.energy_profiler.model.BatteryInfo;
import com.samteladze.delta.energy_profiler.utils.FileManager;
import com.samteladze.delta.energy_profiler.utils.MyLogger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class OnAlarmReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{	
		MyLogger.LogInfo("Collecting battery info", OnAlarmReceiver.class.getSimpleName());
		
		// Get and save battery information
		BatteryInfo currentBatteryInfo = BatteryInfo.current(context);
		FileManager.SaveObjectToFile(currentBatteryInfo, FileManager.FILE_NAME_DEFAULT_RESULTS);
		
		if (Experimenter.nextMeasurement()) {
			Experimenter.scheduleMeasurement(context);
		}
		// Stop experiment conditions service if we are done with measurements
		else {
			Experimenter.stopExperiment(context);
		}
	}

}
