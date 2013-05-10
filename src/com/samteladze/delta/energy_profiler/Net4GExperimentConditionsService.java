package com.samteladze.delta.energy_profiler;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;

import com.samteladze.delta.energy_profiler.model.IExperimentConditionsService;
import com.samteladze.delta.energy_profiler.utils.MyLogger;

public class Net4GExperimentConditionsService extends Service implements IExperimentConditionsService {

	private PowerManager.WakeLock wakeLock;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		MyLogger.LogInfo("Started conditions service - Net 4G", Net4GExperimentConditionsService.class.getSimpleName());
		
		// Acquire the wake lock
		PowerManager mgrPower = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wakeLock = mgrPower.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Wake Lock");
		wakeLock.acquire();
		
		MyLogger.LogInfo("Acquired wake lock", Net4GExperimentConditionsService.class.getSimpleName());
		
	    return START_STICKY;
	}
	
	@Override
	public void onCreate(){
        super.onCreate();
        
        MyLogger.LogInfo("Created conditions service - Net 4G", Net4GExperimentConditionsService.class.getSimpleName());
    }
	
	@Override
    public void onDestroy(){
		MyLogger.LogInfo("Stopped conditions service - Net 4G", Net4GExperimentConditionsService.class.getSimpleName());
    	
    	wakeLock.release();
    	
    	MyLogger.LogInfo("Released wake lock", Net4GExperimentConditionsService.class.getSimpleName());
    	
        super.onDestroy();
    }

}
