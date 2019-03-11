#!/usr/bin/env bash
if [ "$#" -ne 1 ]; then
    echo "Error: Roman numerals expects a single (digit) argument."
    exit 1
fi

JAR_FILE=target/roman-numerals-0.1.0-SNAPSHOT-standalone.jar
if [ -f $JAR_FILE ]
then
	java -jar $JAR_FILE $1
else
    lein uberjar && java -jar $JAR_FILE $1
fi