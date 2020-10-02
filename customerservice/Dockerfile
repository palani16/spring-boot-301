FROM openjdk:8

COPY ./customerservice-*.jar customerservice.jar

EXPOSE 8083

CMD ["java","-jar","-Dspring.profile.active=dev","customerservice.jar"]