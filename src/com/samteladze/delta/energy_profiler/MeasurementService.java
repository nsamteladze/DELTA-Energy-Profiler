package com.samteladze.delta.energy_profiler;

import android.content.Intent;
import android.util.Log;
import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.samteladze.delta.energy_profiler.model.BatteryInfo;
import com.samteladze.delta.energy_profiler.utils.FileManager;

public class MeasurementService extends WakefulIntentService
{	
	public MeasurementService()
	{
		super("MeasurementService");
	}

	@Override
	protected void doWakefulWork(Intent intent)
	{	
		Log.d("TAG", "Inside measurement service");

		BatteryInfo currentBatteryInfo = BatteryInfo.current(getApplicationContext());
		
		FileManager.WriteBatteryInfo(currentBatteryInfo, FileManager.FILE_NAME_DEFAULT_RESULTS);
	}
}
