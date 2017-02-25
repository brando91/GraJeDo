#!/bin/bash

function signal(){
	echo "******* $1 *******"
}


set -e

signal "Building GraJeDo"

cd grajedo
rm -rf logs
gradle clean build

signal "Done"
