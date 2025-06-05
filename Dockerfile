# Maven + Java 21
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# RUN the application
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]