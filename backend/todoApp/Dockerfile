FROM eclipse-temurin:25

WORKDIR /app

COPY . /app

COPY target/todoApp-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java","-jar","app.jar"]
