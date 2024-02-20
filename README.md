

# REST API

The REST API to the example app is described below.

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
