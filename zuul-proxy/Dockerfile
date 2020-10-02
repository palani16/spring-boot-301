FROM openjdk:8

COPY ./target/zuul-proxy-*.jar zuul-proxy.jar

EXPOSE 9114

CMD ["java","-jar","-Dspring.profile.active=local","zuul-proxy.jar"]