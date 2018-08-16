#!/bin/sh
#####################
# Run Java Snippets #
#####################

banner="Java Snippets - Run"
projectHome=$(cd $(dirname $0); pwd)

displayIntro() {
   cd $projectHome
   echo
   echo $banner
   echo $(echo $banner | sed -e "s/./=/g")
   pwd
   echo
   }

buildClassFiles() {
   cd $projectHome
   echo "Building..."
   rm -rf build
   ls -l src/*.java
   javac -d build src/*.java src/library/*.java
   cp data/countries.xsd build/  #for XmlValidator, see: https://stackoverflow.com/q/16570523
   echo
   }

runSnippets() {
   cd $projectHome/build
   echo "Running..."
   pwd
   echo
   for file in ../src/*.java; do
      name=$(basename $file .java)
      echo "-------------------------------------------------------"
      echo "\$ java $name"
      java $name
      echo
      done
   }

displayIntro
buildClassFiles
runSnippets
