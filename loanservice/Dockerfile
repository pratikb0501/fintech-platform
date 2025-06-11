
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app

COPY loanservice/mvnw .
COPY loanservice/.mvn .mvn
COPY loanservice/pom.xml .

RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline

COPY loanservice/src src

RUN ./mvnw -pl loanservice -am clean package -DskipTests

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app


COPY --from=build /app/loanservice/target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
