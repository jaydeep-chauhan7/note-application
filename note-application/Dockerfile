FROM openjdk:17
ARG JAR_FILE=target/note-application.jar
COPY ${JAR_FILE} note-application.jar
ENTRYPOINT ["java","-jar","/note-application.jar"]