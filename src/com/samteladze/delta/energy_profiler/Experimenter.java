package com.samteladze.delta.energy_profiler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.samteladze.delta.energy_profiler.model.ExperimentOptions;
import com.samteladze.delta.energy_profiler.model.ExperimentType;
import com.samteladze.delta.energy_profiler.model.IExperimentConditionsService;
import com.samteladze.delta.energy_profiler.model.IExperimentWithMeasurementService;
import com.samteladze.delta.energy_profiler.utils.MyLogger;

public class Experimenter {
	
	// Used to stop measurements
	private static int _numberOfMeasurementsRemain = 0;
	// Delay between measurements
	private static long _timeBetweenMeasurements = 0;
	
	// Is started externally from an ACtivity but us initialized in Experimenter
	private static IExperimentConditionsService _conditionsService = null;
	private static ExperimentOptions _experimentOptions = null;
	
	public static final int ALARM_REQUEST_CODE = 0;
	
	// Construct and start energy experiment
	public static void startExperiment(ExperimentType experimentType, Context context) {
		initialize(experimentType);
		
		startConditionsService(context);
		startMeasurements(context);
	}
	
	public static void stopExperiment(Context context) {
		if (_conditionsService != null) {
			context.stopService(new Intent(context, _conditionsService.getClass()));
		}
		else {
			MyLogger.LogError("Failed to stop experiment conditions service", Experimenter.class.getSimpleName());
		}
	}
	
	private static void initialize(ExperimentType experimentType) {
		// Initialize experiment options
		_experimentOptions = ExperimentOptionsFactory.instantiate(experimentType);
		_conditionsService = ExperimentConditionsServiceFactory.instantiate(_experimentOptions.experimentType);
	}
	
	private static void startConditionsService(Context context) {
		if (_conditionsService != null) {
			context.startService(new Intent(context, _conditionsService.getClass()));
		}
		else {
			MyLogger.LogError("Failed to start experiment conditions service", Experimenter.class.getSimpleName());
		}
	}
	
	private static void startMeasurements(Context context) {
		// Start measurements if the experiment supports it
		if (_experimentOptions instanceof IExperimentWithMeasurementService) {
			IExperimentWithMeasurementService currentOptions = (IExperimentWithMeasurementService) _experimentOptions;
			
			// Initialize number of repetitions and time between measurements
			_numberOfMeasurementsRemain = currentOptions.getNumberOfMeasurements();
			_timeBetweenMeasurements = currentOptions.getTimeBetweenMeasurements();
		}
		
		// Schedule the first measurement
		scheduleMeasurement(context);
	}
	
	public static void scheduleMeasurement(Context context) {	
		// Set single alarm 
 		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
 		Intent alarmIntent = new Intent(context, OnAlarmReceiver.class);
		PendingIntent alarmPendingIntent = 
				PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, alarmIntent, 0);		
 		alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					  	 SystemClock.elapsedRealtime() + _timeBetweenMeasurements, alarmPendingIntent);   
	}
	
	// Returns true if more repetitions should be done within the experiment
	public static boolean nextMeasurement() {
		if (_numberOfMeasurementsRemain > 0) {
			--_numberOfMeasurementsRemain;
			return true;
		}
		
		return false;
	}
}
