# JavaFX Asteroids

## About
This is a maven-based project which implements a JavaFX Asteroids Game.

The code is based on a tutorial from Lee Stemkoski which comes in two parts:
- Part One: https://www.youtube.com/watch?v=9xsT6Z6HQfw
- Part Two: https://www.youtube.com/watch?v=7Vb9StpxFtw

The project was created in IntelliJ from the "IntelliJ: Modular with Maven" instructions on https://openjfx.io/openjfx-docs/

The project is modular and does not require a separate javafx library install because it brings in javafx via the maven dependency.
The only prerequisites were to have the Java SDK installed (I'm using 17.0.1 from Oracle) and to install maven (3.8.4).

I use both a Windows 10 desktop and MacBook Pro laptop (2012) and ran the project on both. For Windows, I use Git Bash (mingw64) in Windows terminal.

The code is an almost direct copy of the code from the video tutorial from Lee Stemkoski. There are some comments in the comments section asking
for a link to the code. I will therefore not refactor the code so that it stays close to the original. I reached out to Lee Stemkoski
to ask if he was okay with me putting the code for the video online. Lee confirmed he is fine with me sharing the code from his tutorial video. Thank you Lee! 

Since Lee was so kind to allow me to share, I'd like to encourage you to take a look at his homepage and some of his other videos (and books). You can find them via the following links:
- https://home.adelphi.edu/~stemkoski/
- https://www.youtube.com/playlist?list=PLxpdybrffYlNtjVkMbDD5TULzopOGdJ6D

## How to run the game

### From terminal / windows terminal / git bash
1. cd into the root folder of the project (where the pom.xml is)
2. mvn javafx:run

### From intellij
1. open the project in intellij
2. in the maven pane, navigate to plugins and run javafx:run under javafx
3. or create a maven run configuration with the run field set to "javafx:run"

## Game Assets

Game images were sourced from opengameart.org. All of them are Public Domain CCO license:
https://creativecommons.org/publicdomain/zero/1.0/

Asteroid: https://opengameart.org/content/asteroid-generator-and-a-set-of-generated-asteroids
scaled to 100x100

Space background: https://opengameart.org/content/space-backgrounds-with-stars-and-nubular
scaled to 800x600

Ship: https://opengameart.org/content/purple-space-ship
scaled to 50x50

Laser: https://opengameart.org/content/pixel-orbs
scaled to 10x10

## Creating an installer
To create an installer (exe for windows, dmg for mac), follow these instructions:
1. if using windows make sure wix toolset is installed. for mac make sure xcode is installed.
2. open a terminal
3. cd into the root of this project
4. build-package.sh
5. an exe (installer for windows) or dmg (installer for mac) will be created
6. run the installer

note: on windows, you may have to use explorer go to c:\program files\javafx-asteroids. In the future the build-package
script could be modified to add a shortcut on the desktop or the windows app launcher
