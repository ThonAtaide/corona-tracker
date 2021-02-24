FROM maven:3.6.3-openjdk-17-slim as build
WORKDIR /app
COPY /src /app/src
COPY /pom.xml /app

FROM build as dep
RUN mvn clean install

FROM openjdk:11.0-jre
COPY --from=dep /app/target/corona-tracker-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
