#!/bin/sh
rm -rf org
cd src
javac -d .. org/hackyourlife/natneg/*.java
cd ..
jar -cfm Natneg.jar MANIFEST.TXT src/ org/
