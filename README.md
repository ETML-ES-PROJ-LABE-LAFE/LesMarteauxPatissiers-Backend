# Auction Back-End Application

//TODO C'est important de faire un "cross-check" avec le template livré. Cela vous aidera à structure votre contenu. [Readme - template](https://github.com/NGY-TEMPLATE/MASTER-README)

This is a Spring Boot-based Auction Application. The application allows users to manage auction items, categories, and subcategories. It also includes CRUD operations for these entities.

## Features

- Manage auction items
- Manage categories and subcategories
- Basic CRUD operations
- CORS configuration for frontend integration
- Exception handling for robust API

## Technologies Used

//TODO Cette partie ne me semble pas pertinente. C'est information sont déjà présentes dans le code. Chaque mise à jour va vous contraindre à les mettre à jour ici également.
//TODO Soyez consistent. Parfois des versions, parfois pas....
//TODO Annoncer des technos, et dites à quoi elles servent dans le projet.
//TODO Redondance avec la partie "Prerequisistes"

- Java 17
- Spring Boot 3.2.4
- Spring Data JPA
- Hibernate
- H2 Database (for development)
- Maven
- Lombok (optional for reducing boilerplate code)

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- IDE of your choice (IntelliJ IDEA recommended)

### Installation

1. Clone the repository
    //TODO ne mentionnez pas le lien du dépôt. Si vous modifiez le lien on sera bon pour devoir le modifier là aussi.

    ```bash
    git clone https://github.com/ETML-ES-PROJ-LABE-LAFE/LesMarteauxPatissiers-Backend.git
    cd LesMarteauxPatissiers-Backend
    ```

2. Build the project using Maven
    ```bash
    mvn clean install
    ```

    //TODO résultat attendu ?
    //TODO clean install, ne fait qu'un build [Maven Life Cycle](https://www.marcobehler.com/guides/mvn-clean-install-a-short-guide-to-maven)?

3. Run the application
    ```bash
    mvn spring-boot:run
    ```

The application will start on `http://localhost:8080`.

### API Endpoints

If you run the application, you can use Swagger to check API Endpoints on the link [HERE](http://localhost:8080/swagger-ui.html)
Otherwise you can see them [HERE](https://github.com/ETML-ES-PROJ-LABE-LAFE/LesMarteauxPatissiers-Backend/wiki/API-END-POINTS) to see the end points 

### CORS Configuration

//TODO ce paramètrage doit pouvoir être modifié sans devoir "rebuild"
//TODO ne mélangez pas la prise en main pour le développement ou le déploiement.

The application is configured to allow CORS requests from `http://localhost:8081`. You can modify this in the `CorsConfig` class or by using the `@CrossOrigin` annotation in your controllers.

### Exception Handling

//TODO vous ne le faies plus ainsi, non ?

Global exception handling is provided using `@ControllerAdvice` and custom exception classes to handle not found and other exceptions gracefully.

### Database

The application uses H2 in-memory database for development. The database schema is automatically created on startup.

### Running Tests

To run tests, use the following command:
```bash
    mvn test
```

//TODO ajouter la commande pour ne lancer qu'un seul test

### Contributing

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


//TODO Licence ?
