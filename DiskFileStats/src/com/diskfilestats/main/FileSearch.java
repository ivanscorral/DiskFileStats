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
	
	public static ArrayList<File> searchFiles(File directory){
		return r_searchFiles(directory, new ArrayList<File>());
	}
	
	/**
	 * Llamada recursiva oculta.
	 */
	
	private static ArrayList<File> r_searchFiles(File search, ArrayList<File> found){
		ArrayList<File> result = new ArrayList<File>();
		
		//ignore folders starting with $ (recycling bin.. etc)		
		if(search.isDirectory() && !search.getName().startsWith("$")) {
			//copy parameter to temporal variable
			result.addAll(found);
			//add this folder
			result.add(search);
			
			if(search.listFiles() != null) {
				for(File f : search.listFiles()) {
					if(f.isDirectory()) {
						//recursive call
						result = r_searchFiles(f, result);
					}else {
						//add file
						result.add(f);
					}
				}
			}else {
				System.out.println("LOG: Can't read " + search.getPath() + "!");
			}
		}
		
		return result;
	}

}
