FROM openjdk:8-jre-alpine
WORKDIR /notification
COPY ./target/apix2019-microservice-notification-kotlin-0.0.1-SNAPSHOT.jar /notification/app.jar
CMD ["/usr/bin/java","-jar","/notification/app.jar"]

