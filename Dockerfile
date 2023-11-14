FROM openjdk:11
WORKDIR /app
EXPOSE 8089
# Specify the absolute link to the JAR file
ADD http://192.168.1.6:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar /app/
ENTRYPOINT ["java", "-jar","achat-1.0.jar"]
