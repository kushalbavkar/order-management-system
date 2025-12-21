FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-XX:MaxRAMPercentage=50", "-XX:InitialRAMPercentage=25", "-XX:+UseG1GC", "-jar", "/app/app.jar"]
