FROM openjdk:11.0.6-jre-slim
VOLUME /tmp
ADD target/message-core-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]