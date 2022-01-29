#!/bin/bash
#######################
# Run Groovy Snippets #
# WTFPL               #
#######################

banner="Groovy Snippets - Run"
projectHome=$(cd $(dirname $0); pwd)

displayIntro() {
   cd $projectHome
   echo
   echo $banner
   echo $(echo $banner | sed s/./=/g)
   pwd
   which groovy || exit
   groovy --version
   groovyHome=$(cd $(dirname $(which groovy))/$(dirname $(readlink $(which groovy)))/../libexec; pwd)
   groovyVersion=$(groovy --version | awk '{ print $3 }')
   groovyJar=$groovyHome/lib/groovy-$groovyVersion.jar
   echo $groovyJar
   mkdir -p data
   cp -v ../data/* data
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
   java --version
   echo
   for file in ../src/*.groovy; do
      name=$(basename $file .groovy)
      echo "-------------------------------------------------------"
      echo "$ java -classpath \$groovyJar:. $name"
      java -classpath $groovyJar:. $name
      echo
      done
   }

displayIntro
buildClassFiles
runSnippets
