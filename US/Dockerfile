FROM openjdk:17-jdk-slim

WORKDIR /

COPY your-app.jar /us.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "us.jar"]