# Spring Boot Base

*Spring Boot Base* is a **starter kit**. This project is a very simple and useful.

## Technologies

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Validation
- Spring Security 6 (JWT Token & Authorization)
- PostgreSQL
- Mapstruct
- Lombok
- Swagger (Open API)

## Customization

- You can customize ```which endpoints are accessible without token information``` in [*
  SecurityConstant.java*](https://github.com/lammn224/Spring-Boot-Base/blob/master/src/main/java/com/lammai/SpringBootBase/constant/SecurityConstant.java)
  file.

- You can customize ```token information (secret key, issuer, expiry date) ``` in [*
  SecurityConstant.java*](https://github.com/lammn224/Spring-Boot-Base/blob/master/src/main/java/com/lammai/SpringBootBase/constant/SecurityConstant.java)
  file.
- You can customize ```database connection information``` in [*
  application-dev.yml*](https://github.com/lammn224/Spring-Boot-Base/blob/master/src/main/resources/application-dev.yml)
  file.
- You can customize ```swagger information``` in [*
  SwaggerConfig.java*](https://github.com/lammn224/Spring-Boot-Base/blob/master/src/main/java/com/lammai/SpringBootBase/config/SwaggerConfig.java)
  file.

## Run the Application

### Run locally

- Step 1: Git clone repo
- Step 2: First you need to make sure that the database is up. Install database (e.g. PostgreSQL)
- Step 3: Make sure in [*
  application.properties*](https://github.com/lammn224/Spring-Boot-Base/blob/master/src/main/resources/application.properties)
  file you are using application-dev (```spring.profiles.active=dev```)
- Step 4: For building the project using command line, run command: ```mvn clean package```
- Step 5: Navigate to *target* directory, run: ``` java -jar spring-boot-base-1.0.jar ```

### Run with Docker

- Step 1: Make sure in [*
  application.properties*](https://github.com/lammn224/Spring-Boot-Base/blob/master/src/main/resources/application.properties)
  file you are using application-dev (```spring.profiles.active=prod```)
- Step 2: If you're using Docker, you can use ```docker compose up -d``` command (*docker-compose.yml* is being
  updated). (If you have made changes in local,
  you should use the *local-docker-compose* file.)

## Reference

- [Spring Security: Authentication and Authorization In-Depth]
- [Introduction to Spring Method Security]
- [Quick Guide to MapStruct]
- [Connect to Kafka running in Docker]
- [Connect to Kafka running in Docker using Bitnami Image]

[Spring Security: Authentication and Authorization In-Depth]: <https://www.marcobehler.com/guides/spring-security#_authentication_with_spring_security>

[Introduction to Spring Method Security]: <https://www.baeldung.com/spring-security-method-security>

[Quick Guide to MapStruct]: <https://www.baeldung.com/mapstruct>

[Connect to Kafka running in Docker]: <https://stackoverflow.com/questions/51630260/connect-to-kafka-running-in-docker>

[Connect to Kafka running in Docker using Bitnami Image]:<https://github.com/bitnami/containers/tree/main/bitnami/kafka#accessing-apache-kafka-with-internal-and-external-clients>

[1]: http://www.github.com/lammn224

[2]: https://www.linkedin.com/in/lammn

--- 

**Connect with me**

[![github](https://cloud.githubusercontent.com/assets/17016297/18839843/0e06a67a-83d2-11e6-993a-b35a182500e0.png)][1]
[![linkedin](https://cloud.githubusercontent.com/assets/17016297/18839848/0fc7e74e-83d2-11e6-8c6a-277fc9d6e067.png)][2]
---