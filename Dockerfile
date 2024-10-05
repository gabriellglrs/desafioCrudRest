FROM maven:3.8.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/desafioCrudRest-0.0.1-SNAPSHOT.jar desafioCrudRest-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","desafioCrudRest-0.0.1-SNAPSHOT.jar"]