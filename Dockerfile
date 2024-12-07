FROM openjdk:17-jdk-slim
COPY target/analysis-0.0.1-SNAPSHOT.jar /app/analysis-0.0.1-SNAPSHOT.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "analysis-0.0.1-SNAPSHOT.jar"]
