# Use Java 21 base image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY . .

# Grant execute permissions to Maven wrapper
RUN chmod +x ./mvnw

# Build the project with Maven
RUN ./mvnw -B -DskipTests clean install

# Expose the Spring Boot application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/storestock-1.0-SNAPSHOT.jar"]
