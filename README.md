# iTrade

iTrade is a simple e-commerce web application built with Spring Boot.  
Users can register, browse products, publish their own listings with images, search products, and manage a shopping cart.

---

## Features

- User registration and authentication
- Spring Security-based login system
- Create product listings
- Upload product images
- Product search
- Browse products from other users
- Shopping cart management
- Responsive UI (Amado template)

---

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
- MySQL

---

## Project Structure

```
configurations/
├── MvcConfig.java
├── SecurityConfig.java

controllers/
├── CartController.java
├── ImageController.java
├── ProductController.java
├── UserController.java

models/
├── Cart.java
├── Image.java
├── Product.java
├── User.java
└── enums/

repositories/
├── CartRepository.java
├── ImageRepository.java
├── ProductRepository.java
├── UserRepository.java

services/
├── CartService.java
├── ProductService.java
├── UserService.java
├── CustomUserDetailsService.java
```

---

## Getting Started

### Requirements

- Java 11+
- Maven
- MySQL
- Docker

---

## Run with Docker

```bash
git clone https://github.com/RomanMakarov6666/itrade-amado.git
cd itrade-amado
cp .env.example .env
docker-compose up --build
```

Application will be available at:

http://localhost:8080

MySQL will run at:

http://localhost:3307

---

## Run locally

### Create database

```sql
CREATE DATABASE itrade;
```

---

### Set environment variables

```
DB_USERNAME=your_username
DB_PASSWORD=your_password
```

---

### Run application

```bash
mvn spring-boot:run
```

or

```bash
mvn clean package
java -jar target/iTrade-0.0.1-SNAPSHOT.jar
```

---

## Image Storage

Product images are stored in the MySQL database as LONGBLOB along with metadata such as filename, size, and content type.
