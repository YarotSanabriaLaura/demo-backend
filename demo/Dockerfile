# Etapa 1: construir el JAR con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos solo lo mínimo para resolver dependencias primero
COPY pom.xml .
COPY src ./src

# Construimos el JAR (sin tests para que sea más rápido)
RUN mvn clean package -DskipTests

# Etapa 2: imagen mínima solo con el JRE
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# IMPORTANTE: este nombre debe coincidir con el JAR que se generó
# Tú ya tienes: demo-0.0.1-SNAPSHOT.jar en target
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
