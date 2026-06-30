# iTrade

iTrade is a simple e-commerce web application built with Spring Boot. Users can register, browse products, publish their own listings with images, search for products, and manage a shopping cart.

## Features

- User registration and authentication
- Spring Security authentication
- Create product listings
- Upload product images
- Product search
- Browse products from other users
- Shopping cart management
- Responsive UI based on the Amado template

## Technologies

### Backend
- Java 11
- Spring Boot 2.7
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate
- Lombok
- FreeMarker

## Project Structure

```text
configurations/
    ├── MvcConfig
    └── SecurityConfig

controllers/
    ├── CartController
    ├── ImageController
    ├── ProductController
    └── UserController

models/
    ├── Cart
    ├── Image
    ├── Product
    ├── User
    └── enums/

repositories/

services/
```

## Getting Started

### Requirements

- Java 11
- Maven
- MySQL

### Configure the database

Create a MySQL database named:

```text
itrade
```

Configure the following environment variables:

```text
DB_USERNAME=your_username
DB_PASSWORD=your_password
```

### Run

```bash
mvn spring-boot:run
```

or

```bash
mvn clean package
java -jar target/iTrade-0.0.1-SNAPSHOT.jar
```

## Image Storage

Uploaded product images are stored directly in the MySQL database as `LONGBLOB` records together with metadata such as filename, size, and content type.
