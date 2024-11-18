FROM openjdk:21-jdk-slim AS build
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN chmod +x mvnw
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw clean package -DskipTests


FROM openjdk:21-jdk-slim
WORKDIR /challenge
COPY --from=build target/*.jar challenge.jar
ENTRYPOINT ["java", "-jar", "challenge.jar"]