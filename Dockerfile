FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/*.jar eventos.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/eventos.jar"]