#!/bin/bash

function signal(){
	echo "******* $1 *******"
}


set -e

signal "Building GraJeDo"

cd grajedo
rm -rf logs
mkdir logs
./gradlew clean build

signal "Done"
