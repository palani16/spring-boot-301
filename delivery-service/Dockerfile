# Delivery Service
FROM openjdk:8
COPY ./target/delivery-service-0.0.1-SNAPSHOT.war  delivery-service-0.0.1-SNAPSHOT.war 
EXPOSE 9111
CMD ["java","-jar","-Dspring.profile.active=dev","delivery-service-0.0.1-SNAPSHOT.war "]
