#!/bin/sh
################
# Analyse Code #
################

projectHome=$(cd $(dirname $0); pwd)
pmdVersion=6.6.0

setupPmd() {
   cd $projectHome
   pwd
   echo
   echo "Setup PMD:"
   pmdFolder=$projectHome/pmd/pmd-bin-$pmdVersion
   echo $pmdFolder
   downloadPmd() {
      echo "Downloading..."
      mkdir -p pmd
      cd pmd
      pwd
      curl --location --remote-name https://github.com/pmd/pmd/releases/download/pmd_releases%2F$pmdVersion/pmd-bin-$pmdVersion.zip
      unzip pmd-bin-$pmdVersion.zip
      ls -l
      rm pmd-bin-$pmdVersion.zip
      }
   test -d $pmdFolder || downloadPmd
   echo
   }

runPmd() {
   cd $projectHome/src
   echo "Run PMD:"
   pwd
   report=$projectHome/pmd/report.html
   $pmdFolder/bin/run.sh pmd -dir . -rulesets java-basic,java-design -f html > $report
   echo "--> $report"
   echo
   }

echo
echo "Analyse Java Code"
echo "================="
setupPmd
runPmd
sleep 2
open $report
