-- USERS
INSERT INTO `webshop`.`user` (`discriminator`, `username`, `name`, `password`, `reg_date`, `role`, `surname`) VALUES ('MANAGER', 'mina', 'Mina', 'mina', '2017-06-01 00:34:11', 'manager', 'Medic');
INSERT INTO `webshop`.`user` (`discriminator`, `username`, `name`, `password`, `reg_date`, `role`, `surname`) VALUES ('SELLER', 'kaca', 'Katarina', 'kaca', '2017-06-01 00:34:11', 'seller', 'Kaca');
INSERT INTO `webshop`.`user` (`discriminator`, `username`, `name`, `password`, `reg_date`, `role`, `surname`, `points`) VALUES ('CUSTOMER', 'voki', 'Voki', 'voki', '2017-06-01 00:34:01', 'customer', 'voki', '0');
