package com.samteladze.delta.energy_profiler.model;

import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class BatteryInfo {
	public long timeStamp;
	public double batteryLevel;
	public int batteryVoltage;
	
	public BatteryInfo (long timeStamp, double batteryLevel, int batteryVoltage) {
		this.timeStamp = timeStamp;
		this.batteryLevel = batteryLevel;
		this.batteryVoltage = batteryVoltage;
	}
	
	@Override
	public String toString() {
		return String.format(Locale.US, "%.2f,%d,%d", batteryLevel, batteryVoltage, timeStamp);
	}
	
	// Returns the current battery info with a time stamp
	public static BatteryInfo current(Context context) {
		// Get battery information
		Intent batteryIntent = context.getApplicationContext().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		int rawlevel = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
		double scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
		int voltage = batteryIntent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
		double level = rawlevel / scale;
		
		return new BatteryInfo(System.currentTimeMillis(), level, voltage);
	}
}
