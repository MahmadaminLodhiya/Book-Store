CREATE TABLE Author(
  AuthorId INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  AuthorName  varchar(100) NOT NULL,
  Email varchar(100)
);


CREATE TABLE Book(
   id INTEGER  NOT NULL AUTO_INCREMENT PRIMARY KEY ,
   Title varchar(100) NOT NULL,
   AuthorId int,
   Isbn varchar(100),
   Price int,
   FOREIGN KEY (AuthorId) REFERENCES Author(AuthorId)

);