Apache OpenOffice Client Application project template
-----------------------------------------------------

You have just created a basic Apache OpenOffice Client application. 
There is a standard project structure for source code and tests.
Simply add your source files to `src/main/<groovy or java>`, your test cases 
to `src/test/<groovy or java>` and see below for running your application.

In this project you get:

* A Gradle build file
* A standard project structure:

   <proj>
      |
      +- src
           |
           +- main
           |     |
           |     +- groovy
           |     |     |
           |     |     +- <your-package>
           |     |     |    |
           |     |     |    +- ${project_class_name}.groovy
           |     |     |          
           |     |     |          
           |     |     |
           |     |     +- org
           |     |          |
           |     |          +- openoffice
           |     |                |
           |     |                +- guno
           |     |                      |
           |     |                      +- SpreadsheetDocHelper.groovy
           |     |      
           |     +- java
           |     |     |
           |     |     +- com
           |     |          |
           |     |          +- sun
           |     |               |
           |     |               +- lib
           |     |                    |
           |     |                    +- loader
           |     |                          |
           |     |                          +- InstallationFinder.java
           |     |                          |
           |     |                          +- Loader.java
           |     |                          |
           |     |                          +- WinRegKey.java
           |     |                          |
           |     |                          +- WinRegKeyException.java
           |     |
           |     +- resources
           |           |                           
           |           +- META-INF
           |           |     |
           |           |     +- LICENSE
           |           |     |
           |           |     +- NOTICE
           |           |
           |           +- win
           |                |
           |                +- unowinreg.dll
           |
           +- test
                |
                +- groovy
 

## Using the project: 
1. Add any dependencies to build.gradle.
2. Add logic to ${project_class_name}.groovy.

## Building the Extension
- Clean and build archives for distribution:
```
./gradlew assemble
```
- Clean and build an install directory with a runnable project unpacked:
```
./gradlew installDist
```
