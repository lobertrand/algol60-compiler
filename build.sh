#!/bin/bash

if [ $# -ne 0 ] ; then
  echo "Usage: $0"
  exit 1
fi

./gradlew jar --console=plain -q # generates build/libs/algol60_compiler.jar
