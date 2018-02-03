# DiskFileStats
Disk file statistics in Java.

## About

This is is a simple Java application that processes disk file information and is able to generate reports (txt files) of it.

## Usage

There are 3 main classes:

#### FileSearch

This class searchs for all the files and folders contained in the specified folder *searchFiles(File directory)* method.

#### StatEngine

This class processes the file array found by the **FileSearch** class. It has 2 methods (for now):
- ```public HashMap<String, Integer> getFileExtensionStats()``` **Returns a hashmap with file extensions and ocurrences**
- ```public File[] getBiggestFiles(int number)``` **Returns array of biggest files (upto number variable)**

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
extensionFile.writeBiggestFilesReport(15, StatFile.UNIT_GIGABYTES);
extensionFile.writeExtensionReport();
```

The output is:

```
-----15 BIGGEST FILES REPORT [GB, GiB]-----
00000.m2ts - 40,4574 GB, 37,6789 GiB
The Place Beyond the Pines.mkv - 26,0487 GB, 24,2598 GiB
Marvels.The.Defenders.S01E08.The.Defenders.1080p.NF.WEBRip.DD5.1.x264-GoodPeople.mkv - 7,9964 GB, 7,4472 GiB
Marvels.The.Defenders.S01E06.Ashes.Ashes.1080p.NF.WEBRip.DD5.1.x264-GoodPeople.mkv - 7,9549 GB, 7,4085 GiB
Marvels.The.Defenders.S01E07.Fish.In.The.Jailhouse.1080p.NF.WEBRip.DD5.1.x264-GoodPeople.mkv - 7,1 GB, 6,6124 GiB
00017.m2ts - 5,7275 GB, 5,3341 GiB
00021.m2ts - 5,2281 GB, 4,869 GiB
00011.m2ts - 4,2835 GB, 3,9893 GiB
00031.m2ts - 4,1046 GB, 3,8227 GiB
VID_20180116_162924.mp4 - 3,9051 GB, 3,6369 GiB
marvels.the.defenders.s01e08.1080p.webrip.x264-strife.mkv - 3,2693 GB, 3,0448 GiB
00015.m2ts - 3,0888 GB, 2,8766 GiB
marvels.the.defenders.s01e06.1080p.webrip.x264-strife.mkv - 3,0877 GB, 2,8757 GiB
marvels.the.defenders.s01e07.1080p.webrip.x264-strife.mkv - 2,8676 GB, 2,6706 GiB
marvels.the.defenders.s01e05.1080p.webrip.x264-strife.mkv - 2,7983 GB, 2,6062 GiB

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
exe - 25
xml - 10
jar - 39
onetoc2 - 1
zip - 9
otf - 2
cli - 4
fontindex - 1
7z - 1
png - 48
bdjo - 6
cbtt - 15
bsh - 7
ja - 1
jsa - 1
template - 2
access - 1
bdmv - 11
flags - 1
js - 32
pgi - 27
pgn - 71
prop - 11
mpls - 56
policy - 2
cp2 - 2
BMP - 16
cfg - 2
cp1 - 11
CP1 - 1
cp3 - 2
certs - 1
ijm - 90
url - 4
docx - 5
txt - 52
config - 2
CPN - 2
data - 1
bmp - 326
cbini - 1
rating - 1
tif - 3
ico - 1
dat - 1
m2ts - 58
xig - 2
mkv - 14
html - 2
cpl - 1
bfc - 1
cpn - 7
cpo - 6
jfc - 2
cbgi - 13
xrl - 2
xrt - 2
cib - 39
CBA - 2
gif - 29
CBC - 2
ck1 - 12
CBH - 2
CK1 - 2
CBG - 2
ck3 - 2
ck2 - 2
media - 1
sys - 1
WAV - 21
cit - 39
CBP - 2
security - 1
CBT - 2
avi - 27
CBS - 2
PLS - 1
cib2 - 11
json - 6
class - 27
<file continues...>
```

