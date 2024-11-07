FROM maven:3.9.9-ibm-semeru-23-jammy
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/ciu.grupo1.jar"]