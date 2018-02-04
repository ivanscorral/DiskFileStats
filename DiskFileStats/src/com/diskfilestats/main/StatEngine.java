package com.diskfilestats.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class StatEngine {
	
	private ArrayList<File> data;
	private static HashMap<File, String> extensions;
		
	public StatEngine(ArrayList<File> data) {
		this.data = data;
		if(extensions == null) {
			extensions = getFilesAndExtensions();
		}
	}
	
	/**
	 *Extensiones de archivos
	 * 
	 * @return Mapa con las extensiones como llave y el número de ocurrencias de la misma.
	*/
	public HashMap<String, Integer> getFileExtensionStats(){
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		
		for(String extension : extensions.values()) {
			if(result.containsKey(extension)) {
				int count = 1 + result.get(extension);
				result.put(extension, count);
			}else {
				result.put(extension, 1);
			}
		}
		
		
		return result;
	}
	
	/** 
	 * @param number Número de archivos a mostrar
	 * @return Devuelve los archivos más grandes (hasta n = number).
	 */
	
	public File[] getBiggestFiles(int number){
		ArrayList<File> files = new ArrayList<File>();
		files.addAll(data);
		File[] biggestFiles = new File[number];
		
		Collections.sort(files, new Comparator<File>() {

			@Override
			public int compare(File o1, File o2) {
				double a = o2.length() - o1.length();
				return (int)Math.signum(a);
			}
			
		});
		
		for(int i = 0; i < biggestFiles.length; i++) {
			if(i < files.size()) {
				biggestFiles[i] = files.get(i);
			}
		}
		
		return biggestFiles;
	}
	
	/**
	 * @param extension: Extensión a buscar.
	 * @return Devuelve los archivos encontrados con esa extensión.
	 */

	public File[] getFilesWithExtension(String extension) {
		ArrayList<File> result = new ArrayList<File>();
		File[] files = new File[0];
		if(extensions.containsValue(extension)) {
			for(File f : extensions.keySet()) {
				if(extensions.get(f).equals(extension)) {
					result.add(f);
				}
			}
			
			return result.toArray(files);
		}else {
			return new File[0];
		}
	}
	
	
	//TODO Rehacer estrucura de datos a una que permita manipulación de datos más eficiente.
	
	private HashMap<File, String> getFilesAndExtensions(){
		HashMap<File, String> result = new HashMap<File, String>();
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
					result.put(f, extension);	
				}
			}
		}	
		return result;
	}

}
