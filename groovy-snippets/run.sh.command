#!/bin/bash
#######################
# Run Groovy Snippets #
#######################

banner="Groovy Snippets - Run"
projectHome=$(cd $(dirname $0); pwd)

displayIntro() {
   cd $projectHome
   echo
   echo $banner
   echo $(echo $banner | sed s/./=/g)
   pwd
   source ../add-app-to-path.sh java
   source ../add-app-to-path.sh groovy
   groovyJar=$GROOVY_HOME/lib/$(basename $GROOVY_HOME).jar
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
   echo
   for file in ../src/*.groovy; do
      name=$(basename $file .groovy)
      echo "-------------------------------------------------------"
      echo "$ java -classpath \$groovyJar:. $name"
      java -classpath $groovyJar:. --illegal-access=deny $name
      # --illegal-access=deny, see: https://issues.apache.org/jira/browse/GROOVY-8339
      echo
      done
   }

displayIntro
buildClassFiles
runSnippets
