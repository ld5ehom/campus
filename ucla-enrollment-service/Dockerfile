# Stage 1: Build Stage
FROM gradle:8.6-jdk17 AS build

# Set working directory
WORKDIR /app

# Copy Gradle Wrapper and source files to utilize Gradle cache
COPY ../gradlew .
COPY ../gradle gradle/
COPY ../build.gradle .
COPY ../settings.gradle .

# Use Gradle cache to install dependencies
RUN ./gradlew build -x test --stacktrace --no-daemon || return 0

# Copy project sources
COPY ucla-enrollment-service/ ucla-enrollment-service/
COPY ucla-grpc-common/ ucla-grpc-common/

# Build necessary modules
RUN ./gradlew -Pprotoc_platform=linux-x86_64 :ucla-enrollment-service:build -x test --stacktrace --no-daemon

# Stage 2: Runtime Stage
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy build artifacts
COPY --from=build /app/ucla-enrollment-service/build/libs/*.jar app.jar

# Run the application when the container starts
ENV SPRING_PROFILE "dev"
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILE}", "-jar", "app.jar"]
