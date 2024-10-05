CREATE DATABASE Murach;

USE Murach;

CREATE TABLE UserTest (
  UserID INT NOT NULL AUTO_INCREMENT, 
  FirstName VARCHAR(50), 
  LastName VARCHAR(50), 
  EmailAddress VARCHAR(50), 

  PRIMARY KEY (UserID)
);

INSERT INTO UserTest 
    (FirstName, LastName, EmailAddress)
VALUES 
    ('John', 'Smith', 'jsmith@gmail.com'), 
    ('Andrea', 'Steelman', 'andrea@murach.com'), 
    ('Joel', 'Murach', 'joelmurach@yahoo.com');
    