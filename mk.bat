@echo off
rm *.class
cd src
javac -d .. org/hackyourlife/natneg/*.java
cd ..
jar -cfm Natneg.jar MANIFEST.TXT *.class src/ org/
pause