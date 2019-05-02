FROM maven:3.5.2-jdk-8-alpine AS build
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp
RUN mvn package

FROM openjdk:8
WORKDIR /tmp
COPY --from=build /tmp/target/code-test-engineer.jar /tmp
EXPOSE 8085
CMD ["java -jar code-test-engineer.jar"]
