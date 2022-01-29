#!/bin/bash
#####################
# Run Java Snippets #
# WTFPL             #
#####################

banner="Java Snippets - Run"
projectHome=$(cd $(dirname $0); pwd)

displayIntro() {
   cd $projectHome
   echo
   echo $banner
   echo $(echo $banner | sed s/./=/g)
   pwd
   which java || exit
   java --version
   echo
   }

buildClassFiles() {
   cd $projectHome
   echo "Building..."
   rm -rf build
   javac -d build src/*.java src/library/*.java
   ls -1 build/javasnippets/*.class
   cp data/countries.xsd build/javasnippets  #for XmlValidator, see: https://stackoverflow.com/q/16570523
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
      echo "$ java $name"
      java javasnippets/$name
      echo
      done
   }

displayIntro
buildClassFiles
runSnippets
