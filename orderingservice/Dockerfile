FROM openjdk:8

COPY ./orderingservice-*.jar orderingservice.jar

EXPOSE 8081

CMD ["java","-jar","-Dspring.profile.active=dev","orderingservice.jar"]