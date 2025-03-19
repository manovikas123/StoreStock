# Use Maven with JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS builder


WORKDIR /app


COPY pom.xml .
COPY src ./src


RUN mvn -B -DskipTests clean install


FROM openjdk:21-jdk-slim AS runtime

WORKDIR /app


COPY --from=builder /app/target/storestock-1.0-SNAPSHOT.jar app.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "app.jar"]
