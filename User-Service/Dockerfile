# ---------- Build Stage ----------
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app
COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon

# ---------- Runtime Stage ----------
FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]