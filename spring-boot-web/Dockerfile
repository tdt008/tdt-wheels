FROM openjdk:20-ea-17-jdk

MAINTAINER tdt<tudoutiao@163.com>

RUN mkdir -p /wwwroot/service/web

WORKDIR /wwwroot/service/web

EXPOSE 9090

ADD ./target/spring-boot-web-1.0-SNAPSHOT.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

