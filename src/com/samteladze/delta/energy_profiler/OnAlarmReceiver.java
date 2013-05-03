package com.samteladze.delta.energy_profiler;

import com.commonsware.cwac.wakeful.WakefulIntentService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OnAlarmReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{	
		Log.d("TAG", "Inside the alarm receiver");
		
		if (Experimenter.nextMeasurement()) {
			Experimenter.scheduleMeasurement(context);
		}
		
		// Small amount of work. May be service is an overkill
		WakefulIntentService.sendWakefulWork(context, MeasurementService.class);
	}
}
