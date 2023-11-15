FROM openjdk:11
WORKDIR /app
EXPOSE 8089
ADD http://localhost:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar /app/
ENTRYPOINT ["java", "-jar","achat-1.0.jar"]
