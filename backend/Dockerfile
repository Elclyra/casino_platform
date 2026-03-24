# ---- 1) Build stage ----
FROM eclipse-temurin:25-jdk AS builder

WORKDIR /app

# Copy files needed to resolve dependencies first
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

RUN chmod +x gradlew

# Download dependencies first (better layer caching)
RUN ./gradlew dependencies --no-daemon || true

# Copy source code
COPY src src

# Build the Spring Boot jar
RUN ./gradlew bootJar --no-daemon

# ---- 2) Runtime stage ----
FROM eclipse-temurin:25-jre

WORKDIR /app

# Copy built jar from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]