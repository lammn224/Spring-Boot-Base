# Spring Boot Base
*Spring Boot Base* is a **starter kit**. This project is a very simple and useful.

## Technologies
- Java (17)
- Spring Boot 3
- Spring Data JPA
- Spring Validation
- Spring Security 6 + JWT Token
- PostgreSQL
- Mapstruct
- Lombok
- Swagger (Open API)

## Customization

- You can customize ```token information (secret key, issuer, expiry date) ``` in [*application.yml*](https://github.com/Genc/spring-boot-boilerplate/blob/master/src/main/resources/application.yml#L40) file.
- You can customize ```database connection information``` in [*application.yml*](https://github.com/Genc/spring-boot-boilerplate/blob/master/src/main/resources/application.yml#L3) file.
- You can customize ```swagger information``` in [*application.yml*](https://github.com/Genc/spring-boot-boilerplate/blob/master/src/main/resources/application.yml#L45) file.
- You can customize ```which endpoints are accessible without token information``` in [*SecurityConfiguration.java*](https://github.com/Genc/spring-boot-boilerplate/blob/master/src/main/java/com/farukgenc/boilerplate/springboot/configuration/SecurityConfiguration.java#L45) file.

## Run the Application

First you need to make sure that the database is up.
If you're using Docker, you can use ```docker compose up -d``` command. (If you have made changes in local, you should use the *local-docker-compose* file.)

Navigate to the root of the project. For building the project using command line, run below command :

``` mvn clean install```

Run service in command line. Navigate to *target* directory.

``` java -jar spring-boot-base.jar ```

## Reference
- [Spring Security: Authentication and Authorization In-Depth]

[For Angular]: <https://github.com/Genc/angular-boilerplate>
[Spring Security: Authentication and Authorization In-Depth]: <https://www.marcobehler.com/guides/spring-security#_authentication_with_spring_security>
