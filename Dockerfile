FROM openjdk:21-jdk-slim

COPY ./out/artifacts/Progetto_SeR_jar/Progetto-SeR.jar /app/Progetto_SeR_jar
COPY codice/Regione-Toscana---Strutture-ricettive.csv /app/Regione-Toscana---Strutture-ricettive.csv

WORKDIR /app

EXPOSE 1050
CMD ["java","-jar", "Progetto_SeR_jar"]