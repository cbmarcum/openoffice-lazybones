Apache OpenOffice Calc AddIn project template
-----------------------------------------------------

You have just created a basic Apache OpenOffice Calc AddIn project. 
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
           |     |     |    |
           |     |     |    +- CentralRegistrationClass.java
           |     |     |    |
           |     |     |    +- ${project_class_name}.idl
           |     |     |    |
           |     |     |    +- ${project_class_name}Impl.java
           |     |     |    |
           |     |     |    +- X${project_class_name}.idl
           |     |
           |     +- resources
           |
           +- test
                |
                +- groovy
 ```
You need to: 
1. Edit the build.gradle file and add any additional dependencies if needed.
1. Edit X${project_class_name}.idl for your new functions and parameters.
1. Edit CalcAddins.xcu for your new functions and parameters.
