FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} arilab.jar
ENTRYPOINT ["java","-jar","/arilab.jar"]