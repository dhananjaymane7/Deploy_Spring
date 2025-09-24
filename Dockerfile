# Use official OpenJDK 17 image
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the JAR built by Maven into the container
COPY target/ValidateLogin-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that Spring Boot will run on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
