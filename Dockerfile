# Use a more recent Maven image with OpenJDK
FROM maven:3.9.5-eclipse-temurin-21 AS build

COPY . .
RUN mvn clean package -DskipTests

# Use OpenJDK for the runtime stage
FROM eclipse-temurin:21-jre-jammy

COPY --from=build /target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
