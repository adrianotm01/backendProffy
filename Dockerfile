FROM openjdk:8

VOLUME /tmp
ARG JAR_FILE=server-0.0.1-SNAPSHOT
COPY ${JAR_FILE}.jar app.jar
EXPOSE 8080
CMD ["java -jar ","/app.jar"]