# GraJeDo [![Build Status](https://api.travis-ci.org/brando91/GraJeDo.svg?branch=master)](https://travis-ci.org/brando91/GraJeDo)
An entrypoint for building your Java web application with Gradle and Jetty and running it in a Docker container.

GraJeDo is just an entrypoint for building your awesome web application. It is meant for **Ubuntu** OS and it is written in **Java 8**. The web server used is **[Jetty](http://www.eclipse.org/jetty/)** embedded, **[Gradle](https://gradle.org/)** is used for building and testing and finally **[Docker](https://www.docker.com/)** for shipping.

### What has been done ###
* Routing
* Static resources loading
* [Rythm](http://rythmengine.org/) template engine
* Logging
* Self Diagnostics
* Unit tests setup
* Integration tests setup
* Gradle scripts for building testing and preparing deploy package
* Scripts for installing and launching the application in a docker container

The rest is up to you...

## Usage ##

#### Build & Test ####
```
./scripts/build-and-test.sh
```

#### Launch application in a new container ####
```
./scripts/launch-application.sh
```

## Set Up ##

#### Repository ####
```
sudo apt-get install git-core
cd ~
git clone https://github.com/brando91/GraJeDo.git
cd GraJeDo
./scripts/install-dependency.sh setup
```

#### Environment ####
Before launching theese commands edit the script ```scripts/install-dependency.sh``` in order to set your country [locales](https://docs.moodle.org/dev/Table_of_locales)

```
. ./scripts/install-dependency.sh locales
./scripts/install-dependency.sh setup
```


#### Java ####
```
./scripts/install-dependency.sh java
```

In case you also have Java 7 installed launch the following and select Java 8
```
sudo update-alternatives --config java
sudo update-alternatives --config javac
```

#### Docker ####
* Install Docker following the official guide https://docs.docker.com/engine/installation/linux/ubuntu/

#### Eclipse IDE ####
* Install Eclipse Neon from https://eclipse.org/downloads/
* Selecting Java 8
    * Project -> Properties -> Java Build Path -> Libraries -> Add Library -> JRE System Library -> Installed JREs -> Add -> Standard VM
    * install directory should be ```/usr/lib/jvm/java-8-openjdk-amd64```
* Code Assist Preferences
    * window --> preferences --> java --> editor --> content assist --> favorites -> New Member
    * org.hamcrest.Matchers.*
    * org.junit.*
    * org.junit.Assert.*
    * org.junit.Assume.*
    * org.junit.matchers.JUnitMatchers.*
* Configuring a Gradle project: http://www.vogella.com/tutorials/EclipseGradle/article.html
