FROM openjdk:20-oracle
COPY target/*.jar healthapp.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","healthapp.jar"]
