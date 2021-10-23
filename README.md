# Kēmu Kupu - Quiz Engine for Te Reo Maori

### Contributors:

- Matthew Jakeman ([@mjakeman](https://github.com/mjakeman))
- Jordan York ([@jyor212](https://github.com/jyor212))
- Raymond Feng ([@ray-f](https://github.com/ray-f))


### Quick Start:

To run the precompiled application, run the following command in the root project directory:

```
bash run.sh
```

Alternatively, run the jar file directly:

```
java -jar kemu-kupu-0.5.0.jar
```

Please note, if you run into performance issues when running the application, turn off "special effects" in the preferences menu.

### Tech Stack:

![img](https://img.shields.io/badge/language-java%2011-orange)
![img](https://img.shields.io/badge/framework-openjfx--11.0.2-yellow)
![img](https://img.shields.io/badge/build%20tool-gradle%206.8.3-red)


### Dependencies:

- JDK / JRE version 11 - 16 (must be compatible with Gradle 6.8.3). 
  
  _NB: You can check your java runtime version by running `java --version`. This should be between 11 and 16._


### Development:

To run the application in development tool, run the command:
- Windows: `gradlew.bat run`
- Other: `./gradlew run`

This will automatically download the JavaFX dependencies (as well as other build tool dependencies) for you to run the application, and this should launch automatically.

### Build:

To build a standalone executable jar application that is "double-clickable", run the command:
- Windows: `gradlew.bat standaloneJar`
- Unix: `./gradlew standaloneJar`

The built `.jar` file will be located under `build/libs/kemu-kupu-<VERSION>.jar`

### Attribution:
Various media files are used in this project under royalty-free attribution licences.

 * The licence for third-party image assets can be viewed in game
 * The licence for third-party audio assets can be seen below:

```
Audio File Usage and Licences

Background Loop (bg_loop.wav):
https://freesound.org/people/FoolBoyMedia/sounds/264295/
Music track created by FoolBoyMedia (FoolBoyMedia@gmail.com)
Licence: CC-Attribution Non-Commercial

Game Complete (endgame.wav):
https://freesound.org/people/Bertrof/sounds/131659/
Sound effect created by Bertrof (bertrof@hotmail.com)
Licence: CC-Attribution

Correct/Faulted (correct.wav):
https://freesound.org/people/Eponn/sounds/531512/
Sound effect created by Eponn
Licence: CC-Zero

Try Again (tryagain.wav):
https://freesound.org/people/wobesound/sounds/488402/
Sound effect created by wobesound (wobesound.com)
Licence: CC-Zero

Incorrect (incorrect.wav):
https://freesound.org/people/Bertrof/sounds/351565/
Sound effect created by Bertrof (bertrof@hotmail.com)
Licence: CC-Attribution

Skip (skipped.wav):
https://freesound.org/people/Sheyvan/sounds/568290/
Sound effect created by Sheyvan
Licence: CC-Zero
```
