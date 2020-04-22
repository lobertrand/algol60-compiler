#!/bin/bash

if [ $# -ne 2 ] ; then
  echo "Usage: $0 fichier_algol60 fichier_assembleur"
  exit 1
fi

java -jar build/libs/algol60_compiler.jar $1 -q
