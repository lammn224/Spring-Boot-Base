FROM eclipse-temurin:17-jdk-focal

WORKDIR /app
ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} spring-boot-base.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","spring-boot-base.jar"]