FROM ubuntu:14.04

LABEL name="GraJeDo environment"

ENV destinationRoot scripts

COPY scripts/install-dependency.sh /${destinationRoot}/

WORKDIR /${destinationRoot}

RUN \
	./install-dependency.sh setup && \
	./install-dependency.sh java
