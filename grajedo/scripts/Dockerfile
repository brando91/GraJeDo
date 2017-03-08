FROM ubuntu:14.04

LABEL name="GraJeDo environment"

RUN \
	apt-get update && \
	apt-get install -y --no-install-recommends software-properties-common && \
	add-apt-repository ppa:openjdk-r/ppa && \
	apt-get update && \
	apt-get install -y --no-install-recommends openjdk-8-jdk
