#!/bin/bash
#######################
# Run Groovy Snippets #
#######################

banner="Groovy Snippets - Run"
projectHome=$(cd $(dirname $0); pwd)

addAppToPath() {
   # Pass in the name of the app, such as: "ant", "mongodb", or "groovy"
   # Example usage:
   #     addAppToPath groovy
   # Uses the ~/apps/ folder and assumes structure like: ~/apps/groovy/groovy-2.5.2/bin/groovy
   appName=$1
   addBin() {
      PATH=$PATH:$(find ~/apps/$appName/*/bin -type d | tail -1)
      which $appName || { echo "*** Folder 'bin' not found at: ~/apps/$appName"; exit; }
      }
   which $appName || addBin
   echo
   }

setupGroovySnippets() {
   cd $projectHome
   echo "Setup..."
   addAppToPath groovy
   groovy -version
   groovyHome=$(dirname $(dirname $(which groovy)))
   groovyJar=$groovyHome/lib/$(basename $groovyHome).jar
   echo $groovyJar
   mkdir -p data
   cp -v ../data/* data
   echo
   }

displayIntro() {
   cd $projectHome
   echo
   echo $banner
   echo $(echo $banner | sed s/./=/g)
   pwd
   echo
   }

buildClassFiles() {
   cd $projectHome
   echo "Building..."
   rm -rf build
   groovyc -d build src/*.groovy
   ls -1 build/*.class
   cp data/countries.xsd build  #for XmlValidator, see: https://stackoverflow.com/q/16570523
   echo
   }

runSnippets() {
   cd $projectHome/build
   echo "Running..."
   pwd
   echo
   for file in ../src/*.groovy; do
      name=$(basename $file .groovy)
      echo "-------------------------------------------------------"
      echo "$ java -cp \$groovyJar:. $name"
      java -cp $groovyJar:. $name
      echo
      done
   }

displayIntro
setupGroovySnippets
buildClassFiles
runSnippets
