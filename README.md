# Airbnb-WebApp

## Overview
Airbnb-WebApp is a Spring Boot-based backend application that provides basic CRUD operations for properties and user authentication using JWT. This project is designed to manage property listings and user accounts for an Airbnb-like service.

## Features
- User Registration and Login
- JWT-based Authentication
- CRUD Operations for Properties
- Exception Handling

## Technologies Used
- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Maven
- JPA/Hibernate
- MySQL (or any other relational database)

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven
- MySQL (or any other relational database)

### Installation

1. **Clone the repository:**
    ```sh
    git clone https://github.com/rahulpannati513/Airbnb-WebApp.git
    cd Airbnb-WebApp
    ```

2. **Configure the database:**
    Update the `application.properties` file with your database configuration.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
    spring.datasource.username=your_database_username
    spring.datasource.password=your_database_password
    spring.jpa.hibernate.ddl-auto=update
    jwt.secret=TmV3U2VjcmV0S2V5Rm9ySldUU2lnbmluZ1B1cnBvc2VzMTIzNDU2Nzg=
    ```

3. **Build the project:**
    ```sh
    mvn clean install
    ```

4. **Run the application:**
    ```sh
    mvn spring-boot:run
    ```

## API Endpoints

### Authentication
- **POST /api/auth/signup**: Register a new user
- **POST /api/auth/login**: Login and obtain a JWT token

### Properties
- **POST /api/properties**: Create a new property (requires JWT token)
- **GET /api/properties**: Get all properties
- **GET /api/properties/{id}**: Get a property by ID
- **PUT /api/properties/{id}**: Update a property by ID (requires JWT token)
- **DELETE /api/properties/{id}**: Delete a property by ID (requires JWT token)
- **GET /api/properties/users/{userId}/properties**: Get properties by user ID

## Exception Handling
Custom exceptions are used to handle errors gracefully:
- `UserNotFoundException`
- `PropertyNotFoundException`

## Contributing
Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## Contact
For any inquiries, please contact rahulpannati1@gmail.com.
