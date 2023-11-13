FROM maven:3.8.1-openjdk-11 AS build-stage
WORKDIR /producer
COPY . /producer/.
RUN mvn -f /producer/pom.xml clean package

FROM openjdk:11-jre

WORKDIR /producer
COPY --from=build-stage /producer/target/*.jar /producer/*.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/producer/*.jar"]