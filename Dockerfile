FROM maven:3.9.12-amazoncorretto-21-alpine AS build
WORKDIR /app
COPY pom.xml .

COPY EmployeeManager/pom.xml ./EmployeeManager/
COPY email-service/pom.xml ./email-service/

COPY EmployeeManager/src ./EmployeeManager/src
COPY email-service/src ./email-service/src

RUN mvn clean package -DskipTests
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/email-service/target/email-service-0.0.1-SNAPSHOT.jar ./email-service.jar
COPY --from=build /app/EmployeeManager/target/employee-0.0.1-SNAPSHOT.jar ./EmployeeManager.jar
EXPOSE 9091
EXPOSE 9090

ENTRYPOINT ["top", "-b"]