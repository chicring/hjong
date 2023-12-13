FROM xldevops/jdk17-lts
LABEL authors="Chicring"
EXPOSE 8888
WORKDIR /root
ADD hjong*.jar /root/app.jar
ENTRYPOINT ["java","-jar","app.jar"]