#!/bin/bash

function signal(){
	echo "******* $1 *******"
}

function install_package(){
	sudo apt-get update
	sudo apt-get install -y --no-install-recommends "$@"
}

set -e
dependency=$1

if [[ $dependency == setup ]]
then
	signal "Installing preliminary dependency"
	install_package software-properties-common
	signal "Done"
fi

if [[ $dependency == locales ]]
then
	signal "Installing Locales"
	locale-gen en_US.UTF-8
	export LC_ALL=en_US.UTF-8
	export LANG=en_US.UTF-8
	export LANGUAGE=en_US:en
	signal "Done"
fi

if [[ $dependency == java ]]
then
	signal "Installing Java"
	sudo add-apt-repository -y ppa:openjdk-r/ppa
	install_package openjdk-8-jdk
	signal "Done"
fi
