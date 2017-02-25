# GraJeDo
An entrypoint for building your Java web application with Gradle and Jetty and running it in a Docker container.

GraJeDo is just an entrypoint for building your awesome web application. It is meant for **Ubuntu** OS and it is written in **Java 8**. The web server used is **[Jetty](http://www.eclipse.org/jetty/) embedded**, **[Gradle](https://gradle.org/)** is used for building and testing and finally **[Docker](https://www.docker.com/)** for shipping.

### What has been done ###
* Routing
* Static resources loading
* [Rythm](http://rythmengine.org/) template engine
* Logging
* Self Diagnostics
* Unit tests
* Integration tests
* Gradle scripts for building testing and preparing deploy package
* Scripts for installing and launching the application in a docker container

# Usage #

## Build & Test ##
```
./build-and-test.sh
```

## Launch application in a new container ##
```
./launch-application.sh
```

# Set Up #

#### Repository ####
```
sudo apt-get install git-core
cd ~
git clone https://github.com/brando91/GraJeDo.git
cd GraJeDo
./scripts/install-dependency.sh setup
```

#### Java ####
```
./scripts/install-dependency.sh java
```

In case you have Java 7 installed launch the following and select Java 8
```
sudo update-alternatives --config java
sudo update-alternatives --config javac
```

#### Gradle ####
```
./scripts/install-dependency.sh gradle
```

#### Docker ####
* Install Docker following the official guide https://docs.docker.com/engine/installation/linux/ubuntu/
