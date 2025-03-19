# Use Maven to build the application
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy the project files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use OpenJDK for running the app
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy the JAR file from the build image
COPY --from=build /app/target/storestock-1.0-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Ensure the correct database credentials are used
ENV SPRING_DATASOURCE_URL=jdbc:mysql://turntable.proxy.rlwy.net:50737/railway
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=fLnQndzAPraNEsNBybYtDQoaNwGqrwrB

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
