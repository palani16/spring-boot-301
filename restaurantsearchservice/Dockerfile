FROM openjdk:8

COPY ./target/restaurantsearchservice-*.jar restaurantsearchservice.jar

EXPOSE 9113

CMD ["java","-jar","-Dspring.profile.active=dev","restaurantsearchservice.jar"]