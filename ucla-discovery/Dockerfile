# Step 1: Build stage
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

# Copy the project source
COPY ucla-discovery/ ucla-discovery/

# Build the necessary module
RUN ./gradlew :ucla-discovery:build -x test --stacktrace --no-daemon

# Step 2: Runtime stage
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the build artifact
COPY --from=build /app/ucla-discovery/build/libs/*.jar app.jar

# Run the application at container start
ENV SPRING_PROFILE "dev"
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILE}", "-jar", "app.jar"]
