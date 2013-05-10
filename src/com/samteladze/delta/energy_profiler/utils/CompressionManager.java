package com.samteladze.delta.energy_profiler.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CompressionManager {
	public static boolean unpackZip(String pathInput, String pathOutput, String zipname)
	{       
	     InputStream inputStream;
	     ZipInputStream zipInputStream;
	     try 
	     {
	         String filename;
	         inputStream = new FileInputStream(pathInput + zipname);
	         zipInputStream = new ZipInputStream(new BufferedInputStream(inputStream));          
	         ZipEntry zipEntry;
	         byte[] buffer = new byte[1024];
	         int count;

	         while ((zipEntry = zipInputStream.getNextEntry()) != null) 
	         {
	             filename = zipEntry.getName();

	             FileOutputStream fout = new FileOutputStream(pathOutput + filename);

	             while ((count = zipInputStream.read(buffer)) != -1) 
	             {
	                 fout.write(buffer, 0, count);             
	             }

	             fout.close();               
	             zipInputStream.closeEntry();
	         }

	         zipInputStream.close();
	     } 
	     catch(IOException e)
	     {
	         e.printStackTrace();
	         return false;
	     }

	    return true;
	}
}
