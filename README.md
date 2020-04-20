# spring-boot-userdetails

## services:
1. User-authorization-service
2. User-profile-service

## how to run
set active MQ with broker url: tcp://localhost:61616

mvn clean install
mvn spring-boot:run

Access the swagger url:
http://localhost:9090/swagger-ui.html#/

Application is using h2 in memory database. Data.sql is to setup the schema

http://localhost:8080/swagger-ui.html#/ can also be used to access user-profile-service, if ActiveMQ setup is not there.

