# testFrameworkRepo

## Overview
The `testFrameworkRepo` project is a Java-based test framework designed to provide a robust and efficient way to perform system health checks. This repository contains the source code, build configuration, and CI/CD pipeline for automating the testing process.

## Tech Stack
- **Java**: The primary programming language used for developing the test framework.
- **Maven**: The build automation tool used to manage dependencies and compile the code.
- **Jenkins**: The CI/CD tool used to automate the build, test, and deployment process.

## Directory Structure
The repository is structured as follows:

```
testFrameworkRepo/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │       ├── snc/
│   │   │           ├── core/
│   │   │               ├── BaseService.java
│   │   │               ├── HealthCheckService.java
│   │   ├── resources/
│   ├── test/
│   │   ├── java/
│   │   │   ├── com/
│   │   │       ├── snc/
│   │   │           ├── core/
│   │   │               ├── HealthCheckServiceTest.java
├── jenkins-pipeline.yaml
```

### Key Files
- **BaseService.java**: The base class for all services, providing a logger and utility methods.
- **HealthCheckService.java**: The service class responsible for checking the system health.
- **HealthCheckServiceTest.java**: The unit test class for the `HealthCheckService`.
- **jenkins-pipeline.yaml**: The CI/CD pipeline configuration file for automating the build and test process.

## Setup Instructions
To set up the project, follow these steps:
1. Clone the repository: `git clone https://github.com/testmail-ihub/testFrameworkRepo.git`
2. Navigate to the project directory: `cd testFrameworkRepo`
3. Build the project using Maven: `mvn clean install`
4. Run the tests: `mvn test`

## CI/CD Process
The CI/CD pipeline is configured to trigger on pull requests and automate the build, test, and deployment process. The pipeline performs the following steps:
1. Checkout the code from the repository.
2. Install dependencies using Maven.
3. Run all tests with `mvn test`.
4. Archive test reports and notify on build status.

## Contribution Guidelines
- Fork the repository and create a feature branch.
- Make your changes and ensure all tests pass.
- Submit a pull request for review.
