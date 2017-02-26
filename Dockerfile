FROM environment

LABEL name="GraJeDo"

ENV destinationRoot grajedo
ENV sourceRoot grajedo

WORKDIR /${destinationRoot}

COPY ${sourceRoot}/todeploy/release.tgz /${destinationRoot}

RUN tar -xzf release.tgz
RUN rm release.tgz

ENTRYPOINT ./bin/grajedo



