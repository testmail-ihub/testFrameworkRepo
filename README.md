# Banking App Test Framework

## Overview
This is a Java Selenium BDD test framework for a banking app using Cucumber. The framework is designed to be scalable, maintainable, and easy to use.

## Prerequisites
- Java 17 or later
- Maven 3.8.6 or later
- Selenium WebDriver 4.8.0 or later
- Cucumber 6.10.4 or later

## Setup Instructions
1. Clone the repository using `git clone https://github.com/testmail-ihub/testFrameworkRepo.git`
2. Navigate to the project directory using `cd testFrameworkRepo`
3. Run `mvn clean install` to install all dependencies
4. Run `mvn test` to run all tests

## Project Structure
```
testFrameworkRepo/
pom.xml
src/
  main/
    java/
      com/
        example/
          bankingapp/
            App.java
  test/
    java/
      com/
        example/
          bankingapp/
            BaseTest.java
            LoginStepDefs.java
  resources/
    features/
      login.feature
```

## How to Run Tests
1. Create a new feature file in `src/test/resources/features` with a `.feature` extension
2. Write step definitions in `src/test/java/com/example/bankingapp` using the `@Given`, `@When`, and `@Then` annotations
3. Run `mvn test` to run all tests

## Tech Stack
- Java 17
- Selenium WebDriver 4.8.0
- Cucumber 6.10.4
- Maven 3.8.6

## Dependencies
- `org.seleniumhq.selenium:selenium-java:4.8.0`
- `io.cucumber:cucumber-java:6.10.4`
- `io.cucumber:cucumber-junit:6.10.4`
- `org.junit.jupiter:junit-jupiter:5.9.1`

## Contributing
1. Fork the repository
2. Create a new branch for your feature or bug fix
3. Make your changes and commit them
4. Push your branch to your fork
5. Submit a pull request to the main repository

## License
This project is licensed under the MIT License.