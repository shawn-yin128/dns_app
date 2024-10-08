FROM openjdk:17-jdk-slim

WORKDIR /

COPY lab3-0.0.1-SNAPSHOT.jar /fs.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "fs.jar"]