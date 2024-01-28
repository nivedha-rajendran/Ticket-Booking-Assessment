# Bookstore Assessment

## Table of Contents
- [Project Description](#project-description)
- [Contents](#contents)
- [Setup](#setup)
- [API Usage](#api-usage)


## Project Description
The Bookstore Assessment repository is a simple implementation of a product details API for a bookstore. 
It allows you to create, read, update, and delete product details, as well as retrieve a list of all products. 
The API also supports applying discounts or taxes to products.

## Contents
The "Bookstore Assessment" repository contains the following components:

- Controllers, services for handling product details and applying discounts or taxes.
- Models representing various entities in the system such as Product and ProductDetailDto.
- Configuration files for setting up the Spring Boot application.
- Test cases for the implemented functionalities.

## Setup
To set up the Bookstore Assessment project locally, follow these steps:

1. Clone the repository.
2. Open the project in your preferred Java IDE.
3. Run the application using `./gradlew bootRun`.
4. Access the Swagger UI for API documentation: [Swagger UI](http://localhost:8080/bookstore/assessment/swagger-ui/index.html#/)

## API Usage
To use the API-related files and components provided by this repository, follow these guidelines:


### Product Details Controller Payloads

### Create Product (POST) 

Payload : RequestBody

{
  "name": "history",
  "description": "Indian history",
  "retailPrice": 250,
  "quantityAvailable": 20
}


### Read Product (GET)

Payload : productId : 1


### Update Product (PUT)

Payload : productId : 1

RequestBody :
{
  "productId": 1,
  "name": "geography",
  "description": "geography",
  "retailPrice": 250,
  "quantityAvailable": 25
}

### Delete Product (DELETE)

Payload :  productId : 1

### Update Product (PUT) (apply discount or tax)

1 .Discount Payload :

   productId=1 & discount=20

2 .Tax Payload :

   productId : 1 & tax : 20 