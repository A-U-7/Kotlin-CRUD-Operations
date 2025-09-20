# Build stage
FROM gradle:8.4.0-jdk21 AS build
WORKDIR /app

# Enable Gradle build cache
ENV GRADLE_OPTS="-Dorg.gradle.caching=true"

# Copy build files
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle ./gradle

# Download dependencies
RUN --mount=type=cache,target=/home/gradle/.gradle \
    ./gradlew dependencies --no-daemon

# Copy source code
COPY src ./src

# Build the application
RUN --mount=type=cache,target=/home/gradle/.gradle \
    ./gradlew bootJar --no-daemon

# Runtime stage
FROM eclipse-temurin:21-jre-jammy

# Set timezone
ENV TZ=Asia/Kolkata

# Create data directory and set permissions
RUN mkdir -p /data && \
    groupadd -r spring && \
    useradd -r -g spring spring && \
    chown -R spring:spring /data && \
    chmod 755 /data

# Set working directory
WORKDIR /app

# Set permissions for the app directory
RUN chown -R spring:spring /app

# Switch to non-root user
USER spring

# Copy the built application from the build stage
COPY --from=build --chown=spring:spring /app/build/libs/*.jar app.jar

# JVM optimization flags for containerized environments
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+UseG1GC -XX:+OptimizeStringConcat -XX:+UseStringDeduplication -Djava.security.egd=file:/dev/./urandom"

# Expose the application port
EXPOSE 8080

# Run the application with optimized JVM settings
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
