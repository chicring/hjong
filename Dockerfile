FROM xldevops/jdk17-lts
LABEL authors="Chicring"
EXPOSE 8888

ADD target/hjong-0.0.1-SNAPSHOT.jar /root/app.jar
RUN ls -l /root

ENTRYPOINT ["java","-jar","/root/app.jar"]