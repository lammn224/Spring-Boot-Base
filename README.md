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

## Reference

- [Spring Security: Authentication and Authorization In-Depth]
- [Introduction to Spring Method Security]
- [Quick Guide to MapStruct]

[Spring Security: Authentication and Authorization In-Depth]: <https://www.marcobehler.com/guides/spring-security#_authentication_with_spring_security>

[Introduction to Spring Method Security]: <https://www.baeldung.com/spring-security-method-security>

[Quick Guide to MapStruct]: <https://www.baeldung.com/mapstruct>
