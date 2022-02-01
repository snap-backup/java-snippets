#!/bin/bash
################
# Analyse Code #
# WTFPL        #
################

banner="Java Snippets - Static Code Analysis"
pmdVersion=$(curl -s https://pmd.github.io | grep "Latest Version:" | awk '{ print $3 }')
pmdDownload=https://github.com/pmd/pmd/releases/download/pmd_releases%2F$pmdVersion/pmd-bin-$pmdVersion.zip
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
   echo $pmdVersion
   pmdFolder=$projectHome/static-analysis/pmd/pmd-bin-$pmdVersion
   echo $pmdFolder
   which java || exit
   java --version
   downloadPmd() {
      echo "Downloading..."
      echo $pmdDownload
      mkdir -p static-analysis/pmd
      cd static-analysis/pmd
      pwd
      curl --location --remote-name $pmdDownload
      ls -o *.zip
      unzip pmd-bin-$pmdVersion.zip
      rm pmd-bin-$pmdVersion.zip
      ls -o
      }
   test -d $pmdFolder || downloadPmd
   echo
   }

runPmd() {
   cd $projectHome/static-analysis
   echo "Run PMD:"
   pwd
   report=$projectHome/static-analysis/report.html
   $pmdFolder/bin/run.sh pmd --dir $projectHome/src --rulesets rule-set-good-java.xml --no-cache --format html > $report
   fixPage="s|<head>|<head><style>html { font-family: system-ui; }</style>|"
   sed -i "" "$fixPage" $report
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
