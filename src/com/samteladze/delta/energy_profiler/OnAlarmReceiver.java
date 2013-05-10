package com.samteladze.delta.energy_profiler;

import com.samteladze.delta.energy_profiler.model.BatteryInfo;
import com.samteladze.delta.energy_profiler.utils.FileManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class OnAlarmReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{	
		if (Experimenter.nextMeasurement()) {
			Experimenter.scheduleMeasurement(context);
		}
		
		// Get and save battery information
		BatteryInfo currentBatteryInfo = BatteryInfo.current(context);
		FileManager.SaveObjectToFile(currentBatteryInfo, FileManager.FILE_NAME_DEFAULT_RESULTS);
	}
}
