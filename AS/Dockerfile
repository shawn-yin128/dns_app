FROM openjdk:17-jdk-slim

WORKDIR /

COPY lab3-0.0.1-SNAPSHOT.jar /as.jar

EXPOSE 53533

ENTRYPOINT ["java", "-jar", "as.jar"]