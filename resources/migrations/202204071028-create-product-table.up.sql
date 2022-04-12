CREATE TABLE product(
  id int NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  description varchar(200) NOT NULL,
  price int NOT NULL,
  supplierId int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(supplierId) REFERENCES supplier(id) ON UPDATE CASCADE ON DELETE CASCADE
);
--;;
insert into product(id, name, description, price, supplierId) values
(1,'Xiaomi 12','Mobile phone',600, 1),
(2,'Yonex racket','Tennis racket',200, 2),
(3,'Galaxy 4 watch','Smart watch',300, 3),
(4,'Mi Band 6','Smart band',60, 1),
(5,'Mirage Pro','Bicycle',450, 4),
(6,'Mop Pro','Smart vacuum',350, 1);
