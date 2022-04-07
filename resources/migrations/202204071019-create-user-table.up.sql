CREATE TABLE user(
  id int NOT NULL AUTO_INCREMENT,
  firstname varchar(50) NOT NULL,
  lastname varchar(50) NOT NULL,
  username varchar(30) NOT NULL,
  password varchar(15) NOT NULL,
  role varchar(10) NOT NULL,
  PRIMARY KEY (id)
);
--;;
insert into user(id, firstname, lastname, username, password, role) values
(1,'Lazar','Dondo','lazardondo@gmail.com','aaabbb','admin'),
(2,'John','Doe','johndoe@gmail.com','johndoe','user');

