FROM openjdk:8
ADD target/code-test-engineer.jar code-test-engineer.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "code-test-engineer.jar"]