# Testing gRPC service in Kotlin and TestNG
This is an example project showing the basics of grpc testing with kotlin/gradle/testng

## Setup 
* install [Intellij Idea](https://www.jetbrains.com/idea/download/?section=linux)
* install [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or use Intellij to manage Java version

## Executing Examples

* Build teh server jar file and start the gRPC server
  * `gradlew build -x test`
  * `cd build/libs`
  * `java -jar grpc_app-1.0-SNAPSHOT.jar`
* Start the tests for module 1
  * `gradlew :Example_1:test`
* Start the tests for module 2
  * `gradlew :Example_2:test`