  # Bookstore Management System

  ## Introduction
  This project is a Bookstore Management System developed using Spring Boot. It provides APIs for managing books in a bookstore and utilizes Swagger for API documentation.

  ## Software Requirements
  - Java 17
  - Gradle 7.6.3 (Binary)
  - IntelliJ Community Edition

  ## Setup Instructions
    1. Clone the repository: https://github.com/MahmadaminLodhiya/Book-Store
    
    2. Open the project in IntelliJ IDEA.
    
    3. Configure Java 17:
    - Go to `File` > `Project Structure`.
    - Under `Project`, ensure that the Project SDK is set to Java 17.
    
    4. Configure Gradle 7.6.3:
    - Ensure that Gradle 7.6.3(Binary) is installed and configured in IntelliJ IDEA. You can download Gradle from https://gradle.org/releases/.
    - In IntelliJ IDEA, go to `File` > `Settings` > `Build, Execution, Deployment` > `Build Tools` > `Gradle`.
    - Set Gradle home to the location where Gradle 7.6.3 is installed.

    5. Configure Database:
    - The project uses an in-memory H2 database by default. No additional setup is required.

    6. Run the Application:
    - Run the main application class (`BookstoreApplication`) from IntelliJ IDEA.

    ## API Documentation
    Swagger is integrated into the project for API documentation. After running the application, you can access the Swagger UI at: http://localhost:8080/swagger-ui/index.html#/

    ## Testing
    You can test the APIs using tools like Postman or cURL. Make sure the application is running before sending requests.
## Contributors
    - [Niraj Ghetiya](https://github.com/NirajGhetiya)
    - [Mahmadamin Lodhiya](https://github.com/MahmadaminLodhiya)

# REST API

#Book-Store

## Get list of Things

# book-controller
## get all book 
### Request

`curl -X 'GET' \
  'http://localhost:8080/api/v1/AllBook' \
  -H 'accept: */*'`

   

### Response
```json
    {
  "Data": [
    {
      "id": 0,
      "title": "string",
      "isbn": "string",
      "price": 0,
      "authorId": 0
    }
  ],
  "Success": true,
  "Massage": "string"
 }
```
##  get book by title
### Request

`curl -X 'GET' \
  'http://localhost:8080/api/v1/BookByTitle/Xyz' \
  -H 'accept: */*'`

   

### Response
```json
    {
 {
  "Data": {
    "price": 50,
    "author": {
      "id": 1,
      "name": "Niraj",
      "email": "niraj.ghetiya@mybook.com"
    },
    "title": "xyz",
    "isbn": "123-789-456"
  },
  "Success": true,
  "Message": null
}
```
## add a new book
### Request

`url -X 'POST' \
  'http://localhost:8080/api/v1/AddBook' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 0,
  "authorid": 1,
  "title": "string",
  "isbn": "string"
}'`

   

### Response
```json
  {
  "Data": "string",
  "Success": true,
  "Message": "string"
}
```
## delete a book
### Request

`curl -X 'DELETE' \
  'http://localhost:8080/api/v1/Book/2' \
  -H 'accept: */*'`

   

### Response
```json
  {
  "Data": {
    "id": 0,
    "title": "string",
    "isbn": "string",
    "price": 0,
    "authorId": 0
  },
  "Success": true,
  "Message": "string"
}
```
## update whole book
### Request

`curl -X 'PUT' \
  'http://localhost:8080/api/v1/Book/2' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "price": 0,
  "authorid": 1,
  "title": "string",
  "isbn": "string"
}'`
### Response
```json
{
  "Data": {
    "id": 0,
    "title": "string",
    "isbn": "string",
    "price": 0,
    "authorId": 0
  },
  "Success": true,
  "Message": "string"
}
```
# author-controller
## get all the authors
### Request

`curl -X 'GET' \
  'http://localhost:8080/Authors/GetAllAuthor' \
  -H 'accept: */*'`
### Response
```json
{
  "Data": [
    {
      "id": 0,
      "name": "string",
      "email": "string"
    }
  ],
  "Success": true,
  "Message": "string"
}
```
## get all the books of the author
### Request

`curl -X 'GET' \
  'http://localhost:8080/Authors/GetAllBooksOfAuthor/1' \
  -H 'accept: */*'`
### Response
```json
{
  "Data": [
    {
      "id": 0,
      "title": "string",
      "isbn": "string",
      "price": 0,
      "authorId": 0
    }
  ],
  "Success": true,
  "Message": "string"
}
```
## add a new author
### Request

`curl -X 'POST' \
  'http://localhost:8080/Authors/Author' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "string",
  "email": "string"
}'`
### Response
```json
{
  "Data": "string",
  "Success": true,
  "Message": "string"
}
```
## update the whole author details
### Request

`curl -X 'PUT' \
  'http://localhost:8080/Authors/UpdateAuthor/1' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "string",
  "email": "strinvbhg"
}'`
### Response
```json
{
  "Data": {
    "id": 0,
    "name": "string",
    "email": "string"
  },
  "Success": true,
  "Message": "string"
}
```
