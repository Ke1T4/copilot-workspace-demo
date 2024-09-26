# Use openjdk:17-jdk as the base image
FROM openjdk:17-jdk

# Set the working directory
WORKDIR /app

# Copy the application JAR file to the container
COPY build/libs/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
