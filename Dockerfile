FROM openjdk:17-jdk-alpine
COPY target/PortifolioSenai-0.0.1-SNAPSHOT.jar PortifolioSenaiApi.jar
ENTRYPOINT [ "java", "-jar", "/PortifolioSenaiApi.jar" ]
EXPOSE 8080
