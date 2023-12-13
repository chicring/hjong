FROM xldevops/jdk17-lts
LABEL authors="Chicring"
EXPOSE 8888
WORKDIR /root
ADD target/hjong-0.0.1-SNAPSHOT.jar /root/app.jar
ENTRYPOINT ["java","-jar","/root/app.jar"]