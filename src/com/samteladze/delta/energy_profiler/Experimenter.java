package com.samteladze.delta.energy_profiler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.view.Window;
import android.view.WindowManager;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.samteladze.delta.energy_profiler.model.ExperimentOptions;
import com.samteladze.delta.energy_profiler.model.ExperimentType;
import com.samteladze.delta.energy_profiler.model.IExperimentConditionsService;
import com.samteladze.delta.energy_profiler.utils.FileManager;
import com.samteladze.delta.energy_profiler.utils.MyLogger;

public class Experimenter {
	
	// Used to stop measurements
	private static int _numberOfMeasurementsRemain = 0;
	// Delay between measurements
	private static long _timeBetweenMeasurements = 0;
	
	private static Window _activityWindow = null;
	
	// Is started externally from an ACtivity but us initialized in Experimenter
	private static IExperimentConditionsService _conditionsService = null;
	private static ExperimentOptions _experimentOptions = null;
	
	public static final int ALARM_REQUEST_CODE = 0;
	
	// Used to stop external measurements
	private static boolean _experimentStopFlag = false;
	
	// Construct and start energy experiment
	public static void startExperiment(ExperimentType experimentType, Context context, Window activityWindow) {
		_activityWindow = activityWindow;
		
		// Don't need to stop
		_experimentStopFlag = false;
		
		initialize(experimentType);
		
		startConditionsService(context);
		startMeasurements(context);
	}
	
	public static void stopExperiment(Context context) {
		stopConditionsService(context);
		_experimentStopFlag = true;
	}
	
	private static void stopConditionsService(Context context) {
		if (_conditionsService != null) {
			/* TODO 
			 * Use WakefulIntentService that can't be stopped like that
			 * Use some other design to do work in background
			 * Redesign the method
			 */
			
			/* TODO
			 * Figure out experiment behavior when conditions service stops and
			 * when all the external measurements are done
			 * Note: number of external measurements = number of experiment iterations,
			 * 		 but each iteration takes different time in case of CPU of NET
			 */
			if ((_experimentOptions.getExperimentType() == ExperimentType.Idle) || 
				(_experimentOptions.getExperimentType() == ExperimentType.Screen)) {
				context.stopService(new Intent(context, _conditionsService.getClass()));
			}
			else {
				MyLogger.LogInfo("End of external measurements. Conditions service was not stopped", 
								 Experimenter.class.getSimpleName());
			}
				 
		}
		else {
			MyLogger.LogError("Failed to stop experiment conditions service", Experimenter.class.getSimpleName());
		}
	}
	
	private static void initialize(ExperimentType experimentType) {
		// Initialize experiment options
		_experimentOptions = ExperimentOptionsFactory.instantiate(experimentType);
		_conditionsService = ExperimentConditionsServiceFactory.instantiate(_experimentOptions.getExperimentType());
		
		// Initialize number of repetitions and time between measurements
		_numberOfMeasurementsRemain = _experimentOptions.getNumberOfMeasurements();
		_timeBetweenMeasurements =  _experimentOptions.getTimeBetweenMeasurements();
		
		// Delete old results
		FileManager.deleteResultsFiles();
	}
	
	private static void startConditionsService(Context context) {
		if (_conditionsService != null) {
			/* TODO 
			 * Use WakefulIntentService for now as a quick solution
			 * Use some other design to do work in background
			 * Redesign the object hierarchy accordingly
			 */
			if ((_experimentOptions.getExperimentType() == ExperimentType.Idle) || 
				(_experimentOptions.getExperimentType() == ExperimentType.Screen)) {
				context.startService(new Intent(context, _conditionsService.getClass()));
			}
			else {
				/* TODO
				 * Conditions service here will be constructed for the second time
				 * First time - in using ExperimentConditionsServiceFactory in initialize()
				 * Service must be constructed only once
				 */
				WakefulIntentService.sendWakefulWork(context, _conditionsService.getClass());
			}
		}
		else {
			MyLogger.LogError("Failed to start experiment conditions service", Experimenter.class.getSimpleName());
		}
	}
	
	private static void startMeasurements(Context context) {
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
		if ((_numberOfMeasurementsRemain > 0) && !_experimentStopFlag) {
			--_numberOfMeasurementsRemain;
			return true;
		}
		
		return false;
	}
	
	public static void setScreenOn() {
		if (_activityWindow != null) {
			_activityWindow.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			MyLogger.LogInfo("Set screen ON", Experimenter.class.getSimpleName());
		}
	}
	
	public static void setScreenNormal() {
		if (_activityWindow != null) {
			_activityWindow.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			MyLogger.LogInfo("Set screen back to normal", Experimenter.class.getSimpleName());
		}
	}
	
	public static ExperimentOptions getExperimentOptions() {
		return _experimentOptions;
	}
}
