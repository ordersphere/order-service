# -------- Build stage --------
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /build

COPY ../ordersphere-common /build/ordersphere-common
RUN cd ordersphere-common && mvn clean install -DskipTests

WORKDIR /build/order-service
COPY order-service/pom.xml ./pom.xml
RUN mvn dependency:go-offline

COPY order-service/src ./src
RUN mvn clean package -DskipTests

# -------- Runtime stage --------
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /build/order-service/target/order-service-*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
