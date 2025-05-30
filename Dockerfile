FROM eclipse-temurin:21-jre-alpine

RUN addgroup -S spring && adduser -S -G spring spring
USER spring:spring

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]