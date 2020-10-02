FROM openjdk:8

COPY ./target/orderingservice-*.jar orderingservice.jar

EXPOSE 9112

CMD ["java","-jar","-Dspring.profile.active=dev","orderingservice.jar"]