FROM openjdk:11-jre-slim-stretch
MAINTAINER Silas Candiolli
VOLUME /config
EXPOSE 8080
ADD target/music-library-reactive-api-0.0.1-SNAPSHOT.jar music-library-reactive-api.jar
COPY target/classes/config/* /config/
ENTRYPOINT [ "sh", "-c", "java -jar music-library-reactive-api.jar" ]