package com.diskfilestats.main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class StatEngine {
	
	private ArrayList<File> data;
		
	public StatEngine(ArrayList<File> data) {
		this.data = data;
	}
	
	/**
	 *Extensiones de archivos
	 * 
	 * @return Mapa con las extensiones como llave y el número de ocurrencias de la misma.
	*/
	public HashMap<String, Integer> getFileExtensionStats(){
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		//Config variables
		String NO_EXTENSION = "<no extension>";
		int MAX_EXTENSION_LENGTH = 11;	
		
		for(File f : data) {	
			if(!f.isDirectory()) {				
				String filename = f.getName().trim();
				String[] name = filename.split("\\.");
				String extension;
				
				if(name.length > 1) {
					extension = name[name.length-1];
				}else {
					extension = NO_EXTENSION;
				}if(extension.length() < MAX_EXTENSION_LENGTH) {					
					if(result.get(extension) != null) {						
						int count = 1 + result.get(extension);
						result.put(extension, count);
					}else {
						result.put(extension, 1);
					}	
				}
			}
		}		
		return result;
	}
	
	public File[] getBiggestFiles(int number){
		File[] biggestFiles = new File[number];
		
		for(File f : data) {	
			if(!f.isDirectory()) {			
				long fileSize = f.length();
				for(int i = 0; i < biggestFiles.length; i++) {
					if(biggestFiles[i] == null) {
						biggestFiles[i] = f;
						break;
					}else {
						if(biggestFiles[i].length() < fileSize) {
							biggestFiles[i] = f;
							break;
						}
					}
				}
			}
		}	

		return biggestFiles;
	}	
}
