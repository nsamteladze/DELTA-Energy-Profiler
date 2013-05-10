package com.samteladze.delta.energy_profiler;

import com.samteladze.delta.energy_profiler.model.IExperimentConditionsService;
import com.samteladze.delta.energy_profiler.utils.MyLogger;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;

public class IdleExperimentConditionsService extends Service implements IExperimentConditionsService {

	private PowerManager.WakeLock wakeLock;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		MyLogger.LogInfo("Started conditions service - IDLE", IdleExperimentConditionsService.class.getSimpleName());
		
		// Acquire the wake lock
		PowerManager mgrPower = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wakeLock = mgrPower.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Wake Lock");
		wakeLock.acquire();
		
		MyLogger.LogInfo("Acquired wake lock", IdleExperimentConditionsService.class.getSimpleName());
		
	    return START_STICKY;
	}
	
	@Override
	public void onCreate(){
        super.onCreate();
        
        MyLogger.LogInfo("Created conditions service - IDLE", IdleExperimentConditionsService.class.getSimpleName());
    }
	
	@Override
    public void onDestroy(){
		MyLogger.LogInfo("Stopped conditions service - IDLE", IdleExperimentConditionsService.class.getSimpleName());
    	
    	wakeLock.release();
    	
    	MyLogger.LogInfo("Released wake lock", IdleExperimentConditionsService.class.getSimpleName());
    	
        super.onDestroy();
    }

}
