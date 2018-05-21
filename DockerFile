FROM openjdk:8
ADD target/tasks-1.0.jar tasks-1.0.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","tasks-1.0.jar"]