FROM openjdk:8

COPY ./restaurantsearchservice-*.jar restaurantsearchservice.jar

EXPOSE 8082

CMD ["java","-jar","-Dspring.profile.active=dev","restaurantsearchservice.jar"]