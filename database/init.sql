-- CCATEGORY
INSERT INTO `webshop`.`ccategory` (`name`) VALUES ('basic');
INSERT INTO `webshop`.`ccategory` (`name`) VALUES ('silver');
INSERT INTO `webshop`.`ccategory` (`name`) VALUES ('gold');

-- USERS
INSERT INTO `webshop`.`user` (`discriminator`, `username`, `name`, `password`, `reg_date`, `role`, `surname`) VALUES ('MANAGER', 'mina', 'Mina', 'mina', '2017-06-01 00:34:11', 'manager', 'Medic');
INSERT INTO `webshop`.`user` (`discriminator`, `username`, `name`, `password`, `reg_date`, `role`, `surname`) VALUES ('SELLER', 'kaca', 'Katarina', 'kaca', '2017-06-01 00:34:11', 'seller', 'Kaca');
INSERT INTO `webshop`.`user` (`discriminator`, `username`, `name`, `password`, `reg_date`, `role`, `surname`, `points`, `address`, `ccategory`) VALUES ('CUSTOMER', 'voki', 'Voki', 'voki', '2017-06-01 00:34:01', 'customer', 'voki', '0', 'Pere Perica 12, Novi Sad', '1');
INSERT INTO `webshop`.`user` (`discriminator`, `username`, `name`, `password`, `reg_date`, `role`, `surname`, `points`, `address`, `ccategory`) VALUES ('CUSTOMER', 'jo', 'Jovana', 'jo', '2017-06-01 00:34:01', 'customer', 'Micic', '0', 'Mike Antica 343, Beograd', '1');

-- LIMIT
INSERT INTO `webshop`.`limit_range` (`limit_id`, `discount`, `maximum`, `minimum`) VALUES ('1', '10', '5000', '1000');
INSERT INTO `webshop`.`limit_range` (`discount`, `maximum`, `minimum`) VALUES ('20', '5001', '10000');
INSERT INTO `webshop`.`limit_range` (`discount`, `maximum`, `minimum`) VALUES ('25', '10001', '100000');

-- CATEGORY LIMIT
INSERT INTO `webshop`.`category_limit` (`ccategory_id`, `limit_id`) VALUES ('1', '1');
INSERT INTO `webshop`.`category_limit` (`ccategory_id`, `limit_id`) VALUES ('1', '2');
INSERT INTO `webshop`.`category_limit` (`ccategory_id`, `limit_id`) VALUES ('1', '3');
