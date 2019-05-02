FROM maven:3.5-jdk-8-alpine
WORKDIR .
RUN mvn clean install

FROM openjdk:8
ADD target/code-test-engineer.jar code-test-engineer.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "code-test-engineer.jar"]