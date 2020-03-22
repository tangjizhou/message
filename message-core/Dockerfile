FROM openjdk:11.0.6-jre-slim
VOLUME /tmp
ADD target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]