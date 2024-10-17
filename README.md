# Banking Application

This is a Spring Boot application for a banking system. It provides APIs for user registration, login, authentication, password reset, and managing beneficiaries.

## Project Structure

```
banking-application
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── bankingapplication
│   │   │           ├── BankingApplication.java
│   │   │           ├── config
│   │   │           │   ├── JwtConfig.java
│   │   │           │   └── SwaggerConfig.java
│   │   │           ├── controller
│   │   │           │   ├── AuthController.java
│   │   │           │   ├── UserController.java
│   │   │           │   └── BeneficiaryController.java
│   │   │           ├── model
│   │   │           │   ├── User.java
│   │   │           │   ├── Account.java
│   │   │           │   └── Beneficiary.java
│   │   │           ├── repository
│   │   │           │   ├── UserRepository.java
│   │   │           │   ├── AccountRepository.java
│   │   │           │   └── BeneficiaryRepository.java
│   │   │           ├── service
│   │   │           │   ├── AuthService.java
│   │   │           │   ├── UserService.java
│   │   │           │   └── BeneficiaryService.java
│   │   │           └── util
│   │   │               └── JwtUtil.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── data.sql
│   └── test
│       ├── java
│       │   └── com
│       │       └── bankingapplication
│       │           └── BankingApplicationTests.java
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Getting Started

To run the application, follow these steps:

1. Clone the repository: `git clone https://github.com/your-username/banking-application.git`
2. Navigate to the project directory: `cd banking-application`
3. Build the project: `./mvnw clean install`
4. Run the application: `./mvnw spring-boot:run`

## API Documentation

The API documentation is generated using Swagger. You can access it at `http://localhost:8080/swagger-ui.html` after starting the application.

## Configuration

The application uses H2 as the database. The database configuration can be found in the `application.properties` file.

## Testing

The application includes unit tests to ensure the correctness of the implemented functionality. You can run the tests using the following command:

```
./mvnw test
```

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvement, please create a new issue or submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.