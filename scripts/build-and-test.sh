#!/bin/bash

function signal(){
	echo "******* $1 *******"
}


set -e
relative_path=`dirname $0`
project=`cd $relative_path/../;pwd`

signal "Building GraJeDo"

cd $project
rm -rf logs
mkdir logs
./gradlew clean build

signal "Done"
