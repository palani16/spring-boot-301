# Eureka
FROM openjdk:8
COPY ./target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server-0.0.1-SNAPSHOT.jar
EXPOSE 8761
CMD ["java","-jar","-Dspring.profile.active=dev","eureka-server-0.0.1-SNAPSHOT.jar"]
