FROM ubuntu:14.04

LABEL name="GraJeDo environment"

ENV destinationRoot scripts

COPY scripts/install-dependency.sh /${destinationRoot}/

WORKDIR /${destinationRoot}

RUN locale-gen en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

RUN \
	./install-dependency.sh setup && \
	./install-dependency.sh java
