FROM environment

LABEL name="GraJeDo"

ENV destinationRoot grajedo

WORKDIR /${destinationRoot}

COPY todeploy/release.tgz /${destinationRoot}

RUN tar -xzf release.tgz
RUN rm release.tgz

ENTRYPOINT ./bin/grajedo



