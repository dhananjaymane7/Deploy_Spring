# Stage 1: Build the JAR using Maven and JDK
FROM maven:3.9.3-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy Maven config first (for dependency caching)
COPY pom.xml .

# Copy the source code
COPY src ./src

# Build the Spring Boot JAR, skipping tests
RUN mvn clean package -DskipTests

# Stage 2: Create lightweight image for running the app
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/ValidateLogin-0.0.1-SNAPSHOT.jar app.jar

# Expose the port Spring Boot will run on
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
