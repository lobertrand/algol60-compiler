#!/bin/bash

files=`ls *.dot`
for f in $files ; do
  pdffile=`echo $f | sed "s/\.dot/\.pdf/g"`
  dot -Tpdf $f -o $pdffile
done
