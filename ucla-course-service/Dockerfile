# Step 1: Build stage
FROM gradle:8.6-jdk17 AS build

# Set the working directory
WORKDIR /app

# Copy Gradle Wrapper and source files to leverage Gradle cache
COPY ../gradlew .
COPY ../gradle gradle/
COPY ../build.gradle .
COPY ../settings.gradle .

# Install dependencies leveraging Gradle cache
RUN ./gradlew build -x test --stacktrace --no-daemon || return 0

# Copy project source
COPY ucla-course-service/ ucla-course-service/

# Build required modules
RUN ./gradlew :ucla-course-service:build -x test --stacktrace --no-daemon

# Step 2: Execution stage
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the build artifacts
COPY --from=build /app/ucla-course-service/build/libs/*.jar app.jar

# Run the application when the container starts
ENV SPRING_PROFILE "dev"
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILE}", "-jar", "app.jar"]
