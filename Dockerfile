# ====================================================
# SD26 Encoder Web Application - Docker Image
# ====================================================
# Stage 1: Build the application with Maven
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /build

# Copy Maven wrapper and pom.xml first for dependency caching
COPY pom.xml ./
COPY src ./src

# Build the application JAR (skip tests for Docker build speed)
RUN mkdir -p /app && \
    apk add --no-cache maven && \
    mvn clean package -DskipTests -Djacoco.skip=true -q && \
    mv target/sd26-encoder.jar /app/sd26-encoder.jar

# ====================================================
# Stage 2: Create a minimal runtime image
FROM eclipse-temurin:21-jre-alpine

RUN addgroup -S sd26 && adduser -S sd26 -G sd26

WORKDIR /app

# Copy the JAR from build stage
COPY --from=builder /app/sd26-encoder.jar ./sd26-encoder.jar

# Security: run as non-root user
USER sd26

# Expose the application port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
    CMD wget -qO- http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "/app/sd26-encoder.jar"]
