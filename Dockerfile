# ===== Stage 1: build (Maven) =====
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o POM e baixa deps (cacheável)
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -q -B -DskipTests dependency:go-offline

# Copia o código e empacota
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -B -DskipTests clean package

# ===== Stage 2: runtime (JRE) =====
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia qualquer jar gerado (não depende de -SNAPSHOT)
COPY --from=build /app/target/*.jar app.jar

# Flags opcionais para a JVM
ENV JAVA_OPTS=""

EXPOSE 8080
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
