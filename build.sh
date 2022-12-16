#!/bin/sh
cd app/source/cz/radeknolc/cameljourney
javac -d ./build *
cd build
jar cmvf ../../../../META-INF/MANIFEST.MF CamelJourney.jar *
mv CamelJourney.jar ../../../../../../CamelJourney.jar
cd ..
rm -r build
echo "Done."