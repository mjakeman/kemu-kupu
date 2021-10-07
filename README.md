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
java -jar kemu-kupu-0.3.0-alpha1.jar
```


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
