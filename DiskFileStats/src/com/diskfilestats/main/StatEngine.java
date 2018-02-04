package com.diskfilestats.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class StatEngine {
	
	private ArrayList<File> data;
	private static HashMap<String, ArrayList<File>> extensions;
		
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
		
		for(String extension : extensions.keySet()) {
			result.put(extension, extensions.get(extension).size());
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
		if(extensions.containsKey(extension)) {
			for(File f : extensions.get(extension)) {
				result.add(f);
			}
			
			return result.toArray(files);
		}else {
			return new File[0];
		}
	}
	
	/**
	 * @return Devuelve un mapa con las extensiones encontradas como llave y una lista de archivos con esa extension
	 * como valor.
	 */

	
	private HashMap<String, ArrayList<File>> getFilesAndExtensions(){
		HashMap<String, ArrayList<File>> result = new HashMap<String, ArrayList<File>>();
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
					if(result.containsKey(extension)) {
						result.get(extension).add(f);
					}else {
						ArrayList<File> newExtension = new ArrayList<File>();
						newExtension.add(f);
						result.put(extension, newExtension);
					}
				}
			}
		}	
		return result;
	}

}
