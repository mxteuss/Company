FROM openjdk:17-jdk-slim
LABEL authors="mateus"

WORKDIR /app

# Copiar arquivo JAR gerado pelo Maven/Gradle
COPY target/seu-app.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]