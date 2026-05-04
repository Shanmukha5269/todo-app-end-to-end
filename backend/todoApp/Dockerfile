# FROM eclipse-temurin:25

# WORKDIR /app

# COPY . /app

# COPY target/todoApp-0.0.1-SNAPSHOT.jar app.jar

# EXPOSE 8080

# CMD ["java","-jar","app.jar"]


### MULTISTAGE DOCKER FILE ####

FROM eclipse-temurin:25.0.3_9-jdk-ubi10-minimal AS build

WORKDIR /app

COPY .mvn .mvn

COPY mvnw pom.xml /app/

RUN chmod 001 ./mvnw

RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw package -DskipTests -B


FROM eclipse-temurin:25.0.3_9-jre-ubi10-minimal

WORKDIR /app

COPY --from=build /app/target/todoApp-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]



