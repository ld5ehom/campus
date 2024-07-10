# Stage 1: Build Stage
FROM gradle:8.6-jdk17 AS build

# Set the working directory
WORKDIR /app

# Copy Gradle Wrapper and source files to leverage Gradle cache
COPY ../gradlew .
COPY ../gradle gradle/
COPY ../build.gradle .
COPY ../settings.gradle .

# Install dependencies using Gradle cache
RUN ./gradlew build -x test --stacktrace --no-daemon || return 0

# Copy project source files
COPY ucla-gateway/ ucla-gateway/

# Build required modules
RUN ./gradlew :ucla-gateway:build -x test --stacktrace --no-daemon

# Stage 2: Execution Stage
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy build artifacts
COPY --from=build /app/ucla-gateway/build/libs/*.jar app.jar

# Run the application when the container starts
ENV SPRING_PROFILE "dev"
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILE}", "-jar", "app.jar"]
