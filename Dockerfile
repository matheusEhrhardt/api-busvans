# Multi-stage build for Spring Boot (Java 17)
# 1) Build stage
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /workspace

# Pre-cache dependencies
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -B -q -e -DskipTests dependency:go-offline

# Copy sources and build
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -B -q -DskipTests package

# 2) Runtime stage
FROM eclipse-temurin:21-jre-alpine
ENV TZ=America/Sao_Paulo \
    JAVA_OPTS=""

# Add a non-root user
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
WORKDIR /app

# Copy the fat jar from the builder stage
COPY --from=builder /workspace/target/*.jar /app/app.jar

EXPOSE 8090

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
