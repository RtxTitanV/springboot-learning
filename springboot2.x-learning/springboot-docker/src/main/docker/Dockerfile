FROM jdk1.8/ubuntu:v1.0.0
ENV ARTIFACTID springboot-docker
ENV VERSION 1.0.0
ENV HOME_PATH /work
ADD $ARTIFACTID-$VERSION.jar $HOME_PATH/app.jar
WORKDIR $HOME_PATH
ENTRYPOINT ["java","-jar","app.jar"]