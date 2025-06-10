# Use OpenJDK image as base
FROM openjdk:21-jdk

# Set working directory
WORKDIR /app

# Copy built jar into container
COPY target/fairshare-0.0.1-SNAPSHOT.jar app.jar

# Expose app port (change if your app uses a different one)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
