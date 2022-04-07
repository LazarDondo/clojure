CREATE TABLE product(
  id int NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  description varchar(200) NOT NULL,
  price int NOT NULL,
  supplierId int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(supplierId) REFERENCES supplier(id)
);
--;;
insert into product(id, name, description, price, supplierId) values
(1,'MI 12','Mobile phone',600, 1),
(2,'Yonex racket','Tennis racket',200, 2);
