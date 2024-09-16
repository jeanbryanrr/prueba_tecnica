FROM openjdk:17-jdk-alpine AS builder

WORKDIR /app/prueba-tecnica

COPY ./pom.xml /app
COPY ./.mvn ./.mvn
COPY ./mvnw .
COPY ./pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./src ./src/

RUN ./mvnw clean package -DskipTests

FROM  openjdk:17-jdk-alpine

WORKDIR /app

RUN mkdir ./logs


COPY --from=builder /app/prueba-tecnica/target/prueba-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

ENTRYPOINT ["java","-jar","prueba-0.0.1-SNAPSHOT.jar"]
