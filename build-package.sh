#!/bin/bash

# refer to README.md for instructions (see section on creating an installer)

mvn clean install
mvn javafx:jlink

jpackage \
--verbose \
--input target/ \
--name JavaFxAsteroids \
--main-jar javafx-asteroids-1.0-SNAPSHOT.jar \
--main-class com.mashton.JavaFxAsteroids \
--runtime-image target/image
