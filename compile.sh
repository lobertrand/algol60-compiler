#!/bin/bash

if [ $# -ne 1 ] ; then
  echo "Usage: $0 fichier.alg"
  exit 1
fi

java -jar build/libs/algol60_compiler.jar $1 -q



