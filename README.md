# maven-license-checker
Maven plugin that checks whether your dependencies' licenses can work with the project's license.

[![Build Status](https://travis-ci.org/MyMilkedEek/maven-license-checker.svg?branch=develop)](https://travis-ci.org/MyMilkedEek/maven-license-checker)

### Goal

For v 1.0.0 the aim is the following:

- parse project to retrieve the license(s) defined
- parse dependencies to retrieve all licenses of the direct licenses. It is not the intention to check transitive dependencies.
- throw an error if license incompatibilities are found (unless failOnError is set to false of course)
- create a report out of the check

### Installation instructions

- git clone repository
- mvn clean install
- update the pom file of your project with the following plugin

```xml
<plugin>
    <groupId>net.mymilkedeek</groupId>
    <artifactId>maven-license-checker</artifactId>
    <version>${licensechecker.version}</version>
    <executions>
        <execution>
            <phase>compile</phase>
            <goals>
                <goal>check</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

Then during your maven build, license checker will do its magic!