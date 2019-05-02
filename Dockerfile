FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package

FROM openjdk:8
WORKDIR /tmp/
ADD target/code-test-engineer.jar code-test-engineer.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "code-test-engineer.jar"]
