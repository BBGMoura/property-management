# Property Management Application
A property management RESTful API application which implements CRUD operations. 
This application utilises:
- Java
- Spring Boot
- Spring Data JPA
- H2 Database
- Project Lombok

This application utilises OOP aswell as a 4 layer application.
- controller
- service
- repository
- model

Communication with the H2 Database is done using Spring Data JPA.
Hibernate is used to map JPA Entities to database records.

In addition, the adaptor factory design pattern is utilised to convert DTOs to Entities and vise versa.
This is done using a Converter.


## API Documentation
### Save

### Update
