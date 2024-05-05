FROM jelastic/maven:3.9.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-slim
COPY --from=build /target/editor-0.0.1-SNAPSHOT.jar editor.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","editor.jar"]
