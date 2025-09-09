# ---------- Build stage ----------
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy pom.xml and mvnw scripts first
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Ensure mvnw is executable
RUN chmod +x mvnw

# Pre-download dependencies (better caching)
RUN ./mvnw dependency:go-offline -B

# Copy the actual source code
COPY src ./src

# Package the jar
RUN ./mvnw clean package -DskipTests
