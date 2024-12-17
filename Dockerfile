FROM amazoncorretto:18 AS builder
WORKDIR /app
COPY pom.xml .

RUN ./usr/bin/mvn dependency:go-offline -B

COPY src ./src
RUN ./usr/bin/mvn clean package -DskipTests

FROM amazoncorretto:18-slim
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]