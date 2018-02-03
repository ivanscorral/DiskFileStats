# DiskFileStats
Disk file statistics in Java.

## About

This is is a simple Java application that processes disk file information and is able to generate reports (txt files) of it.

## Usage

There are 3 main classes:

#### FileSearch

This class searchs for all the files and folders contained in the specified folder *searchFiles(File directory)* method.

#### tatEngine

This class processes the file array found by the **FileSearch** class. It has 2 methods (for now):
- ```public HashMap<String, Integer> getFileExtensionStats()``` **Returns a hashmap with file extensions and ocurrences**
- ```public File[] getBiggestFiles(int number)``` **Returns array of biggest files (upto number variable)**

#### StatFile

This acts as a buffer class between the user and StatEngine and its 2 methods write StatEngine's data to a formatted given file.

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
00000.m2ts - 40.457367552 GB, 37.67885971069336 GiB
The Place Beyond the Pines.mkv - 26.048683146 GB, 24.259726652875543 GiB
Marvels.The.Defenders.S01E08.The.Defenders.1080p.NF.WEBRip.DD5.1.x264-GoodPeople.mkv - 7.996335123 GB, 7.447167414240539 GiB
Marvels.The.Defenders.S01E07.Fish.In.The.Jailhouse.1080p.NF.WEBRip.DD5.1.x264-GoodPeople.mkv - 7.099928632 GB, 6.612323813140392 GiB
marvels.the.defenders.s01e07.1080p.webrip.x264-strife.mkv - 2.86753004 GB, 2.670595459640026 GiB
black.mirror.s04e02.internal.1080p.web.x264-strife.mkv - 1.339238444 GB, 1.2472629956901073 GiB
black.mirror.s04e06.internal.1080p.web.x264-strife.mkv - 1.287609982 GB, 1.19918024353683 GiB
black.mirror.s04e04.internal.1080p.web.x264-strife.mkv - 0.892184865 GB, 0.8309119055047631 GiB
BlckMrrr201 [www.newpct.com].avi - 0.548400452 GB, 0.5107377208769321 GiB
Black Mirror 4x05 [www.newpct1.com].avi - 0.504805376 GB, 0.4701366424560547 GiB
BlackMirror203 [www.newpct.com].avi - 0.482987724 GB, 0.44981737062335014 GiB
00034.m2ts - 0.132360192 GB, 0.12327003479003906 GiB
PLAYERUNKNOWN'S BATTLEGROUNDS 01.28.2018 - 23.42.34.01.mp4 - 0.086856435 GB, 0.08089135866612196 GiB
SicilianSveshnikov.pgn - 0.013580813 GB, 0.01264811772853136 GiB
SicilianAlapin2Nf6.pgn - 0.01178517 GB, 0.010975794866681099 GiB

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
txt - 36
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
player - 1
jpg - 2151
inf - 1
htm - 5
cbb - 9
cba - 190
crt - 4
ini - 235
dll - 74
src - 1
cbc - 191
ttf - 8
cbe - 193
CKN - 2
libraries - 1
cbh - 191
cbg - 191
cbj - 193
cbl - 15
wav - 4
cbm - 43
cbp - 191
mp4 - 8
pdf - 3
cbt - 191
srt - 4
cbs - 191
pf - 5
cbv - 1
properties - 24
```

