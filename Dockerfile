# Use an official Java 17 image as the base
FROM adoptopenjdk:17-jdk-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the Maven dependencies (pom.xml) to the working directory
COPY pom.xml .

# Download the Maven dependencies
RUN mvn dependency:go-offline

# Copy the application code to the working directory
COPY . .

# Build the application with Maven
RUN mvn package -DskipTests

# Expose the port for the Spring Boot application
EXPOSE 8080

# Run the Spring Boot application when the container starts
CMD ["java", "-jar", "target/ms-processos-0.0.1-SNAPSHOT.jar"]