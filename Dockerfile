FROM xldevops/jdk17-lts
LABEL authors="Chicring"
EXPOSE 8888

USER app

ADD target/hjong-0.0.1-SNAPSHOT.jar /app/app.jar
RUN ls -l /app

ENTRYPOINT ["java","-jar","/app/app.jar"]