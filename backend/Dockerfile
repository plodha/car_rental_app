FROM java:8-jdk-alpine

COPY ./target/car-rental-webapp-0.0.1-SNAPSHOT /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch car-rental-webapp-0.0.1-SNAPSHOT'

ENTRYPOINT ["java","-jar","car-rental-webapp-0.0.1-SNAPSHOT"]