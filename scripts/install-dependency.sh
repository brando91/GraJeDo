#!/bin/bash

function signal(){
	echo "******* $1 *******"
}

set -e
dependency=$1

if [[ $dependency == setup ]]
then
	signal "Installing preliminary dependency"
	sudo apt-get install -y software-properties-common
	signal "Done"
fi

if [[ $dependency == java ]]
then
	signal "Installing Java"
	sudo add-apt-repository -y ppa:openjdk-r/ppa
	sudo apt-get update
	sudo apt-get install -y openjdk-8-jdk
	signal "Done"
fi
