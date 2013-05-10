package com.samteladze.delta.energy_profiler;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.os.PowerManager;

import com.samteladze.delta.energy_profiler.model.BatteryInfo;
import com.samteladze.delta.energy_profiler.model.IExperimentConditionsService;
import com.samteladze.delta.energy_profiler.utils.CompressionManager;
import com.samteladze.delta.energy_profiler.utils.FileManager;
import com.samteladze.delta.energy_profiler.utils.MyLogger;

public class CPUZipExperimentConditionsService extends Service implements IExperimentConditionsService {

	private PowerManager.WakeLock wakeLock;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		MyLogger.LogInfo("Started conditions service - CPU ZIP", CPUZipExperimentConditionsService.class.getSimpleName());
		
		// Acquire the wake lock
		PowerManager mgrPower = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wakeLock = mgrPower.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Wake Lock");
		wakeLock.acquire();
		
		MyLogger.LogInfo("Acquired wake lock", CPUZipExperimentConditionsService.class.getSimpleName());
		
		// Get and save battery information
		BatteryInfo currentBatteryInfo = BatteryInfo.current(getApplicationContext());
		FileManager.SaveObjectToFile(currentBatteryInfo, FileManager.FILE_NAME_DEFAULT_RESULTS);
		
		while (Experimenter.nextMeasurement()) {
			MyLogger.LogInfo("Do compression", CPUZipExperimentConditionsService.class.getSimpleName());
			
			doCompressionTest(FileManager.PATH_TEMP_DIR);
			
			// Get and save battery information
			currentBatteryInfo = BatteryInfo.current(getApplicationContext());
			FileManager.SaveObjectToFile(currentBatteryInfo, FileManager.FILE_NAME_DEFAULT_RESULTS);
		}
		
		stopSelf();
		
	    return START_STICKY;
	}
	
	@Override
	public void onCreate() {
        super.onCreate();
        
        MyLogger.LogInfo("Created conditions service - CPU ZIP", CPUZipExperimentConditionsService.class.getSimpleName());
    }
	
	@Override
    public void onDestroy() {
		MyLogger.LogInfo("Stopped conditions service - CPU ZIP", CPUZipExperimentConditionsService.class.getSimpleName());
    	
    	wakeLock.release();
    	
    	MyLogger.LogInfo("Released wake lock", CPUZipExperimentConditionsService.class.getSimpleName());
    	
    	FileManager.cleanTempDir();
    	
        super.onDestroy();
    }
	
	private void doCompressionTest(String pathOutput) {
		CompressionManager.unpackZip(Environment.getExternalStorageDirectory().getAbsolutePath() + "/", 
				Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + pathOutput + "/", "target.zip");
	}
}
