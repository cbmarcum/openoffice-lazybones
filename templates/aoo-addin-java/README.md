Apache OpenOffice Calc Add-In for Java project template
-----------------------------------------------------

You have just created a basic Apache OpenOffice Calc Add-In project. 
There is a standard project structure for source code and tests.
Simply add your source files to `src/main/<groovy or java>`, your test cases 
to `src/test/<groovy or java>` and then you will be able to build your project 
with `./gradlew distZip` and clean with `./gradlew clean`.

In this project you get:

* A Gradle build file
* A standard project structure:
```
   <proj>
      |
      +- ant
      |     |
      |     +- ant.properties
      |     |
      |     +- build.xml
      |
      +- src
           |
           +- main
           |     |
           |     +- dist
           |     |     |    
           |     |     +- META-INF
           |     |     |     |
           |     |     |     +- manifest.xml
           |     |     |
           |     |     +- description
           |     |     |     |
           |     |     |     +- description_en.txt
           |     |     |    
           |     |     +- images
           |     |     |     |
           |     |     |     +- cblogo-42x42.png
           |     |     |    
           |     |     +- registration
           |     |     |     |
           |     |     |     +- LICENSE
           |     |     |    
           |     |     +- registry
           |     |     |    |
           |     |     |    +- data
           |     |     |          | 
           |     |     |          +- org
           |     |     |               |
           |     |     |               +- openoffice
           |     |     |                     |
           |     |     |                     +- Office
           |     |     |                          |
           |     |     |                          +- CalcAddins.xcu
           |     |     | 
           |     |     +- description.xml 
           |     |
           |     +- groovy
           |     |     |
           |     |     +- <your-package>
           |     |          |
           |     |          +- CentralRegistrationClass.java
           |     |          |
           |     |          +- ${project_class_name}.idl
           |     |          |
           |     |          +- ${project_class_name}Impl.java
           |     |          |
           |     |          +- X${project_class_name}.idl
           |     |
           |     +- resources
           |
           +- test
                |
                +- groovy

 ```
## Using the project
1. Edit the build.gradle file and add any additional dependencies if needed.
2. Edit X${project_class_name}.idl for your new functions and parameters.
3. Edit ${project_class_name}Impl.java for your new functions and parameters.
4. Edit CalcAddins.xcu for your new functions and parameters.


## Final Touches:
1. Change the description in description/description_en.txt
2. Add your own 42x42 pixel jpg or png logo in images directory.
3. Add your license or keep the Apache License in registration directory.
4. Edit description.xml for these changes and the Add-In display name.

## Building the Extension
- Clean and build the extension with:
```
./gradlew distZip
```

## More Information
 [OpenOffice-Lazybones GitHub Project](https://github.com/cbmarcum/openoffice-lazybones)

 [OpenOffice-Lazybones Template Docs](https://cbmarcum.github.io/openoffice-lazybones/)

 [Apache OpenOffice Developer's Guide](https://wiki.openoffice.org/wiki/Documentation/DevGuide)
