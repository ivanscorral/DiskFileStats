package com.diskfilestats.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class StatFile {
	
	private File statFile;
	private StatEngine statEngine;
	
	public static final int UNIT_BYTES = 0;
	public static final int UNIT_KILOBYTES = 1;
	public static final int UNIT_MEGABYTES = 2;
	public static final int UNIT_GIGABYTES = 3;

	private String[][] units = {{"B"}, {"kB", "KiB"},{"MB", "MiB"}, {"GB", "GiB"}};

	
	public StatFile(String path, StatEngine statEngine) {
		statFile = new File(path);
		this.statEngine = statEngine;
	}
	
	public void writeExtensionReport() {
		String toWrite = "-----FILE EXTENSION REPORT-----" + System.getProperty("line.separator");
		HashMap<String, Integer> extensionStats = statEngine.getFileExtensionStats();
		Iterator<String> iterator = extensionStats.keySet().iterator();
		
		while(iterator.hasNext()) {
			String key = iterator.next();
			int value = extensionStats.get(key);
			if(value > 0) {
				toWrite += key + " - " + value + System.getProperty("line.separator");
			}
		}
		
		write(toWrite);
	}

	public void writeBiggestFilesReport(int number, int unit) {
		File[] data = statEngine.getBiggestFiles(number);
		
		String toWrite = "-----" + number + " BIGGEST FILES REPORT "+ Arrays.toString(units[unit])+"-----" + System.getProperty("line.separator");
		
		if(unit == UNIT_BYTES) {
			for(File f : data) {
				toWrite += f.getName() + " - " + f.length() + " " + units[unit][0] + System.getProperty("line.separator");
			}
		}else {
			for(File f : data) {
				long size = f.length();
				double converted0 = size / Math.pow(1000, unit);
				double converted1 = size / Math.pow(1024, unit);
				
				
				toWrite += f.getName() + " - " + converted0 + " " + units[unit][0] + ", "+ converted1 + " " + units[unit][1] + System.getProperty("line.separator");
			}
		}
		
		write(toWrite);
		
	}
	
	private void write(String toWrite) {
		FileWriter fw;
		
		try {
			if(statFile.exists()) {
				toWrite = System.getProperty("line.separator") + toWrite;
			}
			fw = new FileWriter(statFile, true);
			fw.write(toWrite);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
