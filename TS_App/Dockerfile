FROM openjdk:23-jdk-slim
WORKDIR /app
COPY /target/TS_App-0.0.1-SNAPSHOT.jar /app/TS_App.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "TS_App.jar"]