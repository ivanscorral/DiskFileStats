package com.diskfilestats.main;

import java.io.File;
import java.util.ArrayList;

public class FileSearch {
	
	
	/**
	 * Llamada pública.
	 * @param Directorio en el que buscar.
	 * @return Lista de subcarpetas y subarchivos del directorio. Si el directorio es un archivo se devolverá
	 * un ArrayList<String> vacío.
	 */
	
	public static ArrayList<String> searchFiles(File directory){
		return r_searchFiles(directory, new ArrayList<String>());
	}
	
	/**
	 * Llamada recursiva oculta.
	 */
	
	private static ArrayList<String> r_searchFiles(File search, ArrayList<String> found){
		ArrayList<String> result = new ArrayList<String>();
		
		if(search.isDirectory()) {
			//copy parameter to temporal variable
			result.addAll(found);
			result.add(search.getAbsolutePath() + " - Carpeta");
			
			for(File f : search.listFiles()) {
				if(f.isDirectory()) {
					result = r_searchFiles(f, result);
				}else {
					result.add(f.getAbsolutePath() + " - Archivo");
				}
			}
		}
		
		return result;
	}

}
