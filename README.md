# springboot-3.x.x

One of the springboot 3.x.x features
### GraalVM Native Image Support.

Spring Boot 3.0 applications can now be converted into GraalVM native images which can provide significant memory and startup-up performance improvements. 
To get started with GraalVM native images,[please see the updated Spring Boot reference documentation](https://docs.spring.io/spring-boot/docs/3.0.0/reference/html/native-image.html#native-image).

Before all these lets recap our self: How Java application Works (In short with few points):
- We write the code with defined Java Syntax.
- We need JDK to compile it to Bytecode.
- Bytecode is a set of instructions that can be executed by JVM.
- JVM is a Virtual Machine that can execute Bytecode.
- JVM is a part of JRE (Java Runtime Environment).
- JRE is a part of JDK (Java Development Kit)
- JDK is a set of tools that we need to develop a Java application. 
- Springboot application also works in the same way:
  - We generate an executable jar file using maven `mvn clean package` or `./mvnw spring-boot:build-image` or gradle command `gradle build`
  - We run it using `java -jar` command.
  - It runs on HotSpot JVM.
  - HotSpot JVM is a part of JRE (Java Runtime Environment).
  - HotSpot is an implementation of the JVM concept. It was originally developed by Sun, and now it is owned by Oracle. There are other implementations of the JVM specification, like JRockit, IBM J9, among many others.


But When we talk about spring native or GraalVM native support on springboot 3.x.x, it's a bit different:
To get GraalVM native support on springboot 3.x.x:
- It's mandatory to use GraalVM JDK to compile our application into a native executable.
- Once we have the `native executable` , We can run the executable in all the OS without JVM. 

### What is GraalVM?
> https://www.graalvm.org/
- GraalVM is a high-performance JDK ,designed to accelerate the execution of applications written in Java and other JVM languages
- Also It's provide runtimes for JavaScript, Python, and a number of other popular languages
- It can also be used to compile Java applications into native executables.
- GraalVM offers two ways to run Java applications: 
  - on the HotSpot JVM with Graal just-in-time (JIT) compiler 
  - or as an ahead-of-time (AOT) compiled native executable
- Installation:
    - [GraalVM Community Edition](https://www.graalvm.org/downloads/)
    - [GraalVM Enterprise Edition](https://www.graalvm.org/downloads/enterprise/)
    - [GraalVM Native Image](https://www.graalvm.org/reference-manual/native-image/)

### What is GraalVM native image?
- GraalVM Native Image is a technology to compile Java code ahead-of-time (AOT) to a binary – a native executable. 
- A native executable includes only the code required at run time, that is :
  - the application classes, 
  - standard-library classes, 
  - the language runtime, 
  - and statically-linked native code from the JDK.

## To Get started with springboot 3.x.x with GraalVM Native Image Support
> https://docs.spring.io/spring-boot/docs/3.0.0/reference/html/native-image.html#native-image.developing-your-first-application
- If you're starting freshly, start from [https://start.spring.io](https://start.spring.io)
- you need to have this maven or gradle plugins in your pom.xml (as Native Build Tools are a set of Maven and Gradle plugins that allow you to build native images for your Spring Boot applications.)

maven
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.0.0</version>
</parent>
<plugin>
  <groupId>org.graalvm.buildtools</groupId>
  <artifactId>native-maven-plugin</artifactId>
</plugin>
<plugin>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
```
gradle
```shell
dependencies {
  implementation 'org.springframework.boot:spring-boot-starter'
  testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
plugins {
  id 'java'
  id 'org.springframework.boot' version '3.0.5'
  id 'io.spring.dependency-management' version '1.1.0'
  id 'org.graalvm.buildtools.native' version '0.9.20'
}
```

You have two ways to get the native image support:

### Lightweight Container with Cloud Native Buildpacks
If you're already familiar with Spring Boot container images support [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.5/maven-plugin/reference/html/#build-image), this is the easiest way to get started.
Docker should be installed and configured on your machine prior to creating the image.

To create the image, run the following goal:

```
$ ./mvnw spring-boot:build-image -Pnative
```

Then, you can run the app like any other container:

```
$ docker run --rm springboot-3.x.x:0.0.1-SNAPSHOT
```

### Executable with Native Build Tools
Use this option if you want to explore more options such as running your tests in a native image.
The GraalVM `native-image` compiler should be installed and configured on your machine.

NOTE: GraalVM 22.3+ is required.

To create the executable, run the following goal:

```
$ ./mvnw -Pnative native:compile 
```

Then, you can run the app as follows:
```
$ target/springboot-3.x.x
```

You can also run your existing tests suite in a native image.
This is an efficient way to validate the compatibility of your application.

To run your existing tests in a native image, run the following goal:

```
$ ./mvnw test -PnativeTest
```