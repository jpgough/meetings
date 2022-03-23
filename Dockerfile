FROM maven:3.8-eclipse-temurin-11 AS builder
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM eclipse-temurin:11
RUN mkdir /opt/app
COPY --from=builder /usr/src/app/target/meetings-0.0.1-SNAPSHOT.jar /opt/app/app.jar
EXPOSE 8081
ENTRYPOINT [ "java", "-jar", "/opt/app/app.jar" ]