#!/bin/bash

if [ $# -ne 1 ] ; then
  echo "Usage: $0 fichier.iup"
  exit 1
fi

java -jar lib/microPIUPK.jar -batch $1
