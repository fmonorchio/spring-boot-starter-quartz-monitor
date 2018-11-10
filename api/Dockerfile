# TODO: Removing after test

FROM openjdk:8-jdk-alpine

ADD *.jar app.jar

ENTRYPOINT ["java", "-Xmx256m", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]