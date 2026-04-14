FROM maven:3.9.9-eclipse-temurin-21

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

EXPOSE 8080

# CMD ["java", "-jar", "target/ems-backend-0.0.1-SNAPSHOT.jar"]

# CMD ["sh", "-c", "java -Dserver.port=$PORT -jar target/ems-backend-0.0.1-SNAPSHOT.jar"]

CMD ["java", "-Xmx256m", "-Xms128m", "-Server.port=$PORT", "-jar", "target/ems-backend-0.0.1-SNAPSHOT.jar"]