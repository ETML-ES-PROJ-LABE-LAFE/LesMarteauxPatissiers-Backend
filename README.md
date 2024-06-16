# Auction Back-End Application

This is a Spring Boot-based Auction Application. The application allows users to manage auction items, categories, and subcategories. It also includes CRUD operations for these entities.

## Features

- Manage auction items
- Manage categories and subcategories
- Basic CRUD operations
- CORS configuration for frontend integration
- Exception handling for robust API

## Getting Started

### Prerequisites

- Java 17 or higher
- Spring Boot 3.2.4
- Spring Data JPA
- H2 Database (for development)
- Maven 3.6.0 or higher
- IDE of your choice (IntelliJ IDEA recommended)

### Installation

1. Clone the repository
    ```bash
    git clone https://github.com/{repository}
    cd {repository}
    ```

2. Build and resolve dependencies using Maven  
    ```bash
    mvn clean install
    ```

3. Run the application
    ```bash
    mvn spring-boot:run
    ```

The application will start on `http://localhost:8080`.

### API Endpoints

If you run the application, you can use swagger to check API Endpoints on the link [HERE](http://localhost:8080/swagger-ui.html).\
Otherwise, you can see them [HERE](https://github.com/ETML-ES-PROJ-LABE-LAFE/LesMarteauxPatissiers-Backend/wiki/API-END-POINTS).

### Exception Handling

Global exception handling is provided using `@ControllerAdvice` and custom exception classes to handle not found and other exceptions gracefully.

### Database

The application uses H2 in-memory database for development. The database schema is automatically created on startup.

### Running Tests

To run tests, use the following commands :
```bash
    mvn test        //to run all tests
```
```bash
    mvn -Dtest=TestClassName#testMethodName test        //to run a specific test. If you want to run all the tests in a specific class, you can omit the #testMethodName 
```

## Directory Structure

```
├───html-docs-diagrams                       // Class Diagramms to open on a browser or edit with StarUML
│   ├───assets
│   │   ├───css
│   │   ├───icon-font
│   │   ├───img
│   │   └───js
│   ├───contents
│   └───diagrams
├───MCD_MLD                                  // Database Diagramms
└──src                                       // Source code 
    ├───main
    │   ├───java
    │   │   └───ch
    │   │       └───etmles
    │   │           └───auction
    │   │               ├───Config
    │   │               ├───Controllers
    │   │               ├───DTOs
    │   │               ├───Entities
    │   │               ├───Exceptions
    │   │               ├───Mappers
    │   │               ├───Repositories
    │   │               └───Services
    │   └───resources
    └───test                                 
       └───java
           └───ch
              └───etmles
                   └───auction               // Test classes

```


## Contributing

We welcome contributions to improve this project! If you would like to contribute, please follow these steps :

1. Fork the repository: Click the "Fork" button on the top right of the repository page.

2. Clone your fork :
```bash
    git clone https://github.com/{yourfork}
    cd {yourforkRepository}
```

3. Create a new Branch
```bash
    git checkout -b feature-branch
```

4. Make your changes : Add your new features or bug fixes.

5. Commit your changes : 
```bash
    git commit -m 'Add some feature'
```

6. Push to your branch :
```bash
    git push origin feature-branch
```

7. Open a Pull Request : Go to the original repository, click on "Pull Requests" and submit your changes for review.


Please ensure your code adheres to our coding standards and includes appropriate tests.

## Licences
The Auction project is released under the ETML-ES License. You are free to use, modify, and distribute the code for both commercial and non-commercial purposes.

## Contact

### Project Developers 
evgueni.ignatiev@eduvaud.ch\
bruno.silva@eduvaud.ch\
dylan.lopez@eduvaud.ch

Feel free to contact us for any questions.
