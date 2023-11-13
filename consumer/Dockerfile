FROM maven:3.8.1-openjdk-17 AS build-stage
WORKDIR /consumer
COPY . /consumer/.
RUN mvn -f /consumer/pom.xml clean package

FROM openjdk:17-jdk-slim

WORKDIR /consumer
COPY --from=build-stage /consumer/target/*.jar /consumer/*.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/consumer/*.jar"]