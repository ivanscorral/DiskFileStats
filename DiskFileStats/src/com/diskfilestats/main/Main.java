package com.diskfilestats.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	
	
	public static void main(String[] args) {
		File directory = new File("F:\\");
		ArrayList<File> data = FileSearch.searchFiles(directory);
		
		StatEngine statEngine = new StatEngine(data);
		
		StatFile extensionFile = new StatFile("F:\\informes.txt", statEngine);
		extensionFile.writeBiggestFilesReport(15, StatFile.UNIT_MEGABYTES);
		extensionFile.writeFilesByExtension("docx");
		extensionFile.writeFilesByExtension("cfg");
		extensionFile.writeFilesByExtension("py");
		extensionFile.writeExtensionReport();
		
	}

}
