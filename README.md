# Microservices Project with API Gateway

## 📌 Overview

This project demonstrates a professional microservices architecture using **Spring Boot 3**, **Docker**, and **API Gateway**.  
All microservices are accessible through a **single entry point** (the API Gateway), providing a clean and centralized interface for testing and usage.

### Microservices included:

- **User Service** (`/users`)
- **Order Service** (`/orders`)
- **Product Service** (`/products`)

The API Gateway handles routing requests to the appropriate microservice.

---
## Tecnologías

- Java 17
- Spring Boot
- REST APIs
- Spring Cloud OpenFeign
- Spring Cloud Gateway
- PostgreSQL
- Docker
- JUnit
- Maven
- Swagger

## 🏗 Architecture

    Client[Cliente] --> Gateway[API Gateway]

    Gateway --> UserService[User Service]
    Gateway --> OrderService[Order Service]
    Gateway --> ProductService[Product Service]

    UserService --> UserDB[(PostgreSQL)]
    OrderService --> OrderDB[(PostgreSQL)]
    ProductService --> ProductDB[(PostgreSQL)]

    UserService --> OrderService
    OrderService --> ProductService
   
- **API Gateway**: Centralized routing and entry point
- **Microservices**: Independently dockerized, follow RESTful conventions
- **Database**: PostgreSQL (used by User Service, Product Service y Order Service)
- **Postman**: Ready-to-use collection for testing endpoints

---

##  Getting Started (Docker)

### Requirements:

- Docker & Docker Compose
- Optional: Postman to test endpoints

### Steps:

1. Clone the repository:

```bash
git clone https://github.com/Yosleidy/microservices-project.git
cd microservices-project

docker-compose up --build

 ``` 
The API Gateway will be available at http://localhost:8080.

---
##  Testing Endpoints with Postman
1. Import Postman Collection:
File: postman-collections/Microservices Project Gateway.postman_collection.json
Set {{baseUrl}} variable to: http://localhost:8080
2. Example Requests

### User Service Endpoints

- **GET** `/users` → List all users
- **GET** `/users/{id}` → Get a user by ID
- **POST** `/users` → Create a new user
- **PUT** `/users/{id}` → Update an existing user
- **DELETE** `/users/{id}` → Delete a user

### Order Service Endpoints

- **GET** `/orders` → List all orders
- **GET** `/orders/{id}` → Get an order by ID
- **POST** `/orders` → Create a new order
- **PUT** `/orders/{id}` → Update the status of an existing order
- **DELETE** `/orders/{id}` → Delete an order

### Product Service Endpoints

- **GET** `/products` → List all products
- **GET** `/products/{id}` → Get a product by ID
- **POST** `/products` → Create a new product
- **PUT** `/products/{id}` → Update an existing product
- **DELETE** `/products/{id}` → Delete a product

Use Postman to run GET, POST, PUT, DELETE requests via the gateway.