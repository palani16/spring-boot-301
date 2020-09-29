FROM openjdk:8

COPY ./eureka-server-*.jar eureka-server.jar

EXPOSE 8761

CMD ["java","-jar","-Dspring.profile.active=local","eureka-server.jar"]