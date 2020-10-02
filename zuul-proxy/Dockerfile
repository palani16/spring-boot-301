FROM openjdk:8

COPY ./zuul-proxy-*.jar zuul-proxy.jar

EXPOSE 8000

CMD ["java","-jar","-Dspring.profile.active=local","zuul-proxy.jar"]