# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Final application image
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy the built JAR and changelogs
COPY --from=builder /build/target/fairshare-0.0.1-SNAPSHOT.jar app.jar
COPY --from=builder /build/src/main/resources/db/changelogs /db/changelogs

# Install wait-for-it and curl for healthcheck
RUN apt-get update && \
    apt-get install -y wget curl && \
    wget -O /wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh && \
    chmod +x /wait-for-it.sh && \
    apt-get remove -y wget && \
    apt-get autoremove -y && \
    rm -rf /var/lib/apt/lists/*

# Environment variables
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/fairshare
ENV SPRING_DATASOURCE_USERNAME=fairuser
ENV SPRING_DATASOURCE_PASSWORD=fairpass
ENV SPRING_LIQUIBASE_CHANGE_LOG=classpath:/db/changelogs/0001_add_init.sql

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

COPY entrypoint.sh .
RUN chmod +x entrypoint.sh
ENTRYPOINT ["./entrypoint.sh"]