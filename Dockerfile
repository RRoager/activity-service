FROM openjdk:17
EXPOSE 8081
ADD target/activity-service.jar activity-service.jar
ENTRYPOINT ["java","-jar","/activity-service.jar"]