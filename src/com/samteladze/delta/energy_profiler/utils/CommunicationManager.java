package com.samteladze.delta.energy_profiler.utils;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class CommunicationManager {
	public static void downloadFileFromURL(String urlDownloadedFile, String pathOutputFile) {
		try {
		     URL url = new URL(urlDownloadedFile);
		     InputStream inputStream = url.openStream();

		     DataInputStream dataInputStream = new DataInputStream(inputStream);
		     byte[] buffer = new byte[1024];
		     int length;

		     FileOutputStream fileOutputStream = new FileOutputStream(pathOutputFile);
		     while ((length = dataInputStream.read(buffer))>0) {
		    	 fileOutputStream.write(buffer, 0, length);
		     }
		     
		     fileOutputStream.close();
		     dataInputStream.close();
		} 
		catch (Exception e) {
			MyLogger.LogError("Could not download file from " + urlDownloadedFile, CommunicationManager.class.getSimpleName());
		}
	}
}
