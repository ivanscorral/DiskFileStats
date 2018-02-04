# DiskFileStats
Disk file statistics in Java.

## About

This is is a simple Java application that processes disk file information and is able to generate reports (txt files) of it.

## Usage

There are 3 main classes:

#### FileSearch

This class searchs for all the files and folders contained in the specified folder *searchFiles(File directory)* method.

#### StatEngine

This class processes the file array found by the **FileSearch** class. It has 4 public methods (for now):
- ```public void setUnit(int unit)``` **Specifies the unit for the following reports, default is MB/MiB**
- ```public HashMap<String, Integer> getFileExtensionStats()``` **Returns a hashmap with file extensions and ocurrences**
- ```public File[] getBiggestFiles(int number)``` **Returns array of biggest files (upto number variable)**
- ```public File[] getFilesWithExtension(String extension)``` **Returns array of files with that extension**

All possible units are:

```java
public static final int UNIT_BYTES = 0;
public static final int UNIT_KILOBYTES = 1;
public static final int UNIT_MEGABYTES = 2;
public static final int UNIT_GIGABYTES = 3;
```

#### StatFile

This acts as a buffer class between the user and StatEngine and its 2 methods write StatEngine's data to a formatted given file.

## TODO

- [ ] Write biggest folder algorithm and report methods.
- [ ] Add a graphical interface.
- [x] Write main recursive file search algorithm.
- [x] Write file extension algorithm and report methods.
- [x] Write biggest file algorithm and report methods.

## Example:

This code reads all subfolders in F:\\ and generates a report to F:\\informes.txt with extension and filesize infos.

```java
File directory = new File("F:\\");
ArrayList<File> data = FileSearch.searchFiles(directory);
		
StatEngine statEngine = new StatEngine(data);
		
StatFile extensionFile = new StatFile("F:\\informes.txt", statEngine);
extensionFile.setUnit(StatFile.UNIT_MEGABYTES);
extensionFile.writeBiggestFilesReport(15);
extensionFile.setUnit(StatFile.UNIT_KILOBYTES);
extensionFile.writeFilesByExtension("cfg");
extensionFile.writeFilesByExtension("py");
extensionFile.writeExtensionReport();
```

The output is:

```
-----15 BIGGEST FILES REPORT [MB, MiB]-----
00000.m2ts - 40457,3676 MB, 38583,1524 MiB
The Place Beyond the Pines.mkv - 26048,6832 MB, 24841,9601 MiB
Marvels.The.Defenders.S01E08.The.Defenders.1080p.NF.WEBRip.DD5.1.x264-GoodPeople.mkv - 7996,3352 MB, 7625,8995 MiB
Marvels.The.Defenders.S01E06.Ashes.Ashes.1080p.NF.WEBRip.DD5.1.x264-GoodPeople.mkv - 7954,8079 MB, 7586,296 MiB
Marvels.The.Defenders.S01E07.Fish.In.The.Jailhouse.1080p.NF.WEBRip.DD5.1.x264-GoodPeople.mkv - 7099,9287 MB, 6771,0196 MiB
00017.m2ts - 5727,4061 MB, 5462,0801 MiB
00021.m2ts - 5228,0095 MB, 4985,8184 MiB
00011.m2ts - 4283,4248 MB, 4084,9922 MiB
00031.m2ts - 4104,5545 MB, 3914,4083 MiB
VID_20180116_162924.mp4 - 3905,0717 MB, 3724,1666 MiB
marvels.the.defenders.s01e08.1080p.webrip.x264-strife.mkv - 3269,2889 MB, 3117,8368 MiB
00015.m2ts - 3088,7117 MB, 2945,625 MiB
marvels.the.defenders.s01e06.1080p.webrip.x264-strife.mkv - 3087,6664 MB, 2944,6282 MiB
marvels.the.defenders.s01e07.1080p.webrip.x264-strife.mkv - 2867,5301 MB, 2734,6898 MiB
marvels.the.defenders.s01e05.1080p.webrip.x264-strife.mkv - 2798,2843 MB, 2668,6519 MiB

-----FILES FOUND WITH .cfg EXTENSION-----
F:\Descargas\ij150-win-java8\ImageJ\jre\lib\amd64\jvm.cfg (0,64kB/0,62KiB)
F:\Descargas\ij150-win-java8\ImageJ\ImageJ.cfg (0,06kB/0,06KiB)

-----FILES FOUND WITH .py EXTENSION-----
F:\Descargas\ij150-win-java8\ImageJ\plugins\Scripts\Python_Animation.py (0,75kB/0,73KiB)
F:\Descargas\ij150-win-java8\ImageJ\macros\Examples\Python\Synthetic_Image.py (0,35kB/0,34KiB)
F:\Descargas\ij150-win-java8\ImageJ\macros\Examples\Python\Overlay.py (0,91kB/0,88KiB)
F:\Descargas\ij150-win-java8\ImageJ\macros\Examples\Python\Sphere.py (0,35kB/0,34KiB)
F:\Descargas\ij150-win-java8\ImageJ\macros\Examples\Python\Rotational_Animation.py (0,62kB/0,61KiB)
F:\Descargas\ij150-win-java8\ImageJ\macros\Examples\Python\Animated_Gaussian_Blur.py (0,75kB/0,73KiB)

-----FILE EXTENSION REPORT-----
rb1 - 1
rb0 - 1
rb2 - 1
cko - 6
ckn - 7
PNG - 2
py - 6
lut - 27
cit2 - 11
clpi - 116
bak - 3
java - 51
exe - 27
xml - 10
jar - 39
onetoc2 - 1
otf - 2
zip - 9
cli - 4
fontindex - 1
7z - 1
png - 49
<file continues...>
```

