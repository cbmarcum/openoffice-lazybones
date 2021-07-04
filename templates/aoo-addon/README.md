Apache OpenOffice Add-On for Groovy project template
-----------------------------------------------------

You have just created a basic Apache OpenOffice Add-On project. 
There is a standard project structure for source code and tests.
Simply add your source files to `src/main/<groovy or java>`, your test cases 
to `src/test/<groovy or java>` and then you will be able to build your project 
with `./gradlew distZip` and clean with `./gradlew clean`.

In this project you get:

* A Gradle build file
* A standard project structure:
```
<project dir>
| 
+-- ant
|   |
|   +-- ant.properties
|   |
|   +-- build.xml
|   
+-- gradle
|   |
|   +-- wrapper
|       |
|       +-- gradle-wrapper.jar
|       |
|       +-- gradle-wrapper.properties
|
+-- src
|   |
|   +-- main
|   |   |
|   |   +-- dist
|   |   |   |
|   |   |   +-- description
|   |   |   |   |
|   |   |   |   +-- description_en.txt
|   |   |   |
|   |   |   +-- images
|   |   |   |   |
|   |   |   |   +-- cblogo-16x16.png
|   |   |   |   |
|   |   |   |   +-- cblogo-26x26.png
|   |   |   |   |
|   |   |   |   +-- cblogo-42x42.png
|   |   |   |
|   |   |   +-- META-INF
|   |   |   |   |
|   |   |   |   +-- manifest.xml
|   |   |   |
|   |   |   +-- registration
|   |   |   |   |
|   |   |   |   +-- LICENSE
|   |   |   |
|   |   |   +-- registry
|   |   |   |   |
|   |   |   |   +-- data
|   |   |   |       |
|   |   |   |       +-- org
|   |   |   |           |
|   |   |   |           +-- openoffice
|   |   |   |               |
|   |   |   |               +-- Office
|   |   |   |                   |
|   |   |   |                   +-- Addons.xcu
|   |   |   |                   |
|   |   |   |                   +-- ProtocolHandler.xcu
|   |   |   |
|   |   |   +-- description.xml
|   |   |
|   |   +-- groovy
|   |   |   |
|   |   |   +-- <your-package>
|   |   |       |
|   |   |       +-- CentralRegistrationClass.groovy
|   |   |       |
|   |   |       +-- ${project_class_name}.groovy
|   |   |
|   |   +-- resources
|   |
|   +-- test
|       |
|       +-- groovy
|       |
|       +-- resources
|
+-- build.gradle
|
+-- gradlew
|
+-- gradlew.bat
|
+-- README.md
|
+-- settings.gradle
 ```
## Using the project
1. Edit the build.gradle file and add any additional dependencies if needed.
3. Edit ${project_class_name}.groovy for your new commands.
4. Edit Addons.xcu for your new commands.


## Final Touches:
1. Change the description in description/description_en.txt
2. Add your own 42x42 pixel jpg or png logo in images directory.
3. Add your license or keep the Apache License in registration directory.
4. Edit description.xml for these changes and the Add-On display name.

## Building the Extension
- Clean and build the extension with:
```
./gradlew distZip
```

## More Information
 [OpenOffice-Lazybones GitHub Project](https://github.com/cbmarcum/openoffice-lazybones)

 [OpenOffice-Lazybones Template Docs](https://cbmarcum.github.io/openoffice-lazybones/)

 [Apache OpenOffice Developer's Guide](https://wiki.openoffice.org/wiki/Documentation/DevGuide)
