CREATE TABLE productorder(
  id int NOT NULL AUTO_INCREMENT,
  quantity int NOT NULL,
  orderDate date NOT NULL,
  shippingAddress varchar(50) NOT NULL,
  productId int NOT NULL,
  userId int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(productId) REFERENCES product(id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(userId) REFERENCES user(id) ON UPDATE CASCADE ON DELETE CASCADE
);
--;;
insert into productorder(id, quantity, orderDate, shippingAddress, productId, userId) values
(1,3,'2022-04-07', 'test', 1, 1),
(2,4,'2022-04-07', 'test', 2, 1),
(3,4,'2022-04-07', 'test', 2, 2);
