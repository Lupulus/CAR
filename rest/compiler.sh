#!/bin/bash
cd src/main/java
find . -name "*.java" |while read A ; do
  echo "Compiling $A"
  javac -cp '../../../lib/*:.' $A
  if [ "$?" != 0 ]; then
    exit 1
  fi
done
