#!/bin/bash

microPIUPK="../../../lib/microPIUPK.jar"

if [ $# -ne 1 ] ; then
  echo "Usage: $0 path/to/file.asm"
  exit 1
fi

asm_file=$1

if [ ! -f $asm_file ] ; then
  echo "File $asm_file doesn't exist"
  exit 1
fi

iup_file=$(echo $asm_file | sed "s/\.src/\.iup/g")

java -jar $microPIUPK -ass $asm_file >/dev/null

echo "> Fichier assemblé -> $iup_file"

if [[ $? -ne 0 || ! -f $iup_file ]] ; then
  exit 1
fi

echo "> Exécution de $iup_file"

java -jar $microPIUPK -batch $iup_file
