FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY email-service pom.xml
COPY email-service/src ./email-service/src
COPY EmployeeManager pom.xml
COPY EmployeeManager/src ./EmployeeManager/src

RUN mvn clean package -DskipTests
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/email-service/target/email-service-0.0.1-SNAPSHOT.jar ./email-service.jar
COPY --from=build /app/EmployeeManager/target/EmployeeManager-0.0.1-SNAPSHOT.jar ./EmployeeManager.jar
EXPOSE 9091
EXPOSE 9090

ENTRYPOINT ["top", "-b"]