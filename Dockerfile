# Use an official Maven image to build the application
FROM maven:3.9.9-openjdk-21 as build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and src folder into the container
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use an official OpenJDK image to run the application
FROM openjdk:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the built jar file from the build image
COPY --from=build /app/target/storestock-1.0-SNAPSHOT.jar app.jar

# Expose the port that your Spring Boot app runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
