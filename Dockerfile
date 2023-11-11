FROM openjdk:12-alpine

COPY target/achat-*.jar /achat.jar

CMD ["java" , "-jar", "/achat.jar"]