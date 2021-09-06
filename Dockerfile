FROM openjdk:8
EXPOSE 8080
ARG JAR_FILE=target/student-management-system.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]