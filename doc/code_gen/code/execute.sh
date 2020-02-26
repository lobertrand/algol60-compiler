#!/bin/bash

if [ $# -ne 1 ] ; then
  echo "Usage: $0 path/to/file.asm"
  exit 1
fi

asm_file=$1

if [ ! -f $asm_file ] ; then
  echo "File $asm_file doesn't exist"
  exit 1
fi

iup_file=$(echo $asm_file | sed "s/\.asm/\.iup/g")

java -jar microPIUPK.jar -ass $asm_file >/dev/null

if [[ $? -ne 0 || ! -f $iup_file ]] ; then
  exit 1
fi

java -jar microPIUPK.jar -batch $iup_file # | sed 's/\\n/\n/g' | head -n -2 | sed 's/Simulation terminée.*//g'
