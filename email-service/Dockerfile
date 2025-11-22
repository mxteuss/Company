FROM openjdk:17-jdk-slim
LABEL authors="mateus"

WORKDIR /app

COPY target/seu-app.jar app.jar

EXPOSE 9091

ENTRYPOINT ["java", "-jar", "app.jar"]