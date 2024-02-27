#!/bin/bash
################
# Analyse Code #
# WTFPL        #
################

banner="Java Snippets - Static Code Analysis"
projectHome=$(cd $(dirname $0); pwd)
pmdVersion=$(curl -s https://pmd.github.io | grep "Latest Version:" | awk '{ print $3 }')
pmdZipFile=pmd-dist-$pmdVersion-bin.zip
pmdDownload=https://github.com/pmd/pmd/releases/download/pmd_releases%2F$pmdVersion/$pmdZipFile
pmdFolder=$projectHome/static-analysis/pmd/pmd-bin-$pmdVersion

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
      unzip $pmdZipFile
      rm $pmdZipFile
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
   $pmdFolder/bin/pmd check --dir $projectHome/src --rulesets rule-set-good-java.xml \
      --no-cache --format html --report-file $report
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
