-- CCATEGORY
INSERT INTO `webshop`.`ccategory` (`name`) VALUES ('Basic customer');
INSERT INTO `webshop`.`ccategory` (`name`) VALUES ('Silver customer');
INSERT INTO `webshop`.`ccategory` (`name`) VALUES ('Gold customer');

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

-- PRODUCT CATEGORY
INSERT INTO `webshop`.`product_category` (`max_discount`, `name`) VALUES ('10', 'Consumer goods');
INSERT INTO `webshop`.`product_category` (`max_discount`, `name`, `parent_category`) VALUES ('10', 'Cosmetics', '1');
INSERT INTO `webshop`.`product_category` (`max_discount`, `name`, `parent_category`) VALUES ('80', 'Food', '1');
INSERT INTO `webshop`.`product_category` (`max_discount`, `name`, `parent_category`) VALUES ('25', 'Appliances', '1');
INSERT INTO `webshop`.`product_category` (`max_discount`, `name`, `parent_category`) VALUES ('20', 'Alcoholic drinks', '1');
INSERT INTO `webshop`.`product_category` (`max_discount`, `name`, `parent_category`) VALUES ('20', 'Foreing alcoholic drinks', '5');
INSERT INTO `webshop`.`product_category` (`max_discount`, `name`, `parent_category`) VALUES ('30', 'Local alcoholic drinks', '5');
UPDATE `webshop`.`product_category` SET `consumer_goods`=FALSE WHERE `product_category_id`='1';
UPDATE `webshop`.`product_category` SET `consumer_goods`=TRUE WHERE `product_category_id`='2';
UPDATE `webshop`.`product_category` SET `consumer_goods`=TRUE WHERE `product_category_id`='3';
UPDATE `webshop`.`product_category` SET `consumer_goods`=FALSE WHERE `product_category_id`='4';
UPDATE `webshop`.`product_category` SET `consumer_goods`=TRUE WHERE `product_category_id`='5';
UPDATE `webshop`.`product_category` SET `consumer_goods`=TRUE WHERE `product_category_id`='7';
UPDATE `webshop`.`product_category` SET `consumer_goods`=TRUE WHERE `product_category_id`='6';
INSERT INTO `webshop`.`product_category` (`consumer_goods`, `max_discount`, `name`, `parent_category`) VALUES (FALSE, '15', 'TV', '4');

-- PRODUCT
INSERT INTO `webshop`.`product` (`product_id`,`creation_date`, `deleted`, `minimum_in_stock`, `name`, `price`, `refill`, `stock`, `product_category`) VALUES ('ADV1','2017-06-01 00:34:01', FALSE, '50', 'Vinjak', '750', FALSE, '100', '7');
INSERT INTO `webshop`.`product` (`product_id`,`creation_date`, `deleted`, `minimum_in_stock`, `name`, `price`, `refill`, `stock`, `product_category`) VALUES ('ADV2','2017-06-01 00:34:01', FALSE, '90', 'Votka Baltic', '560', FALSE, '150', '7');
INSERT INTO `webshop`.`product` (`product_id`,`creation_date`, `deleted`, `minimum_in_stock`, `name`, `price`, `refill`, `stock`, `product_category`) VALUES ('ASV1','2017-06-01 00:34:01', FALSE, '10', 'Votka Smirnoff classic', '1200', FALSE, '80', '6');
INSERT INTO `webshop`.`product` (`product_id`,`creation_date`, `deleted`, `minimum_in_stock`, `name`, `price`, `refill`, `stock`, `product_category`) VALUES ('ASV2','2017-06-01 00:34:01', FALSE, '5', 'Votka Smirnoff Ice', '200', FALSE, '200', '6');
INSERT INTO `webshop`.`product` (`product_id`, `creation_date`, `deleted`, `minimum_in_stock`, `name`, `price`, `refill`, `stock`, `product_category`) VALUES ('TV1', '2017-06-01 00:34:01', FALSE, '500', 'Samsung s509', '10000', FALSE, '900', '8');


-- ACTION
INSERT INTO `webshop`.`action` (`discount`, `from_date`, `name`, `to_date`) VALUES ('10', '2017-01-01 00:34:01', 'Ortodox christmas discount', '2017-01-08 00:34:01');
INSERT INTO `webshop`.`action` (`discount`, `from_date`, `name`, `to_date`) VALUES ('50', '2016-12-31 00:34:01', 'New Year action', '2017-01-01 00:34:01');
INSERT INTO `webshop`.`action` (`discount`, `from_date`, `name`, `to_date`) VALUES ('25', '2017-04-30 00:34:01', '1. May Spring sale', '2017-05-01 00:34:01');
INSERT INTO `webshop`.`action` (`discount`, `from_date`, `name`, `to_date`) VALUES ('10', '2017-06-30 00:34:01', 'July action', '2017-07-30 00:34:01');


-- ACTION PCATEGORY
INSERT INTO `webshop`.`action_pcategory` (`action_id`, `product_category_id`) VALUES ('1', '6');
INSERT INTO `webshop`.`action_pcategory` (`action_id`, `product_category_id`) VALUES ('1', '7');
INSERT INTO `webshop`.`action_pcategory` (`action_id`, `product_category_id`) VALUES ('2', '3');
INSERT INTO `webshop`.`action_pcategory` (`action_id`, `product_category_id`) VALUES ('3', '7');
INSERT INTO `webshop`.`action_pcategory` (`action_id`, `product_category_id`) VALUES ('3', '6');
INSERT INTO `webshop`.`action_pcategory` (`action_id`, `product_category_id`) VALUES ('3', '3');
INSERT INTO `webshop`.`action_pcategory` (`action_id`, `product_category_id`) VALUES ('4', '6');
INSERT INTO `webshop`.`action_pcategory` (`action_id`, `product_category_id`) VALUES ('4', '7');
