package com.diskfilestats.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class StatFile {
	
	private File statFile;
	private StatEngine statEngine;
	
	private DecimalFormat df2;
	private DecimalFormat df4;
	
	private int unit;
	
	public static final int UNIT_BYTES = 0;
	public static final int UNIT_KILOBYTES = 1;
	public static final int UNIT_MEGABYTES = 2;
	public static final int UNIT_GIGABYTES = 3;
	
	
	private String[][] units = {{"B"}, {"kB", "KiB"},{"MB", "MiB"}, {"GB", "GiB"}};
	
	//Initialize DecimalFormats
	
	{	
			df2 = new DecimalFormat("#.##");
			df4 = new DecimalFormat("#.####");
			df2.setRoundingMode(RoundingMode.CEILING);		
			df4.setRoundingMode(RoundingMode.CEILING);		
	}

	
	public StatFile(String path, StatEngine statEngine) {
		statFile = new File(path);
		if(statFile.exists()) statFile.delete();
		unit = UNIT_MEGABYTES;
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

	public void writeBiggestFilesReport(int number) {
		File[] data = statEngine.getBiggestFiles(number);
		
		String toWrite = "-----" + number + " BIGGEST FILES REPORT "+ Arrays.toString(units[unit])+"-----" + System.getProperty("line.separator");
		
		if(unit == UNIT_BYTES) {
			for(File f : data) {
				toWrite += f.getName() + " - " + f.length() + " " + units[unit][0] + System.getProperty("line.separator");
			}
		}else {
			for(File f : data) {
				if(f != null && f.isFile()) {
					double[] fileSizes = getConvertedFileSize(f, unit);
					
					toWrite += f.getName() + " - " + df4.format(fileSizes[0]) + " " + units[unit][0] + ", "+ df4.format(fileSizes[1]) + " " + units[unit][1] + System.getProperty("line.separator");
				}
			}
		}
		
		
		write(toWrite);
		
	}

	public void writeFilesByExtension(String extension) {
		String toWrite = "-----FILES FOUND WITH ." + extension + " EXTENSION-----" + System.getProperty("line.separator");
		File[] files = statEngine.getFilesWithExtension(extension);
		
		for(File f : files) {
			double[] fileSizes = getConvertedFileSize(f, unit);
			DecimalFormat df = new DecimalFormat("#.####");
			df.setRoundingMode(RoundingMode.CEILING);	
			toWrite += f.getAbsolutePath() + " (" + df2.format(fileSizes[0]) + units[unit][0] +"/" + df2.format(fileSizes[1]) + units[unit][1] + ")" + System.getProperty("line.separator");
		}
		
		write(toWrite);
	
	}
	
	public double[] getConvertedFileSize(File f, int unit) {
		double[] result = new double[2];
		if(unit == UNIT_BYTES) {
			result[0] = f.length();
			result[1] = f.length();
		}else {
			if(f != null && f.isFile()) {
				long size = f.length();
				result[0] = size / Math.pow(1000, unit);
				result[1] = size / Math.pow(1024, unit);					
			}
		}return result;
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

	public void setUnit(int unit) {
		if(!(unit > UNIT_GIGABYTES || unit < UNIT_BYTES)) {
			this.unit = unit;
		}
	}
}
