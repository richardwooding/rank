# rank

## Prerequisites ##

Install [Oracle Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) for your platform

If you have multiple JDKs on your path set the JAVA_HOME environment variable.

## Build system ##

**Rank** uses [Gradle 2.9](http://gradle.org/) via the *Gradle Wrapper*. It's not necessary to install it will be fetched by
the gradle wrapper on first run.

On Unix-ish systems the gradlew wrapper is **gradlew** and for Windows systems it is **gradlew.bat**.

All examples are for Unix-ish systems.

## Checking out ##

Use the following commands to clone the git repository and change to the **rank** project directory.

    cd ~
    mkdir projects
    git clone git@github.com:richardwooding/rank.git
    cd rank

## Running tests ##

Invoke the *test* target of the grails build.

    ./grailsw test

## Building distributions ##

Invoke the *assemble* target of the grails build

    ./grailsw assemble
    
Rank's build process is built on upon the [Gradle Application Plugin](https://docs.gradle.org/current/userguide/application_plugin.html)

You can find the distributions at the following locations:

    build/distributions/rank.tar
    build/distributions/rank.zip


## Installation ##

Unpack distribution

    cd ~
    tar xvf projects/build/distributions/rank.tar
    
Add to path    

    export PATH=$PATH:$HOME/rank/bin
    
## Using rank ##

Please note that rank **reads from standard input by default** as is common practice with many unix tools. 

### View help ###

    rank -help
    
### Ranking scores ###
    
Create a text file *scores.txt* with the following scores

    Lions 3, Snakes 3
    Tarantulas 1, FC Awesome 0
    Lions 1, FC Awesome 1
    Tarantulas 3, Snakes 1
    Lions 4, Grouches 0

Execute the following command

    rank < scores.txt
    
The following output will be displayed
    
    


    
    