#!/bin/bash

function signal(){
	echo "******* $1 *******"
}

function build_environment(){
	if [[ "$(docker images -q $environmentImage)" == "" ]]; then
		signal "Building Environment Image"
		docker build --rm -t $environmentImage scripts
		signal "Done"
	fi
}

function create_jar(){
	signal "Creating Application Jar"
	cd $project
	./gradlew clean install
	rm -rf todeploy
	mkdir -p todeploy
	cp -r build/install/grajedo/* todeploy
	cp -r resources todeploy/resources
	cd todeploy
	tar -czf release.tgz *
	cd ../..
	signal "Done"
}

function build(){
	destroy
	signal "Building GraJeDo Image"
	docker build --rm -t $grajedoImage .
	signal "Done"
}

function destroy(){
	signal "Destroying previous Container and Image"
	docker rm -f $grajedoContainer || true
	if [[ "$(docker images -q $grajedoImage)" != "" ]]; then
		docker rmi $grajedoImage
	fi
	signal "Done"
}

function start(){
	signal "Launching I-Will Container"
	docker run -v /var/log/grajedo:/grajedo/logs -d -p 80:80 --name $grajedoContainer $grajedoImage
	signal "Done"
}

function production_tests(){
	signal "Production Tests"
	cd $project
	./gradlew productionTest
	cd ..
	signal "Done"
}

set -e
environmentImage=environment
grajedoImage=grajedo
grajedoContainer=grajedo
project=grajedo

build_environment
create_jar
build
start
production_tests
