#!/bin/bash

if [ $# -ne 1 ] ; then
  echo "Usage: $0 fichier_assembleur"
  exit 1
fi

set -e # Exit when any command fails

java -jar lib/microPIUPK.jar -ass $1 > /dev/null

iup_file="$(echo $1 | sed 's/\.[^.]*$//g').iup"

java -jar lib/microPIUPK.jar -batch $iup_file
