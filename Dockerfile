FROM openjdk:21
ARG JAR_FILE=target/*.jar
COPY ./target/atipera-0.0.1-SNAPSHOT.jar atipera_app.jar
ENTRYPOINT ["java","-jar","/atipera_app.jar"]