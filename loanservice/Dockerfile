# Stage 1: Build
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app

# Copy everything for Maven context
COPY . .

# Grant permission to mvnw
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Build the fat JAR
RUN ./mvnw clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy fat JAR
COPY --from=build /app/loanservice/target/loanservice-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
