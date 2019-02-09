#!/bin/bash
################
# Analyse Code #
################

banner="Java Snippets - Static Code Analysis"
pmdVersion=$(curl -s https://pmd.github.io | grep "Latest Version:" | awk '{ print $3 }')
projectHome=$(cd $(dirname $0); pwd)

displayIntro() {
   cd $projectHome
   echo
   echo $banner
   echo $(echo $banner | sed s/./=/g)
   pwd
   echo
   }

setupPmd() {
   cd $projectHome
   echo "Setup PMD:"
   source add-app-to-path.sh java
   pmdFolder=$projectHome/static-analysis/pmd/pmd-bin-$pmdVersion
   echo $pmdVersion
   echo $pmdFolder
   downloadPmd() {
      echo "Downloading..."
      mkdir -p static-analysis/pmd
      cd static-analysis/pmd
      pwd
      curl --location --remote-name https://github.com/pmd/pmd/releases/download/pmd_releases%2F$pmdVersion/pmd-bin-$pmdVersion.zip
      unzip pmd-bin-$pmdVersion.zip
      ls -o
      rm pmd-bin-$pmdVersion.zip
      }
   test -d $pmdFolder || downloadPmd
   echo
   }

runPmd() {
   cd $projectHome/static-analysis
   echo "Run PMD:"
   pwd
   report=$projectHome/static-analysis/report.html
   $pmdFolder/bin/run.sh pmd -dir $projectHome/src -rulesets rule-set-good-java.xml -no-cache -f html > $report
   echo
   echo "Report:"
   echo $report
   echo
   }

displayIntro
setupPmd
runPmd
sleep 2
open $report
