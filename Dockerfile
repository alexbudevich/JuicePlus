FROM openjdk:17
MAINTAINER Alexandr Budevich <zacaznoy@gmail.com>

ADD ./target/juiceplus-0.0.1-SNAPSHOT.jar /app/
#CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9292", "-jar", "-Dspring.profiles.active=docker", "/app/juiceplus-0.0.1-SNAPSHOT.jar"]
CMD ["java", "-jar", "-Dspring.profiles.active=docker", "/app/juiceplus-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080
