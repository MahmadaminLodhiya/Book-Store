

# REST API

#Book-Store

## Get list of Things

# book-controller
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
#author-controller

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
