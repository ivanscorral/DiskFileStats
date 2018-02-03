package com.diskfilestats.main;

import java.io.File;
import java.util.ArrayList;

public class FileSearch {
	
	
	/**
	 * Llamada p�blica.
	 * @param Directorio en el que buscar.
	 * @return Lista de subcarpetas y subarchivos del directorio. Si el directorio es un archivo se devolver�
	 * un ArrayList<String> vac�o.
	 */
	
	public static ArrayList<File> searchFiles(File directory){
		return r_searchFiles(directory, new ArrayList<File>());
	}
	
	/**
	 * Llamada recursiva oculta.
	 */
	
	private static ArrayList<File> r_searchFiles(File search, ArrayList<File> found){
		ArrayList<File> result = new ArrayList<File>();
		
		if(search.isDirectory()) {
			//copy parameter to temporal variable
			result.addAll(found);
			//add this folder
			result.add(search);
			
			for(File f : search.listFiles()) {
				if(f.isDirectory()) {
					//recursive call
					result = r_searchFiles(f, result);
				}else {
					//add file
					result.add(f);
				}
			}
		}
		
		return result;
	}

}
