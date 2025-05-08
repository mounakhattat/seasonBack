# Étape de construction
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape d'exécution
FROM openjdk:17-jdk-slim
# Installer libfreetype6
RUN apt-get update && apt-get install -y libfreetype6 && rm -rf /var/lib/apt/lists/*
COPY --from=build /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]