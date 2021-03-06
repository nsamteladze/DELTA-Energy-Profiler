package com.samteladze.delta.energy_profiler.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CompressionManager {
	public static void unpackZip(String pathArchive, String pathOutput)
	{    
		// Need '/' to create a proper combined path for fileOut
        if (!pathOutput.endsWith("/")) {
       	 pathOutput += "/";
        }
		
		InputStream inputStream;
		ZipInputStream zipInputStream;
		
		try 
		{
		    String filename;
		    inputStream = new FileInputStream(pathArchive);
		    zipInputStream = new ZipInputStream(new BufferedInputStream(inputStream));          
		    ZipEntry zipEntry;
		    byte[] buffer = new byte[1024];
		    int count;
		    File checkFile = null;
		
		    while ((zipEntry = zipInputStream.getNextEntry()) != null) 
		    {
		        filename = zipEntry.getName();
		         
		        
		        if (!(checkFile = (new File(pathOutput + filename)).getParentFile()).exists()) {
		        	checkFile.mkdirs();
		        }
		         
		        /* TODO
		         * Combine paths in FileManager instead of (pathOutput + filename)
		         */
				FileOutputStream fileOut = new FileOutputStream(pathOutput + filename);
				
				while ((count = zipInputStream.read(buffer)) != -1) 
				{
					fileOut.write(buffer, 0, count);             
				}
				
				fileOut.close();               
				zipInputStream.closeEntry();
	         }
		
	         zipInputStream.close();
	    }
	    catch(IOException e)
	    {
	    	MyLogger.LogError("Could not decompress " + pathArchive, CompressionManager.class.getSimpleName());
	    }
	}
}
