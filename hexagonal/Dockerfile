FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/hexagonal-1.0.0.jar
COPY ${JAR_FILE} app_hexagonal.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_hexagonal.jar"]