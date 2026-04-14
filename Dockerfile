# FROM maven:3.9.9-eclipse-temurin-21
#
# WORKDIR /app
#
# COPY . .
#
# RUN mvn clean package -DskipTests
#
# EXPOSE 8080
#
# CMD ["java", "-jar", "target/ems-backend-0.0.1-SNAPSHOT.jar"]
#
# CMD ["sh", "-c", "java -Xmx256m -Xms128m -Dserver.port=$PORT -jar target/ems-backend-0.0.1-SNAPSHOT.jar"]

# BUILD
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# RUN
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["sh", "-c", "java -Xmx256m -Xms128m -Dserver.port=$PORT -jar app.jar"]